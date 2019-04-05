package com.risk.controller;

import com.risk.helperInterfaces.Strategy;
import com.risk.model.CardModel;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.utilities.Validation;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller handles the data flow into model object and updates the view
 * whenever data changes
 *
 * @author gursimransingh
 * @version 1.0.0
 */
public class AgressivePlayerController implements Strategy {

    /** The game play model. */
    private GamePlayModel gamePlayModel;

    /** The attacker country. */
    private CountryModel attackerCountry;

    /** The defender country. */
    private CountryModel defenderCountry;

    /** The val. */
    private Validation val = new Validation();

    /**
     * THis constructor initializes values and sets screen visibility
     * @param gamePlayModel
     */

    public AgressivePlayerController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
    }

    /**
     * This method will be called during fortification phase
     */
    @Override
    public void fortification() {
        this.gamePlayModel.getConsole().append("Agressive - fortification");
        int i = 0;
        int j = 0;
        boolean flag = false;
        List<CountryModel> linkedCountries = new ArrayList<CountryModel>();

        ArrayList<CountryModel> controlledCountries = (ArrayList<CountryModel>) gamePlayModel.getGameMap()
                .getPlayerTurn().getOwnedCountries();
        controlledCountries = gamePlayModel.sortCountry(controlledCountries);
        for (i = controlledCountries.size() - 1; i >= 0; i--) {
            linkedCountries = controlledCountries.get(i).getLinkedCountries();
            for (j = 0; j < linkedCountries.size(); j++) {
                for (int k = 0; k < controlledCountries.size(); k++) {

                    if ((linkedCountries.get(j).getArmies() > 1) && (linkedCountries.get(j).getCountryName()
                            .equals(controlledCountries.get(k).getCountryName()))) {
                        controlledCountries.get(i).setArmies(
                                controlledCountries.get(i).getArmies() + linkedCountries.get(j).getArmies() - 1);
                        linkedCountries.get(j).setArmies(1);
                        flag = true;
                    }
                    if (flag == true)
                        break;

                }
                if (flag == true)
                    break;

            }
            if (flag == true)
                break;
        }

        System.out.println("Fortifiction done");

    }

    /**
     * This method will be called during attack phase
     */
    @Override
    public void attack() {
        this.gamePlayModel.getConsole().append("Agressive - attack");
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + "'s attack");
        this.gamePlayModel.setArmyToMoveText(false);
        this.gamePlayModel.setCardToBeAssigned(false);
        gamePlayModel.alloutStrike(attackerCountry, defenderCountry);
        System.out.println("Before moving the attacker has " + attackerCountry.getArmies());
        System.out.println("Before moving The defender has " + defenderCountry.getArmies());
        if (attackerCountry.getArmies() > 1) {
            int low = 1;
            int high = attackerCountry.getArmies() - 1;
            int movingArmies = this.gamePlayModel.getRandomBetweenRange(low, high);
            gamePlayModel.moveArmies(attackerCountry, defenderCountry, movingArmies);
        }
        System.out.println("the attacker has " + attackerCountry.getArmies());
        System.out.println("The defender has " + defenderCountry.getArmies());


    }
    /**
     * This method is called during reinforcement phase
     */
    @Override
    public void reinforcement() {
            this.gamePlayModel.getConsole().append("Agressive - reinforcement");
            this.gamePlayModel.getConsole()
                    .append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
            ArrayList<CardModel> deck = new ArrayList<CardModel>();
            ArrayList<CountryModel> controlledCountries = new ArrayList<CountryModel>();
            ArrayList<CountryModel> linkedCountries = new ArrayList<CountryModel>();
            this.gamePlayModel.getConsole()
                    .append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
            CountryModel strongestCountry = new CountryModel();
            this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
                    + this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));

            controlledCountries.addAll(gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries());
            controlledCountries = gamePlayModel.descCountry(controlledCountries);
            boolean notSamePlayer = false;
            CountryModel cm = new CountryModel();
            for (int i = 0; i < controlledCountries.size(); i++) {
                strongestCountry = controlledCountries.get(i);
                ArrayList<CountryModel> linkedCountry = (ArrayList<CountryModel>) strongestCountry.getLinkedCountries();
                for (int j = 0; j < linkedCountry.size(); j++) {
                    if (!gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                            .equals(linkedCountry.get(j).getRulerName())) {
                        notSamePlayer = true;
                        defenderCountry = linkedCountry.get(j);
                        attackerCountry = strongestCountry;
                    }
                }
                if (notSamePlayer == true) {
                    break;
                }
            }

            attackerCountry
                    .setArmies(gamePlayModel.getGameMap().getPlayerTurn().getremainTroop() + attackerCountry.getArmies());
            gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(0);
            System.out.println(
                    "Attacking from " + attackerCountry.getCountryName() + " to " + defenderCountry.getCountryName());
            this.gamePlayModel.getConsole().append(
                    "Attacking from " + attackerCountry.getCountryName() + " to " + defenderCountry.getCountryName());

            }
        }

