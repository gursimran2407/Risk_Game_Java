package com.risk.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author gursimransingh
 */
public class CountryData {
    private static CountryData instance = new CountryData();
    private ObservableList<CountryModel> country;

    private CountryData() {
        country = FXCollections.observableArrayList();
    }

    public static CountryData getInstance() {
        return instance;
    }

    public void addCountryModel(CountryModel countryModel) {
        country.add(countryModel);
    }

    public ObservableList<CountryModel> getCountry() {
        return country;
    }
}
