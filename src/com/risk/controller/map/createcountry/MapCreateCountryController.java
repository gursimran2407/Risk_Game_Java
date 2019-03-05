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
 * Map create country Controller, also takes care of the movement of
 * data into the model corresponding to the view and the controller and
 * also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Namita
 */

public class MapCreateCountryController {

    private final Environment environment;

    private final MapRiskModel mapRiskModel;
    private final IMapCreateCountryView view;

    private final Map<String, List<Point>> mapPointList;
    private final Map<String, Color> colorMapList;
    private final Map<String, Integer> indexMap;

    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
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
    /**
     * This method is to add the countries to the game and update the view window
     * with the desired message.
     *
     * @param countryValue
     * @param continentModel
     */
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
    /**
     * This method is to trigger view to show the connected countries on the map
     * of the players respective of the  selected countries
     */
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
     * This check is to validate the Name and country value if
     * it is same or not.
     *
     * @return countryValue
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
     * This method is validate the continent name if its empty or not
     * and return the result as per.
     *
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
