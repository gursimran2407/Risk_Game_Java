package com.risk.model;

import java.util.ArrayList;
import java.awt.Color;


public class PlayerModel {
    private String playerName;
    //private String typeOfPlayer;
    private int numberofArmies;
    private Color playerColor;
    private ArrayList<CardModel> playerCards;
    private ArrayList<CountryModel> playerCountryModelList;


    public PlayerModel(String playerName, ArrayList<CountryModel> playerCountryModelList, int numberofArmies, Color playerColor, ArrayList<CardModel> playerCards) {
        this.playerName = playerName;
        //this.typeOfPlayer = typeOfPlayer;
        this.playerCountryModelList = playerCountryModelList;
        this.numberofArmies = numberofArmies;
        this.playerColor = playerColor;
        this.playerCards = playerCards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<CountryModel> getPlayerCountryModelList() {
        return playerCountryModelList;
    }

    public void setPlayerCountryModelList(ArrayList<CountryModel> playerCountryModelList) {
        this.playerCountryModelList = playerCountryModelList;
    }

    public int getNumberofArmies() {
        return numberofArmies;
    }

    public void setNumberofArmies(int numberofArmies) {
        this.numberofArmies = numberofArmies;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public ArrayList<CardModel> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<CardModel> playerCards) {
        this.playerCards = playerCards;
    }
}
