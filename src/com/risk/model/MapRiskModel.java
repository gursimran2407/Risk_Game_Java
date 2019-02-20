package com.risk.model;

import com.risk.gameplayrequirements.MapRead;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MapRiskModel extends Observable {

    private ArrayList<ContinentModel> d_continentModelList;
    private ArrayList<CountryModel> d_countryModelList;
    private ArrayList<PlayerModel> d_playerModelList;
    private int d_indexOfPlayer;
    private PlayerModel d_playerTurn;
    private int leftModelIndex = 0;
    private int rightModelIndex = 0;

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
        MapRead readMapFile = new MapRead();
        try {
            readMapFile.setReadFile(file);
            d_continentModelList = readMapFile.getMapContinentDetails();
            d_countryModelList = readMapFile.getMapCountryDetails();
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

    /**
     * @return the leftModelIndex to get
     */
    public int getLeftModelIndex() {
        return leftModelIndex;
    }

    /**
     * @param leftModelIndex to set
     */
    public void setLeftModelIndex(int leftModelIndex) {
        this.leftModelIndex = leftModelIndex;
    }

    /**
     * @return the rightModelIndex to get
     */
    public int getRightModelIndex() {
        return rightModelIndex;
    }

    /**
     * @param rightModelIndex to set
     */
    public void setRightModelIndex(int rightModelIndex) {
        this.rightModelIndex = rightModelIndex;
    }

    public void callObservers() {
        setChanged();
        notifyObservers(this);
    }

    public void setCountryColor(CountryModel country, Color color) {
        for (int i = 0; i < this.d_countryModelList.size(); i++) {
            if (this.d_countryModelList.get(i).equals(country)) {
                this.d_countryModelList.get(i).setBackgroundColor(color);
            }
        }
        callObservers();
        System.out.println(this.d_countryModelList);
    }

    public boolean setNeighbouringCountry(CountryModel leftModel, CountryModel rightModel) {
        for (int i = 0; i < getCountryModelList().size(); i++) {
            if (getCountryModelList().get(i).equals(leftModel)) {
                ArrayList<CountryModel> temp = getCountryModelList().get(i).getConnectedCountryList();
                if (temp == null) {
                    temp = new ArrayList<>();
                }
                temp.add((CountryModel) rightModel);
                getCountryModelList().get(i).setConnectedCountryList(temp);
                this.setLeftModelIndex(i);
            } else if (getCountryModelList().get(i).equals(rightModel)) {
                ArrayList<CountryModel> temp = getCountryModelList().get(i).getConnectedCountryList();
                if (temp == null) {
                    temp = new ArrayList<CountryModel>();
                }
                temp.add((CountryModel) leftModel);
                getCountryModelList().get(i).setConnectedCountryList(temp);
                this.setRightModelIndex(i);
            }
        }
        callObservers();
        return true;
    }
    
    public void removeNeighbouringCountry(CountryModel leftModelCountry, CountryModel rightModelCountry) {

        for (int i = 0; i < getCountryModelList().size(); i++) {
            if (getCountryModelList().get(i).equals(leftModelCountry)) {
                java.util.ArrayList<CountryModel> temp = getCountryModelList().get(i).getConnectedCountryList();
                if (temp == null) {
                    temp = new ArrayList<>();
                }
                temp.remove( rightModelCountry);
                this.getCountryModelList().get(i).setConnectedCountryList(temp);
                this.setLeftModelIndex(i);
            } else if (this.getCountryModelList().get(i).equals(rightModelCountry)) {
                ArrayList<CountryModel> temp = this.getCountryModelList().get(i).getConnectedCountryList();
                if (temp == null) {
                    temp = new ArrayList<>();
                }
                temp.remove(leftModelCountry);
                this.getCountryModelList().get(i).setConnectedCountryList(temp);
                this.setRightModelIndex(i);
            }
        }
        callObservers();
    }
}
