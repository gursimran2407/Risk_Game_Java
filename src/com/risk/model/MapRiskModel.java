package com.risk.model;

import com.risk.gameplayrequirements.ReadMapFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

public class MapRiskModel extends Observable {

    private ArrayList<ContinentModel> d_continentModelList;
    private ArrayList<CountryModel> d_countryModelList;
    private ArrayList<PlayerModel> d_playerModelList;
    private int d_indexOfPlayer;
    private PlayerModel d_playerTurn;

    public MapRiskModel(ArrayList<ContinentModel> new_continentModelModList, ArrayList<CountryModel> new_countryModelList, ArrayList<PlayerModel> new_playerModelList) {
        d_continentModelList = new_continentModelModList;
        d_countryModelList = new_countryModelList;
        d_playerModelList = new_playerModelList;
    }

    /**
     * Default constructor to make new map
     */
    public MapRiskModel() {
        d_continentModelList = new ArrayList<>();
        d_countryModelList = new ArrayList<>();
    }

    /**
     * This constructor helps to edit the map
     *
     * @param file to edit
     */
    public MapRiskModel(File file) {
        ReadMapFile readMapFile = new ReadMapFile();
        try {
            readMapFile.setMapFile(file);
            d_continentModelList = readMapFile.getMapContinentDetails();
            //countryModelList = readMapFile.getMapCountryDetails();
            //this.countriesInContinent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ContinentModel> getContinentModelList() {
        return d_continentModelList;
    }

    public void setContinentModelModList(ArrayList<ContinentModel> continentModelList) {
        d_continentModelList = continentModelList;
        callObservers();
    }

    public ArrayList<CountryModel> getCountryModelList() {
        return d_countryModelList;
    }

    public void setCountryModelList(ArrayList<CountryModel> countryModelList) {
        d_countryModelList = countryModelList;
        callObservers();
    }

    public ArrayList<PlayerModel> getPlayerModelList() {
        return d_playerModelList;
    }

    public void setPlayerModelList(ArrayList<PlayerModel> playerModelList) {
        d_playerModelList = playerModelList;
        callObservers();
    }

    public PlayerModel getPlayerTurn() {
        return d_playerTurn;
    }

    public void setPlayerTurn(PlayerModel playerTurn) {
        d_playerTurn = playerTurn;
        callObservers();
    }

    public int getIndexOfPlayer() {
        return d_indexOfPlayer;
    }

    public void setIndexOfPlayer(int indexOfPlayer) {
        d_indexOfPlayer = indexOfPlayer;
        callObservers();
    }

    public void callObservers() {
        setChanged();
        notifyObservers(this);
    }
}
