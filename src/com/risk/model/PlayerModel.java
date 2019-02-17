package com.risk.model;

import com.risk.controller.PlayerHumanController;
import com.risk.helperInterfaces.StrategyInterface;

import java.util.ArrayList;
import java.awt.Color;
import java.util.Observable;


public class PlayerModel extends Observable {
    private String playerName;
    //private String typeOfPlayer;
    private int numberofArmies;
    private Color playerColor;
    private ArrayList<CardModel> playerCards;
    private ArrayList<CountryModel> playerCountryModelList;
    private StrategyInterface strategy;
    private String playerType;


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

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void setStrategy(StrategyInterface strategyInterface) {
        strategy = strategyInterface;
    }

    public void executeReinforcement() {
        strategy.reinforcementPhase();
    }

    public void executeAttack() {
        strategy.attackPhase();
    }

    public void executeFortification() {
        strategy.fortificationPhase();
    }

    /**
     * Calling Observer.
     */
    public void callObservers() {
        setChanged();
        notifyObservers(this);

    }
}
