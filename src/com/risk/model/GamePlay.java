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
    private MapRisk mapRiskObj = new MapRisk();

    /** current players playing the game*/
    private ArrayList<Player> currentPlayers = new ArrayList<Player>();

    /** All the cards*/
    private ArrayList<Card> deckOfCards = new ArrayList<Card>();

    /** if country owned*/
    private boolean ifCountryOwned = false;

    /**
     * Default Constructor.
     */
    public GamePlay(MapRisk mapRiskObj, ArrayList<Player> currentPlayers, ArrayList<Card> deckOfCards, boolean ifCountryOwned)
    {
        this.mapRiskObj = mapRiskObj;
        this.currentPlayers = currentPlayers;
        this.deckOfCards = deckOfCards;
        this.ifCountryOwned = ifCountryOwned;
    }


}
