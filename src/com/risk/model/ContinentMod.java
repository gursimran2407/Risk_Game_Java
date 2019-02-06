package com.risk.model;

import java.util.ArrayList;

public class ContinentMod {
    private String continentName;
    private int controlValue;
    private int noOfCountries;
    private ArrayList<Country> countryList = new ArrayList<Country>();



    public ContinentMod(String continentName, int controlValue) {
        this.continentName = continentName;
        this.controlValue = controlValue;

    }
/** Default Constructor*/
    public ContinentMod()
    {
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
