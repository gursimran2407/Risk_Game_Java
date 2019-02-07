package com.risk.model;

import java.util.ArrayList;

public class MapRiskModel {
    private ArrayList<ContinentModel> continentModelModList;
    private ArrayList<CountryModel> countryModelList;
    private ArrayList<PlayerModel> playerModelList;

    public MapRiskModel(ArrayList<ContinentModel> continentModelModList, ArrayList<CountryModel> countryModelList, ArrayList<PlayerModel> playerModelList) {
        this.continentModelModList = continentModelModList;
        this.countryModelList = countryModelList;
        this.playerModelList = playerModelList;
    }


    public ArrayList<ContinentModel> getContinentModelModList() {
        return continentModelModList;
    }

    public void setContinentModelModList(ArrayList<ContinentModel> continentModelModList) {
        this.continentModelModList = continentModelModList;
    }

    public ArrayList<CountryModel> getCountryModelList() {
        return countryModelList;
    }

    public void setCountryModelList(ArrayList<CountryModel> countryModelList) {
        this.countryModelList = countryModelList;
    }

    public ArrayList<PlayerModel> getPlayerModelList() {
        return playerModelList;
    }

    public void setPlayerModelList(ArrayList<PlayerModel> playerModelList) {
        this.playerModelList = playerModelList;
    }
}
