package com.risk.model;


import java.util.ArrayList;
import java.util.Observable;
/**
 * "GamePlayModel" model basically consists of an object that consist of the current map that the game is being played upon
 * and the players that are playing the game.
 */

public class GamePlayModel extends Observable
{
    /** MapRiskmodel
     */
    private MapRiskModel mapRiskModelModObj;
    //private GameMapModel gameMapModel =  new GameMapModel() ;

    /** current players playing the game*/
    private ArrayList<PlayerModel> currentPlayerModels;

    /** All the cards*/
    private ArrayList<CardModel> deckOfCards;

    private StringBuilder consoleOutput;

    /** if country owned*/
    private boolean ifCountryOwned;

    private String PhaseOfGame = null;

    private ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();

    /**
     * Default Constructor.
     */
    public GamePlayModel(MapRiskModel mapRiskModelModObj, ArrayList<PlayerModel> currentPlayerModels, ArrayList<CardModel> deckOfCards, boolean ifCountryOwned)
    {
        this.mapRiskModelModObj = mapRiskModelModObj;
        this.currentPlayerModels = currentPlayerModels;
        this.deckOfCards = deckOfCards;
        this.ifCountryOwned = ifCountryOwned;
    }

    public MapRiskModel getMapRiskModelModObj() {
        return mapRiskModelModObj;
    }

    public void setMapRiskModelModObj(MapRiskModel mapRiskModelModObj) {
        this.mapRiskModelModObj = mapRiskModelModObj;
    }

    public ArrayList<PlayerModel> getCurrentPlayerModels() {
        return currentPlayerModels;
    }

    public void setCurrentPlayerModels(ArrayList<PlayerModel> currentPlayerModels) {
        this.currentPlayerModels = currentPlayerModels;
    }

    public ArrayList<CardModel> getDeckOfCards() {
        return deckOfCards;
    }

    public void setDeckOfCards(ArrayList<CardModel> deckOfCards) {
        this.deckOfCards = deckOfCards;
    }

    public boolean isIfCountryOwned() {
        return ifCountryOwned;
    }

    public void setIfCountryOwned(boolean ifCountryOwned) {
        this.ifCountryOwned = ifCountryOwned;
    }

    public String getPhaseOfGame() {
        return PhaseOfGame;
    }

    public void setPhaseOfGame(String phaseOfGame) {
        PhaseOfGame = phaseOfGame;
    }

    public ArrayList<PlayerModel> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<PlayerModel> playersList) {
        this.playersList = playersList;
    }

    public StringBuilder getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(StringBuilder consoleOutput) {
        this.consoleOutput = consoleOutput;
    }
}
