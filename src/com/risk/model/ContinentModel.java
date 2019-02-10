package com.risk.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ContinentModel {
    private String continentName;
    private int controlValue;
    private int noOfCountries;
    private ObservableList<CountryModel> countryModelList;


    public ContinentModel(String continentName, int controlValue) {
        this.continentName = continentName;
        this.controlValue = controlValue;

    }


    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public int getControlValue() {
        return controlValue;
    }

    public void setControlValue(int controlValue) {
        this.controlValue = controlValue;
    }

    public int getNoOfCountries() {
        return noOfCountries;
    }

    public void setNoOfCountries(int noOfCountries) {
        this.noOfCountries = noOfCountries;
    }

    public ObservableList<CountryModel> getCountryModelList() {
        return countryModelList;
    }

    public void setCountryModelList(ObservableList<CountryModel> countryModelList) {
        this.countryModelList = countryModelList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Continent Name:\n");
        sb.append(continentName + "\n");
        sb.append("Control Value: " + controlValue);
        return sb.toString();
    }
}