package com.risk.controller;

import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gursimransingh
 */
public class StartUpTournamentController {


    /**
     * The game play model.
     */
    private GamePlayModel gamePlayModel;

    /**
     * The no of players.
     */
    private int noOfPlayers;

    /**
     * The no of country for ruler.
     */
    private int[] noOfCountryForRuler = new int[5];

    /**
     * The color for ruler.
     */
    private Color[] colorForRuler = new Color[5];

    /**
     * The total armies player.
     */
    private int[] totalArmiesPlayer = new int[5];

    /**
     * The remain armies.
     */
    private int[] remainArmies = new int[5];

    /**
     * The loop value.
     */
    private int loopValue = 0;

    /**
     * The armies null.
     */
    private boolean armiesNull = false;

    /**
     * The initial.
     */
    private int initial = 0;

    /**
     * The owned country 0.
     */
    private java.util.List<CountryModel> ownedCountry0 = new ArrayList<>();

    /**
     * The owned country 1.
     */
    private java.util.List<CountryModel> ownedCountry1 = new ArrayList<>();

    /**
     * The owned country 2.
     */
    private java.util.List<CountryModel> ownedCountry2 = new ArrayList<>();

    /**
     * The owned country 3.
     */
    private java.util.List<CountryModel> ownedCountry3 = new ArrayList<>();

    /**
     * The owned country 4.
     */
    private List<CountryModel> ownedCountry4 = new ArrayList<>();

    /**
     * Initialization of controller.
     */
    public StartUpTournamentController() {
    }

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayModel the game play model
     * @param noOfTurns     the no of turns
     */
    public StartUpTournamentController(GamePlayModel gamePlayModel, int noOfTurns) {

        this.gamePlayModel = gamePlayModel;
        noOfPlayers = this.gamePlayModel.getPlayers().size();

        allocateArmies();
        checkForOverallArmies();

        new GamePlayController(gamePlayModel, noOfTurns);
    }

    /**
     * This method allocates Player and Armies to the country to start the game
     * play.
     */
    public void allocateArmies() {

        noOfCountryForRuler[0] = 0;
        noOfCountryForRuler[1] = 0;
        noOfCountryForRuler[2] = 0;
        noOfCountryForRuler[3] = 0;
        noOfCountryForRuler[4] = 0;

        colorForRuler[0] = Color.RED;
        colorForRuler[1] = Color.BLUE;
        colorForRuler[2] = Color.GREEN;
        colorForRuler[3] = Color.YELLOW;
        colorForRuler[4] = Color.GRAY;

        int playerNumber = 0;

        for (int i = 0; i < this.gamePlayModel.getGameMap().getCountries().size(); i++) {
            playerNumber = getRandomBetweenRange(1, noOfPlayers);
            System.out.println("playerNumber " + playerNumber);
            String namePlayer = this.gamePlayModel.getPlayers().get(playerNumber - 1).getNamePlayer();

            this.gamePlayModel.getGameMap().getCountries().get(i).setRulerName(namePlayer);
            this.gamePlayModel.getGameMap().getCountries().get(i).setArmies(1);
            switch (playerNumber) {
                case 1:
                    noOfCountryForRuler[0]++;
                    ownedCountry0.add(this.gamePlayModel.getGameMap().getCountries().get(i));
                    break;
                case 2:
                    noOfCountryForRuler[1]++;
                    ownedCountry1.add(this.gamePlayModel.getGameMap().getCountries().get(i));
                    break;
                case 3:
                    noOfCountryForRuler[2]++;
                    ownedCountry2.add(this.gamePlayModel.getGameMap().getCountries().get(i));
                    break;
                case 4:
                    noOfCountryForRuler[3]++;
                    ownedCountry3.add(this.gamePlayModel.getGameMap().getCountries().get(i));
                    break;
                case 5:
                    noOfCountryForRuler[4]++;
                    ownedCountry4.add(this.gamePlayModel.getGameMap().getCountries().get(i));
                    break;
                default:
                    break;
            }
            System.out.println("player " + noOfCountryForRuler[0] + " " + noOfCountryForRuler[1] + " "
                    + noOfCountryForRuler[2] + " " + noOfCountryForRuler[3]);
        }
        this.gamePlayModel.getConsoleText().append(" Countries has been allocated to players randomly" + "\n");
        for (int i = 0; i < noOfPlayers; i++) {
            if (noOfCountryForRuler[i] >= 3) {
                int tempPlayerTrop = (noOfCountryForRuler[i] / 3);
                totalArmiesPlayer[i] = noOfCountryForRuler[i] + (tempPlayerTrop - 1);
                remainArmies[i] = totalArmiesPlayer[i] - noOfCountryForRuler[i];
            } else {
                totalArmiesPlayer[i] = noOfCountryForRuler[i];
            }
        }

        System.out.println("armies " + totalArmiesPlayer[0] + " " + totalArmiesPlayer[1] + " " + totalArmiesPlayer[2]
                + " " + totalArmiesPlayer[3]);

        assignPlayerModel();
    }

    /**
     * This method assign Player to PlayerModel.
     */
    public void assignPlayerModel() {
        for (int i = 0; i < noOfPlayers; i++) {
            if (i == 0) {
                this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry0);
            } else if (i == 1) {
                this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry1);
            } else if (i == 2) {
                this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry2);
            } else if (i == 3) {
                this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry3);
            } else if (i == 4) {
                this.gamePlayModel.getPlayers().get(i).setOwnedCountries(ownedCountry4);
            }

            this.gamePlayModel.getPlayers().get(i).setColor(colorForRuler[i]);
            this.gamePlayModel.getPlayers().get(i).setmyTroop(totalArmiesPlayer[i]);
            this.gamePlayModel.getPlayers().get(i).setremainTroop(remainArmies[i]);
        }
        for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
            System.out.println("player Model " + this.gamePlayModel.getPlayers().get(i).getNamePlayer() + " "
                    + this.gamePlayModel.getPlayers().get(i).getmyTroop());
        }
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
     * Check if the remaining armies allocated to each player has been reached to
     * zero.
     */
    private void checkForOverallArmies() {
        int numb = 0;
        for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
            if (this.gamePlayModel.getPlayers().get(i).getremainTroop() != 0) {
                numb++;
            }
        }
        if (numb == 0) {
            this.gamePlayModel.getGameMap().setPlayerIndex(0);
            armiesNull = true;
        } else {
            for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
                if (this.gamePlayModel.getPlayers().get(i).getremainTroop() != 0) {
                    System.out.println(this.gamePlayModel.getPlayers().get(i).getremainTroop() + " "
                            + this.gamePlayModel.getPlayers().get(i).getNamePlayer());
                    for (int j = 0; j < this.gamePlayModel.getGameMap().getCountries().size(); j++) {
                        if (this.gamePlayModel.getPlayers().get(i).getremainTroop() != 0) {
                            if (this.gamePlayModel.getPlayers().get(i).getNamePlayer()
                                    .equals(this.gamePlayModel.getGameMap().getCountries().get(j).getRulerName())) {
                                System.out.println(
                                        this.gamePlayModel.getGameMap().getCountries().get(j).getCountryName());
                                this.gamePlayModel.getGameMap().getCountries().get(j).setArmies(
                                        this.gamePlayModel.getGameMap().getCountries().get(j).getArmies() + 1);
                                this.gamePlayModel.getPlayers().get(i)
                                        .setremainTroop(this.gamePlayModel.getPlayers().get(i).getremainTroop() - 1);
                            }
                        }
                    }
                }
            }
        }
    }

}