package com.risk.model;

import java.util.ArrayList;
import java.util.List;

public class CountryMod {

    private String countryName;
    private Player countryOwner;
    private String continentName;
    private ArrayList<CountryMod> linkCountry =  new ArrayList<>();
    private int numberofArmies;


    public CountryMod(String countryName, Player countryOwner, String continentName, ArrayList<CountryMod> linkCountry, int numberofArmies) {
        this.countryName = countryName;
        this.countryOwner = countryOwner;
        this.continentName = continentName;
        this.linkCountry = linkCountry;
        this.numberofArmies = numberofArmies;
    }

    public String getCountryName() {
        return countryName;
    }

    public Player getCountryOwner() {
        return countryOwner;
    }

    public String getContinentName() {
        return continentName;
    }

    public ArrayList<CountryMod> getLinkCountry() {
        return linkCountry;
    }

    public int getNumberofArmies() {
        return numberofArmies;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryOwner(Player countryOwner) {
        this.countryOwner = countryOwner;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public void setLinkCountry(ArrayList<CountryMod> linkCountry) {
        this.linkCountry = linkCountry;
    }

    public void setNumberofArmies(int numberofArmies) {
        this.numberofArmies = numberofArmies;
    }
}


