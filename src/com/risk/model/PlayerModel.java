package com.risk.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class PlayerModel extends Observable {
    private String playerName;
    private int numberofArmies;
    private int  remainingNumberOfArmies;
    private Color playerColor;
    private int myPlayer;
    private ArrayList<CountryModel> playerCountries  = new ArrayList<CountryModel>();
    private ArrayList<CardModel> playerCards;


    public PlayerModel(String playerName, int numberOfArmies, int remainingNumberOfArmies, Color playerColor,
                       ArrayList playerCountries, ArrayList playerCards) {
        this.playerName = playerName;
        this.remainingNumberOfArmies = remainingNumberOfArmies;
        this.numberofArmies = numberOfArmies;
        this.playerColor = playerColor;
        this.playerCountries = playerCountries;
        this.playerCards = playerCards;
    }

    public PlayerModel() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getmyPlayer() {
        return myPlayer;
    }

    /**
     * Sets the value Control.
     *
     * @param myPlayer
     */
    public void setmyPlayer(int myPlayer) {
        this.myPlayer = myPlayer;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNumberofArmies() {
        return numberofArmies;
    }

    public void setNumberofArmies(int numberofArmies) {
        this.numberofArmies = numberofArmies;
    }

    public int getRemainingNumberOfArmies() {
        return remainingNumberOfArmies;
    }

    public void setRemainingNumberOfArmies(int remainingNumberOfArmies) {
        this.remainingNumberOfArmies = remainingNumberOfArmies;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    public ArrayList<CountryModel> getPlayerCountries() {
        return playerCountries;
    }

    public void setPlayerCountries(ArrayList<CountryModel> playerCountries) {
        this.playerCountries = playerCountries;
    }

    public ArrayList<CardModel> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<CardModel> playerCards) {
        this.playerCards = playerCards;
    }

    /**
     * To show reinforcement card
     */
    public boolean showReinforcement;


    /**
     * Calling Observer.
     */
    public void callObservers() {
        setChanged();
        notifyObservers(this);

    }

    /**
     * Defend.
     *
     * @param countryForDeduction the country for deduction
     * @return true, if successful
     */
    public boolean defend(CountryModel countryForDeduction) {
        for (int i = 0; i < this.playerCountries.size(); i++) {
            if (this.playerCountries.get(i).getCountryName().equals(countryForDeduction.getCountryName())) {
                this.playerCountries.remove(i);
            }
        }
        return true;
    }

    /**
     * Sets for displaying reinforcement card
     */
    public void setReinforcementCard(boolean showReinforcement)
    {
        this.showReinforcement = showReinforcement;
    }
}
