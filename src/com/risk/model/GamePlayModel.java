package com.risk.model;


import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

/**
 * "GamePlayModel" model basically consists of an object that consist of the current map that the game is being played upon
 * and the players that are playing the game.
 */

public class GamePlayModel extends Observable
{
    /** MapRiskModel
     */
    private MapRiskModel mapRiskModelModObj;
    //private GameMapModel gameMapModel =  new GameMapModel() ;

    /** current players playing the game*/
    private ArrayList<PlayerModel> currentPlayerModels;

    private StringBuilder consoleOutput;

    /** if country owned*/
    private boolean ifCountryOwned;

    private String PhaseOfGame = null;

    private ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();

    /**
     * Default Constructor.
     */
    public GamePlayModel(MapRiskModel mapRiskModelModObj, ArrayList<PlayerModel> currentPlayerModels, boolean ifCountryOwned)
    {
        this.mapRiskModelModObj = mapRiskModelModObj;
        this.currentPlayerModels = currentPlayerModels;
        this.ifCountryOwned = ifCountryOwned;
    }

    public GamePlayModel(File file) {
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
