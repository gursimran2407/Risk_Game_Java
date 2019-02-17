package com.risk.controller;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCreateCountryView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MapCreateCountryController implements ActionListener {

    private MapRiskModel d_mapRiskModel;
    private MapCreateCountryView d_mapCreateCountryView;
    private ContinentModel d_newContinentModel;

    private HashMap<String, ArrayList<Point>> d_mapPointList;
    private HashMap<String, Color> d_colorMapList;
    private  HashMap<String, Integer> d_indexMap;

    public MapCreateCountryController(MapRiskModel new_mapRiskModel, HashMap<String, ArrayList<Point>> new_mapPointList,
                                   HashMap<String, Color> new_colorMapList, HashMap<String, Integer> new_indexMap) {
        d_mapRiskModel = new_mapRiskModel;
        d_mapPointList = new_mapPointList;
        d_colorMapList = new_colorMapList;
        d_indexMap = new_indexMap;
        d_mapCreateCountryView = new MapCreateCountryView(d_mapRiskModel.getContinentModelList());
        d_mapCreateCountryView.setVisible(true);
        d_mapRiskModel.addObserver(d_mapCreateCountryView);
        d_mapCreateCountryView.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
