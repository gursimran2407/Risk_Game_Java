package com.risk.controller;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCreateContinentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Map continent Controller
 * @author gursimransingh
 */
public class MapCreateContinentController implements ActionListener {
    private MapRiskModel mapRiskModel;
    private MapCreateContinentView mapCreateContinentView;

    /**
     * Constructor to create map continent
     */
    public MapCreateContinentController() {
        mapRiskModel = new MapRiskModel();
        mapCreateContinentView = new MapCreateContinentView();
        //Initializing Arraylist to store continent models

        mapRiskModel.addObserver(mapCreateContinentView);
        mapCreateContinentView.setActionListener(this);
        mapCreateContinentView.setVisible(true);
    }

    /**
     * @param e Performs action whenever there is a change in mapCreatecontinent viwe class
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ContinentModel continentModel;
        //Creating the Continent Model
        //If clicked on AddButton
        if (e.getSource().equals(mapCreateContinentView.addButton))
        //Now checking if the input text fields are empty or not.
        {
            if (!("".equals(mapCreateContinentView.controlValue.getText()) || mapCreateContinentView.controlValue.getText().isEmpty() || mapCreateContinentView.continentValue.getText().isEmpty()
                    || "".equals(mapCreateContinentView.continentValue.getText()))) {
                System.out.println("the input from the view is" + mapCreateContinentView.controlValue.getText()
                        + mapCreateContinentView.continentValue.getText());
                continentModel = new ContinentModel(mapCreateContinentView.continentValue.getText(),
                        Integer.parseInt(mapCreateContinentView.controlValue.getText()));
                if (0 < Integer.parseInt(mapCreateContinentView.controlValue.getText())
                        && Integer.parseInt(mapCreateContinentView.controlValue.getText()) < 10) {
                    if (mapCreateContinentView.continentValue != null) {
                        for (int index = 0; index < mapRiskModel.getContinentModelList().size(); index++) {
                            if (mapRiskModel.getContinentModelList().get(index).getContinentName()
                                    .equals(mapCreateContinentView.continentValue.getText())) {
                                JOptionPane.showOptionDialog(null, "You have already added this Continent", "Error",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                        new Object[]{}, null);
                                return;
                            }
                        }
                        mapRiskModel.getContinentModelList().add(continentModel);
                        mapRiskModel.setContinentModelModList(mapRiskModel.getContinentModelList());

                    } else {
                        JOptionPane.showOptionDialog(null, "Please enter some country name", "Error",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{},
                                null);
                        return;
                    }
                } else {
                    JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Error",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                    return;
                }
            } else {
                JOptionPane.showOptionDialog(null, "Please enter values in all the fields", "Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                return;
            }
        }

        //Else if next button is clicked
        else if (e.getSource().equals(this.mapCreateContinentView.nextButton)) {
            if (this.mapRiskModel.getCountryModelList().isEmpty()) {
                JOptionPane.showOptionDialog(null, "Enter atleast one Continent to the list", "Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                return;
            } else {
                //Creating Arraylist of Arraylist of points
                //Creating ColorList of Colorlist
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

                for (int i = 0; i < this.mapRiskModel.getContinentModelList().size(); i++) {
                    mapPointList.put(this.mapRiskModel.getContinentModelList().get(i).getContinentName(), pointsList.get(i));
                    colorMapList.put(this.mapRiskModel.getContinentModelList().get(i).getContinentName(), colorList.get(i));
                    indexMap.put(this.mapRiskModel.getContinentModelList().get(i).getContinentName(), 0);
                }

                new MapCreateContinentController(mapRiskModel, mapPointList, colorMapList, indexMap);
                this.mapCreateContinentView.dispose();
            }
        }



    }


}
