package com.risk.model;


import com.risk.gameplayrequirements.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;

/**
 * "GamePlayModel" model basically consists of an object that consist of the current map that the game is being played upon
 * and the players that are playing the game.
 */

public class GamePlayModel extends Observable
{

    private MapRiskModel mapRiskModel;
    private ArrayList<PlayerModel> players = new ArrayList<>();
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

    public MapRiskModel getGameMap() {
        return mapRiskModel;
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

    /**
     * Gets the player.
     *
     * @param parmCountry the parm country
     * @return the player
     */
    public PlayerModel getPlayer(CountryModel parmCountry) {
        int i, j;
        for (i = 0; i < players.size(); i++) {
            for (j = 0; j < players.get(i).getPlayerCountries().size(); j++) {
                if (parmCountry.getCountryName().equals(players.get(i).getPlayerCountries().get(j).getCountryName())) {
                    return players.get(i);
                } else {
                    continue;
                }
            }
        }
        return null;

    }

    /**
     * Armies deduction.
     *
     * @param countryForDeduction the country for deduction
     * @param armiesToDeduct      the armies to deduct
     * @return the country model
     */
    public CountryModel armiesDeduction(CountryModel countryForDeduction, int armiesToDeduct) {

        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (this.mapRiskModel.getCountryModelList().get(i).getCountryName().equals(countryForDeduction.getCountryName())) {
                if (this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies() == 1) {
                    countryOwned = true;
                    this.mapRiskModel.getCountryModelList().get(i)
                            .setNumberofArmies(this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies() - armiesToDeduct);
                } else {
                    this.mapRiskModel.getCountryModelList().get(i)
                            .setNumberofArmies(this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies() - armiesToDeduct);
                }
            }
        }
        for (int i = 0; i < this.getPlayers().size(); i++) {
            if (this.getPlayers().get(i).getPlayerName().equals(countryForDeduction.getRulerName())) {
                this.getPlayers().get(i).setNumberofArmies(this.getPlayers().get(i).getRemainingNumberOfArmies() - armiesToDeduct);
            }
        }
        return countryForDeduction;
    }

    /**
     * This method gives the Random generation of numbers within two values.
     *
     * @param min the min
     * @param max the max
     * @return the random between range
     */
    public int getRandomBetweenRange(double min, double max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }


    /**
     * Single strike.
     *
     * @param attackDice    the attack dice
     * @param attackCountry the attack country
     * @param defendDice    the defend dice
     * @param defendCountry the defend country
     */
    public boolean singleStrike(int attackDice, CountryModel attackCountry, int defendDice, CountryModel defendCountry) {
        boolean returnValue = false;
        Integer[] attackDiceRoll = new Integer[attackDice];
        Integer[] defendDiceRoll = new Integer[defendDice];
        for (int i = 0; i < attackDice; i++) {
            attackDiceRoll[i] = getRandomBetweenRange(1, 6);
        }
        for (int i = 0; i < defendDice; i++) {
            defendDiceRoll[i] = getRandomBetweenRange(1, 6);
        }
        Arrays.sort(attackDiceRoll, Collections.reverseOrder());
        Arrays.sort(defendDiceRoll, Collections.reverseOrder());
        System.out.println(Arrays.toString(attackDiceRoll));
        System.out.println(Arrays.toString(defendDiceRoll));
        this.consoleText.append("\n Attack country dice " + Arrays.toString(attackDiceRoll) + " \n");
        this.consoleText.append("Defender country dice " + Arrays.toString(defendDiceRoll) + " \n");
        for (int i = 0; i < defendDice; i++) {
            if (attackDiceRoll[i] > defendDiceRoll[i]) {
                armiesDeduction(defendCountry, 1);
                returnValue = true;
            } else {
                armiesDeduction(attackCountry, 1);
                returnValue = true;
            }
        }
        if (countryOwned == true) {
            this.consoleText.append("Attacker " + attackCountry.getRulerName() + " defeated Country "
                    + defendCountry.getCountryName() + " \n");
            for (int i = 0; i < this.getPlayers().size(); i++) {
                if (this.getPlayers().get(i).getPlayerName().equals(defendCountry.getRulerName())) {
                    this.getPlayers().get(i).defend(defendCountry);
                }
                if (this.getPlayers().get(i).getNamePlayer().equals(attackCountry.getRulerName())) {
                    this.getPlayers().get(i).attacked(defendCountry);
                }
            }
            for (int i = 0; i < this.mapRiskModel.getCountries().size(); i++) {
                if (this.mapRiskModel.getCountries().get(i).getCountryName().equals(defendCountry.getCountryName())) {
                    this.mapRiskModel.getCountries().get(i).setRulerName(attackCountry.getRulerName());
                }
            }

            this.setArmyToMoveText(true);
            this.setCardToBeAssigned(true);
        }
        callObservers();
        return returnValue;
    }

