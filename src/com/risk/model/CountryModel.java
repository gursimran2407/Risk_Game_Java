package com.risk.model;

import java.util.ArrayList;

public class CountryModel {

    private String countryName;
    private PlayerModel countryOwner;
    private String continentName;
    private ArrayList<CountryModel> linkCountryModel;
    private int numberofArmies;


    public CountryModel(String countryName, PlayerModel countryOwner, String continentName, ArrayList<CountryModel> linkCountryModel, int numberofArmies) {
        this.countryName = countryName;
        this.countryOwner = countryOwner;
        this.continentName = continentName;
        this.linkCountryModel = linkCountryModel;
        this.numberofArmies = numberofArmies;
    }

    public String getCountryName() {
        return countryName;
    }

    public PlayerModel getCountryOwner() {
        return countryOwner;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setCountryOwner(PlayerModel countryOwner) {
        this.countryOwner = countryOwner;
    }

    public int getNumberofArmies() {
        return numberofArmies;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ArrayList<CountryModel> getLinkCountryModel() {
        return linkCountryModel;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public void setLinkCountryModel(ArrayList<CountryModel> linkCountryModel) {
        this.linkCountryModel = linkCountryModel;
    }

    public void setNumberofArmies(int numberofArmies) {
        this.numberofArmies = numberofArmies;
    }
}


