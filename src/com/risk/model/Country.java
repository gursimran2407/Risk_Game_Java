package com.risk.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryName;
    private Player countryOwner;
    private ArrayList<Country> adjacentCountriesList;
    private CountryCoordinates countryCoordinates;
    private int numberOfArmies;

    public Country(String countryName, Player countryOwner, ArrayList<Country> adjacentCountriesList, CountryCoordinates countryCoordinates, int numberOfArmies) {
        this.countryName = countryName;
        this.countryOwner = countryOwner;
        this.adjacentCountriesList = adjacentCountriesList;
        this.countryCoordinates = countryCoordinates;
        this.numberOfArmies = numberOfArmies;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Player getCountryOwner() {
        return countryOwner;
    }

    public void setCountryOwner(Player countryOwner) {
        this.countryOwner = countryOwner;
    }

    public List<Country> getAdjacentCountriesList() {
        return adjacentCountriesList;
    }

    public void setAdjacentCountriesList(ArrayList<Country> adjacentCountriesList) {
        this.adjacentCountriesList = adjacentCountriesList;
    }

    public CountryCoordinates getCountryCoordinates() {
        return countryCoordinates;
    }

    public void setCountryCoordinates(CountryCoordinates countryCoordinates) {
        this.countryCoordinates = countryCoordinates;
    }

    public int getNumberOfArmies() {
        return numberOfArmies;
    }

    public void setNumberOfArmies(int numberOfArmies) {
        this.numberOfArmies = numberOfArmies;
    }


}