    /**
     * World coverage.
     *
     * @param parmPlayer the parm player
     */
    public boolean worldCoverage(PlayerModel parmPlayer) {
        boolean returnvalue = true;
        double percentage = (parmPlayer.getOwnedCountries().size() * 100) / this.mapRiskModel.getCountries().size();
        this.getConsoleText().append("						" + " Map coverage for " + parmPlayer.getNamePlayer()
                + " is " + percentage + "% " + "\n");
        return returnvalue;
    }

    /**
     * Function to get cards from JSON file
     *
     * @return Arraylist of Card Model from Json File
     */
    public ArrayList<CardModel> getCardFromJSON() throws org.json.simple.parser.ParseException {
        try {
            JSONParser parser = new JSONParser();
            Object cards = parser.parse(new FileReader(
                    System.getProperty("user.dir") + Constants.filePathJSOn));
            JSONObject jsonObject = (JSONObject) cards;
            System.out.println("jsonObject " + jsonObject.get("cards"));
            JSONArray cardsJSON = (JSONArray) jsonObject.get("cards");

            int i = 0;
            CardModel cardModel = new CardModel();
            for (Object o : cardsJSON) {
                JSONObject card = (JSONObject) o;

                int cardId = Integer.parseInt((String) card.get("cardId"));
                System.out.println("cardId " + cardId);
                cardModel.setCardId(cardId);

                int cardValue = Integer.parseInt((String) card.get("cardValue"));
                System.out.println("cardValue " + cardValue);
                cardModel.setCardValue(cardValue);
                this.deck.add(cardModel);
            }
            //Collections.shuffle(this.deck);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.deck;
    }

    /**
     * Gets the defend country list.
     *
     * @param attackCountryName the attack country name
     * @return the defend country list
     */
    public ArrayList<CountryModel> getDefendCountryList(CountryModel attackCountryName) {
        ArrayList<CountryModel> linkedCountry;
        ArrayList<CountryModel> defenderCountryList = new ArrayList<CountryModel>();
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (this.mapRiskModel.getCountryModelList().get(i).equals(attackCountryName)) {
                linkedCountry = (ArrayList<CountryModel>) this.mapRiskModel.getCountryModelList().get(i).getConnectedCountryList();
                for (int j = 0; j < linkedCountry.size(); j++) {
                    if (!attackCountryName.getCountryOwner().equals(linkedCountry.get(j).getCountryOwner())) {
                        defenderCountryList.add(linkedCountry.get(j));
                    }
                }
            }
        }
        return defenderCountryList;
    }

    public boolean getArmyToMoveText() {
        return this.armyToMoveFlag;
    }


    /**
     * Number of countries.
     *
     * @return the int
     */
    public int numberOfCountries() {
        int numberOfCountries = 0;

        for (int i = 0; i < this.getPlayers().size(); i++) {

            if (this.getPlayers().get(i).getPlayerName().equals(this.getGameMap().getPlayerTurn().getPlayerName())) {
                numberOfCountries = this.getPlayers().get(i).getPlayerCountries().size();
            }
        }
        return reinforcementArmies(numberOfCountries);
    }


    /**
     * Reinforcement armies.
     *
     * @param numberOfCountries the number of countries
     * @return the int
     */
    public int reinforcementArmies(int numberOfCountries) {
        int reinforceArmies = 0;
        if (numberOfCountries > 3) {
            reinforceArmies = 3 + Math.round(numberOfCountries / 3);
        } else {
            reinforceArmies = 3;
        }
        if (reinforceArmies > 12) {
            reinforceArmies = 12;
        }
        return reinforceArmies;
    }



}
