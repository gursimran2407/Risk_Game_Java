package com.risk.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author gursimransingh
 */
public class MapEditorData {
    private static MapEditorData instance = new MapEditorData();
    private ObservableList<ContinentModel> continents;
    private ObservableList<CountryModel> countrys;


    private MapEditorData() {
        continents = FXCollections.observableArrayList();
        countrys = FXCollections.observableArrayList();
    }

    public static MapEditorData getInstance() {
        return instance;
    }


    public void addCountryModel(CountryModel countryModel) {
        countrys.add(countryModel);
    }

    public void addContinentModel(ContinentModel continentModel) {
        continents.add(continentModel);
    }

    public ObservableList<ContinentModel> getContinents() {
        return continents;
    }

    public ObservableList<CountryModel> getCountry() {
        return countrys;
    }
}
