package com.risk.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author gursimransingh
 */
public class ContinentData {
    private static ContinentData instance = new ContinentData();
    private ObservableList<ContinentModel> continents;

    private ContinentData() {
        continents = FXCollections.observableArrayList();
    }

    public static ContinentData getInstance() {
        return instance;
    }

    public void addContinentModel(ContinentModel continentModel) {
        continents.add(continentModel);
    }

    public ObservableList<ContinentModel> getContinents() {
        return continents;
    }
}
