package com.risk.model;

import java.util.ArrayList;
import java.awt.Color;


public class Player {
    private String playerName;
    //private String typeOfPlayer;
    private int numberofArmies;
    private Color playerColor;
    private ArrayList<Card> playerCards;
    private ArrayList<Country> playerCountryList;



    public Player(String playerName, ArrayList<Country> playerCountryList, int numberofArmies, Color playerColor, ArrayList<Card> playerCards) {
        this.playerName = playerName;
        //this.typeOfPlayer = typeOfPlayer;
        this.playerCountryList = playerCountryList;
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

    public ArrayList<Country> getPlayerCountryList() {
        return playerCountryList;
    }

    public void setPlayerCountryList(ArrayList<Country> playerCountryList) {
        this.playerCountryList = playerCountryList;
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

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }
}
