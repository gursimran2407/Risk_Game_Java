package com.risk.controller.map.createcontinent;

import com.risk.Environment;
import com.risk.controller.map.createcountry.MapCreateCountryController;
import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.map.createcontinent.IMapCreateContinentView;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map continent Controller
 * @author gursimransingh
 */
public class MapCreateContinentController {

    private final Environment environment;

    private MapRiskModel model;
    private IMapCreateContinentView view;

    /**
     * Constructor to create map continent
     */
    public MapCreateContinentController(final Environment environment) {
        this.environment = environment;

        model = new MapRiskModel();
        view = environment.getViewManager().createMapCreateContinentView();

        // Initializing ArrayList to store continent models

        model.addObserver(view);
        view.addContinentListener(this::addContinent);
        view.addShowMapCreateCountryListener(e -> showMapCreateCountry());
        view.showView();
    }

    private void addContinent(final String controlValue, final String continentValue) {
        if (!("".equals(controlValue) || "".equals(continentValue))) {
            System.out.println("the input from the view is" + controlValue + continentValue);

            final ContinentModel continentModel = new ContinentModel(continentValue, Integer.parseInt(controlValue));
            if (0 < Integer.parseInt(controlValue) && Integer.parseInt(controlValue) < 10) {
                if (continentValue != null) {
                    for (int index = 0; index < model.getContinentModelList().size(); index++) {
                        if (model.getContinentModelList().get(index).getContinentName().equals(continentValue)) {
                            view.showMessage("You have already added this Continent");
                            return;
                        }
                    }

                    model.getContinentModelList().add(continentModel);
                    model.setContinentModelModList(model.getContinentModelList());
                } else {
                    view.showMessage("Please enter some country name");
                }
            } else {
                view.showMessage("Please enter a control value between 0 and 10");
            }
        } else {
            view.showMessage("Please enter values in all the fields");
        }
    }

    private void showMapCreateCountry() {
        if (model.getContinentModelList().isEmpty()) {
            view.showMessage("Enter at least one Continent to the list");
        } else {
            // Creating ArrayList of ArrayList of points
            // Creating ColorList of ColorList

            final List<List<Point>> pointsList = new ArrayList<>();
            final List<Color> colorList = new ArrayList<>();

            colorList.add(Color.RED);
            colorList.add(Color.GREEN);
            colorList.add(Color.BLUE);
            colorList.add(Color.CYAN);
            colorList.add(Color.ORANGE);

            List<Point> p = new ArrayList<>();
            p.add(new Point(330, 40));
            p.add(new Point(300, 95));
            p.add(new Point(255, 110));
            p.add(new Point(270, 120));
            p.add(new Point(325, 130));
            pointsList.add(p);

            p = new ArrayList<>();
            p.add(new Point(230, 160));
            p.add(new Point(265, 150));
            p.add(new Point(290, 160));
            p.add(new Point(300, 180));
            p.add(new Point(270, 195));
            pointsList.add(p);

            p = new ArrayList<>();
            p.add(new Point(200, 210));
            p.add(new Point(240, 200));
            p.add(new Point(255, 220));
            p.add(new Point(230, 245));
            p.add(new Point(275, 225));
            pointsList.add(p);

            p = new ArrayList<>();
            p.add(new Point(300, 210));
            p.add(new Point(290, 240));
            p.add(new Point(300, 260));
            p.add(new Point(260, 285));
            p.add(new Point(210, 270));
            pointsList.add(p);

            p = new ArrayList<>();
            p.add(new Point(165, 260));
            p.add(new Point(125, 220));
            p.add(new Point(120, 260));
            p.add(new Point(70, 290));
            p.add(new Point(30, 285));
            pointsList.add(p);

            Map<String, Color> colorMapList = new HashMap<>();
            Map<String, List<Point>> mapPointList = new HashMap<>();
            Map<String, Integer> indexMap = new HashMap<>();

            for (int i = 0; i < this.model.getContinentModelList().size(); i++) {
                mapPointList.put(this.model.getContinentModelList().get(i).getContinentName(), pointsList.get(i));
                colorMapList.put(this.model.getContinentModelList().get(i).getContinentName(), colorList.get(i));
                indexMap.put(this.model.getContinentModelList().get(i).getContinentName(), 0);
            }

            new MapCreateCountryController(
                    environment, model, mapPointList, colorMapList, indexMap);
            view.hideView();
        }
    }
}
