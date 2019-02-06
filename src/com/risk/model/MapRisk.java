package com.risk.model;

import java.util.ArrayList;
import java.util.List;

public class MapRisk {
    private ArrayList<Continent> continentList;
    private ArrayList<Country> countryList;
    private ArrayList<Player> playerList;

    public MapRisk(ArrayList<Continent> continentList, ArrayList<Country> countryList, ArrayList<Player> playerList) {
        this.continentList = continentList;
        this.countryList = countryList;
        this.playerList = playerList;
    }

    public ArrayList<Continent> getContinentList() {
        return continentList;
    }

    public void setContinentList(ArrayList<Continent> continentList) {
        this.continentList = continentList;
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
