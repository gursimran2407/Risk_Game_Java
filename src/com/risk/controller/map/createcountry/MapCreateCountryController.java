package com.risk.controller.map.createcountry;

import com.risk.Environment;
import com.risk.controller.map.countryconnect.MapCountryConnectController;
import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.map.createcountry.IMapCreateCountryView;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Map country Controller
 * @author Namita Faujdar
 */

public class MapCreateCountryController {

    private final Environment environment;

    private final MapRiskModel mapRiskModel;
    private final IMapCreateCountryView view;

    private final Map<String, List<Point>> mapPointList;
    private final Map<String, Color> colorMapList;
    private final Map<String, Integer> indexMap;

    /**
     * Constructor initializes values and sets the screen to visible
     * @param mapRiskModel
     * @param mapPointList
     * @param colorMapList
     * @param indexMap
     */

    public MapCreateCountryController(
            final Environment environment,
            final MapRiskModel mapRiskModel,
            final Map<String, List<Point>> mapPointList,
            final Map<String, Color> colorMapList,
            final Map<String, Integer> indexMap) {
        this.environment = environment;

        this.mapRiskModel = mapRiskModel;

        this.mapPointList = mapPointList;
        this.colorMapList = colorMapList;
        this.indexMap = indexMap;

        view = environment.getViewManager().createMapCreateCountryView(this.mapRiskModel.getContinentModelList());
        view.showView();

        this.mapRiskModel.addObserver(view);
        view.addCountryListener(this::addCountry);
        view.addShowMapCountryConnectListener(e -> showMapCountryConnect());
    }

    private void addCountry(final String countryValue, final ContinentModel continentModel) {
        if (countryValue != null && !countryValue.equals("")) {
            if (sameCountryNameValidation(countryValue)) {
                view.showMessage("Invalid", "Please enter a different country");
            } else {
                final CountryModel countryModel = new CountryModel();
                countryModel.setContinentName(continentModel.getContinentName());
                countryModel.setCountryName(countryValue);
                countryModel.setBackground(Color.WHITE);
                countryModel.setBorderColor(Color.BLACK);

                mapRiskModel.getCountryModelList().add(countryModel);
                mapRiskModel.setCountryModelList(mapRiskModel.getCountryModelList());
            }
        } else {
            view.showMessage("Invalid", "Please enter a valid input");
        }
    }

    private void showMapCountryConnect() {
        if (emptyContinentNameValidation()) {
            view.showMessage("Invalid", "Please enter at least one country for each continent");
        } else {
            for (int i = 0; i < mapRiskModel.getCountryModelList().size(); i++) {
                int index = indexMap.get(mapRiskModel.getCountryModelList().get(i).getContinentName());

                System.out.println("==>" + mapPointList.get(mapRiskModel.getCountryModelList().get(i).getContinentName()).get(index).x);
                mapRiskModel.getCountryModelList().get(i).setXPosition(
                        mapPointList.get(mapRiskModel.getCountryModelList().get(i).getContinentName()).get(index).x);
                mapRiskModel.getCountryModelList().get(i).setYPosition(
                        mapPointList.get(mapRiskModel.getCountryModelList().get(i).getContinentName()).get(index).y);
                mapRiskModel.getCountryModelList().get(i).setBackgroundColor(
                        colorMapList.get(mapRiskModel.getCountryModelList().get(i).getContinentName()));

                indexMap.put(
                        mapRiskModel.getCountryModelList().get(i).getContinentName(),
                        indexMap.get(mapRiskModel.getCountryModelList().get(i).getContinentName()) + 1);
            }

            new MapCountryConnectController(environment, mapRiskModel);
            view.hideView();
        }
    }

    /**
     * Check for same country validation
     * @return boolean
     */
    private boolean sameCountryNameValidation(final String countryValue) {
        for (final CountryModel countryModel : mapRiskModel.getCountryModelList()) {
            if (countryModel.getCountryName().equals(countryValue)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check for empty continent Value
     * @return boolean
     */
    private boolean emptyContinentNameValidation() {
        final List<ContinentModel> listOfContinents = mapRiskModel.getContinentModelList();
        final List<CountryModel> listOfCountries = mapRiskModel.getCountryModelList();

        String continentName = " ";
        for (int i = 0; i < listOfContinents.size(); i++) {
            continentName = listOfContinents.get(i).getContinentName();

            int count = 0;
            for (int j = 0; j < listOfCountries.size(); j++) {
                count++;
                if (continentName.equals(listOfCountries.get(j).getContinentName())) {
                    count = 0;
                    break;
                }
            }

            if (count == listOfCountries.size()) {
                return true;
            }
        }

        return false;
    }
}
