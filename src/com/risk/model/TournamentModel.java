package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author gursimransingh
 */
public class TournamentModel extends Observable {
    /**
     * The game Play model.
     */
    private ArrayList<GamePlayModel> gamePlayModels = new ArrayList<GamePlayModel>();

    /**
     * The no of games.
     */
    private int noOfGames;

    /**
     * The no of turns.
     */
    private int noOfTurns;

    public TournamentModel(ArrayList<GamePlayModel> gamePlayModels, int noOfGames, int noOfTurns) {
        this.gamePlayModels = gamePlayModels;
        this.noOfGames = noOfGames;
        this.noOfTurns = noOfTurns;
    }


    public TournamentModel() {
    }

    public ArrayList<GamePlayModel> getGamePlayModels() {
        return gamePlayModels;
    }

    public void setGamePlayModels(ArrayList<GamePlayModel> gamePlayModels) {
        this.gamePlayModels = gamePlayModels;
    }

    public int getNoOfGames() {
        return noOfGames;
    }

    public void setNoOfGames(int noOfGames) {
        this.noOfGames = noOfGames;
    }

    public int getNoOfTurns() {
        return noOfTurns;
    }

    public void setNoOfTurns(int noOfTurns) {
        this.noOfTurns = noOfTurns;
    }

    public void callObservers() {
        setChanged();
        notifyObservers(this);
    }

}
