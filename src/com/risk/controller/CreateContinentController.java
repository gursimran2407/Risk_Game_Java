package com.risk.controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.risk.Environment;
import com.risk.model.ContinentsModel;
import com.risk.model.GameMapModel;
import com.risk.view.ICreateContinentView;
import com.risk.view.events.ViewActionEvent;
import com.risk.view.events.ViewActionListener;

/**
 * In CreateContinentController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @author KaranPannu
 * @version 1.0.0
 *
 */

public class CreateContinentController implements ViewActionListener {

    /** The game map model. */
    private GameMapModel gameMapModel;

    /** The create continent view. */
    private ICreateContinentView createContinentView;

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public CreateContinentController() {
        this.gameMapModel = new GameMapModel();
        this.createContinentView =
                Environment.getInstance().getViewManager().newCreateContinentView();
        this.gameMapModel.addObserver(this.createContinentView);
        this.createContinentView.addActionListener(this);
        this.createContinentView.showView();
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param event the action event
     * @see ViewActionListener
     */
    @Override
    public void actionPerformed(ViewActionEvent event) {
        ContinentsModel tempContinent;
        if (ICreateContinentView.ACTION_ADD.equals(event.getSource())) {
            if (!("".equals(this.createContinentView.getControlValue())
                    || this.createContinentView.getControlValue().isEmpty()
                    || this.createContinentView.getContinentValue().isEmpty()
                    || "".equals(this.createContinentView.getContinentValue()))) {
                System.out.println("the input from the view is" + this.createContinentView.getControlValue()
                        + this.createContinentView.getContinentValue());
                tempContinent = new ContinentsModel(this.createContinentView.getContinentValue(),
                        Integer.parseInt(this.createContinentView.getControlValue()));
                if (0 < Integer.parseInt(this.createContinentView.getControlValue())
                        && Integer.parseInt(this.createContinentView.getControlValue()) < 10) {
                    if (this.createContinentView.getContinentValue() != null) {
                        for (int index = 0; index < this.gameMapModel.getContinents().size(); index++) {
                            if (this.gameMapModel.getContinents().get(index).getContinentName()
                                    .equals(this.createContinentView.getContinentValue())) {
                                createContinentView.showOptionDialog("You have already added this Continent", "Invalid");
                                return;
                            }
                        }
                        this.gameMapModel.getContinents().add(tempContinent);
                        this.gameMapModel.setContinents(this.gameMapModel.getContinents());

                    } else {
                        createContinentView.showOptionDialog("Please enter some country name", "Invalid");
                    }
                } else {
                    createContinentView.showOptionDialog("Please enter a control value between 0 and 10", "Invalid");
                }
            } else {
                createContinentView.showOptionDialog("Please enter values in all the fields", "Invalid");
            }
        } else if (ICreateContinentView.ACTION_NEXT.equals(event.getSource())) {
            if (this.gameMapModel.getContinents().isEmpty()) {
                createContinentView.showOptionDialog("Please enter atleast one Continent to proceed", "Invalid");
                return;
            }
            ArrayList<ArrayList<Point>> pointsList = new ArrayList<>();
            ArrayList<Color> colorList = new ArrayList<>();

            colorList.add(Color.RED);
            colorList.add(Color.GREEN);
            colorList.add(Color.BLUE);
            colorList.add(Color.CYAN);
            colorList.add(Color.ORANGE);

            ArrayList<Point> p = new ArrayList<>();
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

            HashMap<String, Color> colorMapList = new HashMap<>();
            HashMap<String, ArrayList<Point>> mapPointList = new HashMap<>();
            HashMap<String, Integer> indexMap = new HashMap<>();

            for (int i = 0; i < this.gameMapModel.getContinents().size(); i++) {
                mapPointList.put(this.gameMapModel.getContinents().get(i).getContinentName(), pointsList.get(i));
                colorMapList.put(this.gameMapModel.getContinents().get(i).getContinentName(), colorList.get(i));
                indexMap.put(this.gameMapModel.getContinents().get(i).getContinentName(), 0);
            }

            new CreateCountryController(this.gameMapModel, mapPointList, colorMapList, indexMap);
            this.createContinentView.hideView();
        }
    }
}
