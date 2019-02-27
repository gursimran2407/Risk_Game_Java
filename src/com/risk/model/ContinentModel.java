package com.risk.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for continents of the map. "continentName" and
 * "controlValue" are attributes of an object
 *
 * @author Karandeep
 *
 */

public class ContinentModel {
    private String continentName;
    private int controlValue;
    private int noOfCountries;
    private List<CountryModel> countryModelList;

    /**
     * Constructor of ContinentsModel
     *
     * @param continentName
     * @param controlValue
     */

    public ContinentModel(String continentName, int controlValue) {
        this.continentName = continentName;
        this.controlValue = controlValue;

    }
    /**
     * gets the continent name
     *
     * @return continentName.
     */

    public String getContinentName() {
        return continentName;
    }

    /**
     * sets the continent name
     *
     * @return continentName.
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /**
     * gets the control Value
     *
     * @return controlvalue.
     */
    public int getControlValue() {
        return controlValue;
    }
    /**
     * sets the control value
     *
     * @return controlValue.
     */
    public void setControlValue(int controlValue) {
        this.controlValue = controlValue;
    }
    /**
     * gets the number of countries
     *
     * @return noOfCountries.
     */
    public int getNoOfCountries() {
        return noOfCountries;
    }
    /**
     * sets the number of countries
     *
     * @return noOfCountries.
     */
    public void setNoOfCountries(int noOfCountries) {
        this.noOfCountries = noOfCountries;
    }
    /**
     * gets the list of countries from model
     *
     * @return countryModelList.
     */
    public List<CountryModel> getCountryModelList() {
        return countryModelList;
    }
    /**
     * sets the list of countries from model
     *
     * @return countryModelList.
     */
    public void setCountryModelList(List<CountryModel> countryModelList) {
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
