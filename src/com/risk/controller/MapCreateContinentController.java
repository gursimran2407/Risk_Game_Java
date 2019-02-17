package com.risk.controller;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCreateContinentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        if (e.getSource().equals(mapCreateContinentView.addButton)) {
            continentModel = new ContinentModel(mapCreateContinentView.continentListText.getText(), Integer.parseInt(mapCreateContinentView.controlValue.getText()));

            //adding this to coninentmodel arraylist
            mapRiskModel.getContinentModelList().add(continentModel);

        }
    }


}
