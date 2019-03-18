package com.risk.controller;

import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.StartupView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a startup phase to assign countries and armies to the players
 * @author Namita Faujdar
 */
public class StartupController implements ActionListener {

    private StartupView startupView;
    private List<CountryModel> countriesList = new ArrayList<CountryModel>();
    private ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModel;
    private int noOfPlayers;
    private int[] noOfCountryForOwner = new int[5];
    private Color[] colorForOwner = new Color[5];
    private int[] totalArmiesPerPlayer = new int[5];
    private int[] remainingArmies = new int[5];
    private int loopValue = 0;
    private boolean armiesNull = false;
    private int initial = 0;
    private GamePlayModel gamePlayModel;
    private ArrayList<CountryModel> ownedCountry0 = new ArrayList<>();
    private ArrayList<CountryModel> ownedCountry1 = new ArrayList<>();
    private ArrayList<CountryModel> ownedCountry2 = new ArrayList<>();
    private ArrayList<CountryModel> ownedCountry3 = new ArrayList<>();
    private ArrayList<CountryModel> ownedCountry4 = new ArrayList<>();

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param gamePlayModel
     */
    public StartupController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
        noOfPlayers = this.gamePlayModel.getPlayers().size();

        allocateArmies();
        checkForOverallArmies();
        initial = 1;

