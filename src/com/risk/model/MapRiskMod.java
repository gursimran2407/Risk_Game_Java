package com.risk.model;

import java.util.ArrayList;

public class MapRiskMod {
    private ArrayList<ContinentMod> continentModList;
    private ArrayList<Country> countryList;
    private ArrayList<Player> playerList;

    public MapRiskMod(ArrayList<ContinentMod> continentModList, ArrayList<Country> countryList, ArrayList<Player> playerList) {
        this.continentModList = continentModList;
        this.countryList = countryList;
        this.playerList = playerList;
    }


    public ArrayList<ContinentMod> getContinentModList() {
        return continentModList;
    }

    public void setContinentModList(ArrayList<ContinentMod> continentModList) {
        this.continentModList = continentModList;
    }

    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
}
