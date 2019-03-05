package com.risk.controller.map.edit;

import com.risk.Environment;
import com.risk.controller.map.countryconnect.MapCountryConnectController;
import com.risk.gameplayrequirements.MapRead;
import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.map.edit.IMapEditView;

import java.util.ArrayList;
import java.util.List;

/**
 * In MapEditController, the data flow into model object and updates the
 * view whenever data changes.
 * @author gursimransingh
 */
public class MapEditController {

    private final Environment environment;

    private IMapEditView view;
    private MapRiskModel mapRiskModel;

    private List<ContinentModel> updateContinentModelList;

    /**
     * Constructor initializes values and sets the screen to visible
     */

    public MapEditController(final Environment environment) {
        this.environment = environment;

        final MapRead mapRead = new MapRead();
        mapRead.setReadFile(environment.getViewManager().openFile());
        List<ContinentModel> continentModelList = mapRead.getMapContinentDetails();

        mapRiskModel = new MapRiskModel();
        mapRiskModel.setCountryModelList(mapRead.getMapCountryDetails());
        mapRiskModel.setContinentModelModList(continentModelList);
        mapRiskModel.callObservers();

        // updating the continent model list
        updateContinentModelList = new ArrayList<>();
        continentModelList = mapRiskModel.getContinentModelList();

        // calling view constructor
        view = environment.getViewManager().createMapEditView(continentModelList);

        mapRiskModel.addObserver(view);

        view.addSaveListener(this::save);
        view.addContinentListener(this::addContinent);
        view.showView();
    }

    private void addContinent(final ContinentModel continentModel, final String controlValue) {
        if (controlValue != null && !controlValue.isEmpty()) {
            if (0 < Integer.parseInt(controlValue) && Integer.parseInt(controlValue) < 10) {
                // removing the selected continent from continent model
                mapRiskModel.removeContinent(continentModel);

                continentModel.setControlValue(Integer.parseInt(controlValue));
                updateContinentModelList.add(continentModel);

                System.out.println(updateContinentModelList);

            } else {
                view.showMessage("Invalid", "Please enter a control value between 0 and 10");
            }
        } else {
            view.showMessage("Invalid", "Please enter at least one control value");
        }
    }

    private void save(final String controlValue) {
        if (controlValue != null && !controlValue.isEmpty()) {
            if (0 < Integer.parseInt(controlValue) && Integer.parseInt(controlValue) < 10) {
                if (!updateContinentModelList.isEmpty()) {
                    mapRiskModel.setContinentModelModList(updateContinentModelList);
                    mapRiskModel = mapRiskModel.updateCountries(mapRiskModel);

                    new MapCountryConnectController(environment, mapRiskModel);
                    view.hideView();
                    // open connectCountries Controller and pass the map model
                } else {
                    view.showMessage("Invalid", "Please add atleast one continent first.");
                }
            } else {
                view.showMessage("Invalid", "Please enter a control value between 0 and 10");
            }
        } else {
            view.showMessage("Invalid", "Please enter at least one control value");
        }
    }
}
