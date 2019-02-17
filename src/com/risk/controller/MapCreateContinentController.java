package com.risk.controller;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCreateContinentView;

import java.awt.*;
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
    private List<ContinentModel> continentModelList;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Constructor to create map continent
     */
    public MapCreateContinentController() {
        mapRiskModel = new MapRiskModel();
        mapCreateContinentView = new MapCreateContinentView();
        continentModelList = new ArrayList<>();

        mapRiskModel.addObserver(mapCreateContinentView);
        mapCreateContinentView.
    }
}
