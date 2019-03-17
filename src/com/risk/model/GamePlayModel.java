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

    private MapRiskModel mapRiskModel;
    private ArrayList<PlayerModel> players = new ArrayList<>()
    private ArrayList<CardModel> deck = new ArrayList<>();
    private int selectedComboBoxIndex;
    private int selectedAttackComboBoxIndex;
    private int selectedDefendComboBoxIndex;
    private boolean countryOwned = false;
    private boolean armyToMoveFlag;
    private boolean cardToBeAssigned;
    private StringBuilder consoleText;
    private CountryModel defeatedCountry;

    public GamePlayModel(MapRiskModel gameMap, ArrayList<PlayerModel> players, ArrayList<CardModel> deck) {
        this.mapRiskModel = gameMap;
        this.players = players;
        this.deck = deck;
        this.consoleText = new StringBuilder("Hello to the Risk Game ! ");
    }

    public GamePlayModel() {
        this.consoleText = new StringBuilder("Hello to the Risk Game ! ");
    }

    public MapRiskModel getMapRiskModel() {
        return mapRiskModel;
    }

    public void setMapRiskModel(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
    }

    public ArrayList<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerModel> players) {
        this.players = players;
    }

    public ArrayList<CardModel> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<CardModel> deck) {
        this.deck = deck;
    }

    public int getSelectedComboBoxIndex() {
        return selectedComboBoxIndex;
    }

    public void setSelectedComboBoxIndex(int selectedComboBoxIndex) {
        this.selectedComboBoxIndex = selectedComboBoxIndex;
    }

    public int getSelectedAttackComboBoxIndex() {
        return selectedAttackComboBoxIndex;
    }

    public void setSelectedAttackComboBoxIndex(int selectedAttackComboBoxIndex) {
        this.selectedAttackComboBoxIndex = selectedAttackComboBoxIndex;
    }

    public int getSelectedDefendComboBoxIndex() {
        return selectedDefendComboBoxIndex;
    }

    public void setSelectedDefendComboBoxIndex(int selectedDefendComboBoxIndex) {
        this.selectedDefendComboBoxIndex = selectedDefendComboBoxIndex;
    }

    public boolean isCountryOwned() {
        return countryOwned;
    }

    public void setCountryOwned(boolean countryOwned) {
        this.countryOwned = countryOwned;
    }

    public boolean isArmyToMoveFlag() {
        return armyToMoveFlag;
    }

    public void setArmyToMoveFlag(boolean armyToMoveFlag) {
        this.armyToMoveFlag = armyToMoveFlag;
    }

    public boolean isCardToBeAssigned() {
        return cardToBeAssigned;
    }

    public void setCardToBeAssigned(boolean cardToBeAssigned) {
        this.cardToBeAssigned = cardToBeAssigned;
    }

    public StringBuilder getConsoleText() {
        return consoleText;
    }

    public void setConsoleText(StringBuilder consoleText) {
        this.consoleText = consoleText;
    }

    public CountryModel getDefeatedCountry() {
        return defeatedCountry;
    }

    public void setDefeatedCountry(CountryModel defeatedCountry) {
        this.defeatedCountry = defeatedCountry;
    }
}