        if (armiesNull == false) {
            while (this.gamePlayModel.getPlayers().get(loopValue).getRemainingNumberOfArmies() == 0) {
                loopValue++;
                if (loopValue > this.gamePlayModel.getPlayers().size()) {
                    loopValue = 0;
                    break;
                }
            }
            this.startupView = new StartupView(this.gamePlayModel, this.gamePlayModel.getPlayers().get(loopValue));
            this.gamePlayModel.getConsoleText().append(" \n Initiating Startup for "
                    + this.gamePlayModel.getPlayers().get(loopValue).getPlayerName() + "\n");
            this.startupView.setActionListener(this);
            for (int i = 0; i < noOfPlayers; i++) {
                this.gamePlayModel.getPlayers().get(i).addObserver(this.startupView);
            }
            this.gamePlayModel.getMapRiskModel().addObserver(startupView);
            this.startupView.setVisible(true);
        }
    }

    /**
     * This method allocates Player and Armies to the country to start the game play
     */
    public void allocateArmies() {

        noOfCountryForOwner[0] = 0;
        noOfCountryForOwner[1] = 0;
        noOfCountryForOwner[2] = 0;
        noOfCountryForOwner[3] = 0;
        noOfCountryForOwner[4] = 0;

        colorForOwner[0] = Color.RED;
        colorForOwner[1] = Color.BLUE;
        colorForOwner[2] = Color.GREEN;
        colorForOwner[3] = Color.YELLOW;
        colorForOwner[4] = Color.GRAY;

        int playerNumber = 0;

        for (int i = 0; i < this.gamePlayModel.getGameMap().getCountryModelList().size(); i++) {
            playerNumber = getRandomBetweenRange(1, noOfPlayers);
            System.out.println("playerNumber " + playerNumber);
            String namePlayer = this.gamePlayModel.getPlayers().get(playerNumber - 1).getPlayerName();

            this.gamePlayModel.getGameMap().getCountryModelList().get(i).setRulerName(namePlayer);
            this.gamePlayModel.getGameMap().getCountryModelList().get(i).setNumberofArmies(1);
            switch (playerNumber) {
                case 1:
                    noOfCountryForOwner[0]++;
                    ownedCountry0.add(this.gamePlayModel.getGameMap().getCountryModelList().get(i));
                    break;
                case 2:
                    noOfCountryForOwner[1]++;
                    ownedCountry1.add(this.gamePlayModel.getGameMap().getCountryModelList().get(i));
                    break;
                case 3:
                    noOfCountryForOwner[2]++;
                    ownedCountry2.add(this.gamePlayModel.getGameMap().getCountryModelList().get(i));
                    break;
                case 4:
                    noOfCountryForOwner[3]++;
                    ownedCountry3.add(this.gamePlayModel.getGameMap().getCountryModelList().get(i));
                    break;
                case 5:
                    noOfCountryForOwner[4]++;
                    ownedCountry4.add(this.gamePlayModel.getGameMap().getCountryModelList().get(i));
                    break;
                default:
                    break;
            }
            System.out.println("player " + noOfCountryForOwner[0] + " " + noOfCountryForOwner[1] + " "
                    + noOfCountryForOwner[2] + " " + noOfCountryForOwner[3]);
        }
        this.gamePlayModel.getConsoleText().append(" Countries has been allocated to players randomly" + "\n");
        for (int i = 0; i < noOfPlayers; i++) {
            if (noOfCountryForOwner[i] >= 3) {
                int tempPlayerTrop = (noOfCountryForOwner[i] / 3);
                totalArmiesPerPlayer[i] = noOfCountryForOwner[i] + (tempPlayerTrop - 1);
                remainingArmies[i] = totalArmiesPerPlayer[i] - noOfCountryForOwner[i];
            } else {
                totalArmiesPerPlayer[i] = noOfCountryForOwner[i];
            }
        }

        System.out.println("armies " + totalArmiesPerPlayer[0] + " " + totalArmiesPerPlayer[1] + " " + totalArmiesPerPlayer[2]
                + " " + totalArmiesPerPlayer[3]);

        assignPlayerModel();
    }

    /**
     * This method assign Player to PlayerModel
     */
    public void assignPlayerModel() {
        for (int i = 0; i < noOfPlayers; i++) {
            if (i == 0) {
                this.gamePlayModel.getPlayers().get(i).setPlayerCountries(ownedCountry0);
            } else if (i == 1) {
                this.gamePlayModel.getPlayers().get(i).setPlayerCountries(ownedCountry1);
            } else if (i == 2) {
                this.gamePlayModel.getPlayers().get(i).setPlayerCountries(ownedCountry2);
            } else if (i == 3) {
                this.gamePlayModel.getPlayers().get(i).setPlayerCountries(ownedCountry3);
            } else if (i == 4) {
                this.gamePlayModel.getPlayers().get(i).setPlayerCountries(ownedCountry4);
            }

            this.gamePlayModel.getPlayers().get(i).setPlayerColor(colorForOwner[i]);
            this.gamePlayModel.getPlayers().get(i).setNumberofArmies(totalArmiesPerPlayer[i]);
            this.gamePlayModel.getPlayers().get(i).setRemainingNumberOfArmies(remainingArmies[i]);
        }
        for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
            System.out.println("player Model " + this.gamePlayModel.getPlayers().get(i).getPlayerName() + " "
                    + this.gamePlayModel.getPlayers().get(i).getNumberofArmies());
        }
    }

    /**
     * This method gives the Random generation of numbers within two values
     *
     * @param min
     * @param max
     * @return
     */
    public int getRandomBetweenRange(double min, double max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.startupView.addButton)) {
            if (this.startupView.numOfArmiesComboBox.getSelectedItem() != null) {
                int selectedArmies = (int) this.startupView.numOfArmiesComboBox.getSelectedItem();
                CountryModel countryName = (CountryModel) this.startupView.countryListComboBox.getSelectedItem();
                System.out.println("selectedArmies " + selectedArmies);

                System.out.println("loopvlaue " + loopValue);
                System.out.println("playername " + this.gamePlayModel.getPlayers().get(loopValue).getPlayerName());

                this.gamePlayModel.getGameMap().remainingArmiesRobinAssign(loopValue,
                        this.gamePlayModel.getPlayers().get(loopValue).getPlayerName(), countryName, selectedArmies,
                        this.gamePlayModel.getPlayers());
                this.gamePlayModel.getConsoleText()
                        .append(this.gamePlayModel.getPlayers().get(loopValue).getPlayerName() + "'s Armies "
                                + selectedArmies + " has been added to " + countryName.getCountryName() + " \n");
            }
            loopValue++;

            if (loopValue < this.gamePlayModel.getPlayers().size()) {
                System.out.println("loopvlaue - " + loopValue);
                this.startupView.welcomeLabel
                        .setText("It's " + this.gamePlayModel.getPlayers().get(loopValue).getPlayerName() + "'s turn");
                this.gamePlayModel.getPlayers().get(loopValue).callObservers();

            } else {
                armiesNull = false;
                checkForOverallArmies();
                if (armiesNull == false) {
                    loopValue = 0;
                    System.out.println("loopvlaue -> " + loopValue);
                    this.startupView.welcomeLabel.setText(
                            "It's " + this.gamePlayModel.getPlayers().get(loopValue).getPlayerName() + "'s turn");
                    this.gamePlayModel.getPlayers().get(loopValue).callObservers();
                }
            }

        } else if (actionEvent.getSource().equals(startupView.nextButton)) {
            this.gamePlayModel.getGameMap().setIndexOfPlayer(0);
            new PlayGameController(gamePlayModel);
            this.startupView.dispose();
        }
    }

    /**
     * Check if the remaining armies allocated to each player has been reached to
     * zero.
     */
    private void checkForOverallArmies() {
        int numb = 0;
        for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
            if (this.gamePlayModel.getPlayers().get(i).getRemainingNumberOfArmies() != 0) {
                numb++;
            }
        }
        if (numb == 0) {
            this.gamePlayModel.getGameMap().setIndexOfPlayer(0);
            armiesNull = true;
            new PlayGameController(gamePlayModel);
            if (initial == 1) {
                this.startupView.dispose();
            }
        }
    }

}
