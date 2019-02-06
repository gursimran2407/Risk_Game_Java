package com.risk.model;


import java.util.ArrayList;
import java.util.Observable;
/**
 * "GamePlay" model basically consists of an object that consist of the current map that the game is being played upon
 * and the players that are playing the game.
 */

public class GamePlay extends Observable
{
    /** MapRiskmodel
     */
    private MapRiskMod mapRiskModObj;

    /** current players playing the game*/
    private ArrayList<Player> currentPlayers;

    /** All the cards*/
    private ArrayList<Card> deckOfCards;

    /** if country owned*/
    private boolean ifCountryOwned;

    private String PhaseOfGame = null;

    /**
     * Default Constructor.
     */
    public GamePlay(MapRiskMod mapRiskModObj, ArrayList<Player> currentPlayers, ArrayList<Card> deckOfCards, boolean ifCountryOwned)
    {
        this.mapRiskModObj = mapRiskModObj;
        this.currentPlayers = currentPlayers;
        this.deckOfCards = deckOfCards;
        this.ifCountryOwned = ifCountryOwned;
    }

    public MapRiskMod getMapRiskModObj() {
        return mapRiskModObj;
    }

    public void setMapRiskModObj(MapRiskMod mapRiskModObj) {
        this.mapRiskModObj = mapRiskModObj;
    }

    public ArrayList<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(ArrayList<Player> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public void setDeckOfCards(ArrayList<Card> deckOfCards) {
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
}
