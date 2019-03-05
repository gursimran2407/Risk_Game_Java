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
 * In MapEditController, also takes care of the movement of
 * data into the model corresponding to the view and the controller and
 * also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Gursimran
 */
public class MapEditController {

    private final Environment environment;

    private IMapEditView view;
    private MapRiskModel mapRiskModel;

    private List<ContinentModel> updateContinentModelList;

    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
     * @param environment
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
    /**
     * This method is to add the countries to the game and update the view window
     * with the desired message.
     *
     * @param controlValue
     * @param continentModel
     */
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
    /**
     * This method takes updates from the view and assigns the control
     * value to the continents and save the values to the
     * model.
     * @param controlValue
     */
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
