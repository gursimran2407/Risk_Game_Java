package com.risk.model;

import java.util.ArrayList;

public class Continent {
    String continentName;
    int controlValue;
    int noOfCountries;
    ArrayList<Country> countryList= new ArrayList<Country>();

    public Continent(String continentName, int controlValue, int noOfCountries, ArrayList<Country> countryList) {
        this.continentName = continentName;
        this.controlValue = controlValue;
        this.noOfCountries = noOfCountries;
        this.countryList = countryList;
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

    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }
}
