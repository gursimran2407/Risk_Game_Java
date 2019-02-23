package com.risk.model;

import com.risk.controller.PlayerHumanController;
import com.risk.helperInterfaces.StrategyInterface;

import java.util.ArrayList;
import java.awt.Color;
import java.util.Observable;


public class PlayerModel extends Observable {
    private String playerName;
    private int numberofArmies;
    private int  remainingNumberOfArmies;
    private String playerColor;


    public PlayerModel(String playerName, int numberOfArmies ,int remainingNumberOfArmies, String playerColor) {
        this.playerName = playerName;
        this.remainingNumberOfArmies = remainingNumberOfArmies;
        //this.typeOfPlayer = typeOfPlayer;
        this.numberofArmies = numberOfArmies;
        this.playerColor = playerColor;

    }

    public String getPlayerName() {
        return playerName;
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

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Calling Observer.
     */
    public void callObservers() {
        setChanged();
        notifyObservers(this);

    }
}
