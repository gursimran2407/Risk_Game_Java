package com.risk.controller;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

/**
 * Map continent Controller
 * @author gursimransingh
 */
public class MapCreateContinentController implements ActionListener {
    /** The game map model. */
    private GameMapModel gameMapModel;

    /** The create continent view. */
    private CreateContinentView createContinentView;

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public CreateContinentController() {
        this.gameMapModel = new GameMapModel();
        this.createContinentView = new CreateContinentView();
        this.gameMapModel.addObserver(this.createContinentView);
        this.createContinentView.setActionListener(this);
        this.createContinentView.setVisible(true);
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        ContinentsModel tempContinent;
        if (actionEvent.getSource().equals(this.createContinentView.addButton)) {
            if (!("".equals(this.createContinentView.controlValue.getText())
                    || this.createContinentView.controlValue.getText().isEmpty()
                    || this.createContinentView.continentValue.getText().isEmpty()
                    || "".equals(this.createContinentView.continentValue.getText()))) {
                System.out.println("the input from the view is" + this.createContinentView.controlValue.getText()
                        + this.createContinentView.continentValue.getText());
                tempContinent = new ContinentsModel(this.createContinentView.continentValue.getText(),
                        Integer.parseInt(this.createContinentView.controlValue.getText()));
                if (0 < Integer.parseInt(this.createContinentView.controlValue.getText())
                        && Integer.parseInt(this.createContinentView.controlValue.getText()) < 10) {
                    if (this.createContinentView.continentValue != null) {
                        for (int index = 0; index < this.gameMapModel.getContinents().size(); index++) {
                            if (this.gameMapModel.getContinents().get(index).getContinentName()
                                    .equals(this.createContinentView.continentValue.getText())) {
                                JOptionPane.showOptionDialog(null, "You have already added this Continent", "Invalid",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                        new Object[] {}, null);
                                return;
                            }
                        }
                        this.gameMapModel.getContinents().add(tempContinent);
                        this.gameMapModel.setContinents(this.gameMapModel.getContinents());

                    } else {
                        JOptionPane.showOptionDialog(null, "Please enter some country name", "Invalid",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                null);
                    }
                } else {
                    JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                }
            } else {
                JOptionPane.showOptionDialog(null, "Please enter values in all the fields", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            }
        } else if (actionEvent.getSource().equals(this.createContinentView.nextButton)) {
            if (this.gameMapModel.getContinents().isEmpty()) {
                JOptionPane.showOptionDialog(null, "Please enter atleast one Continent to proceed", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
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
            this.createContinentView.dispose();
        }

    }
}
