package com.risk.controller;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.StartupView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Namita Faujdar
 */
public class StartupController implements ActionListener {
    private StartupView startUpView;
    private List<CountryModel> countryList;
    private ArrayList<PlayerModel> listOfPlayers;
    private MapRiskModel mapRiskModel;
    private int numberOfPlayers;
    private int[] numberOfCountriesPerPlayer = new int[5];
    private String[] colorForRuler = new String[5];
    private int[] numberOfArmiesPerPlayer = new int[5];
    private int[] remainArmies = new int[5];
    private int loopValue = 0;
    private boolean armiesNull = false;
    private int initial = 0;


    public StartupController(ArrayList<PlayerModel> new_listOfPlayers, MapRiskModel new_mapRiskModel) {
        listOfPlayers = new_listOfPlayers;
        mapRiskModel = new_mapRiskModel;

        countryList = mapRiskModel.getCountryModelList();
        numberOfPlayers = listOfPlayers.size();

        allocateArmies();
        checkForOverallArmies();
        initial = 1;

        if (!armiesNull) {
            while (listOfPlayers.get(loopValue).getRemainingNumberOfArmies() == 0) {
                loopValue++;
                if (loopValue > listOfPlayers.size()) {
                    loopValue = 0;
                    break;
                }
            }
            startUpView = new StartupView(mapRiskModel, listOfPlayers.get(loopValue));
            startUpView.setActionListener(this);

            for (int i = 0; i < numberOfPlayers; i++) {
                listOfPlayers.get(i).addObserver(startUpView);
            }
            mapRiskModel.addObserver(startUpView);
            startUpView.setVisible(true);
        }
        mapRiskModel.setPlayerModelList(listOfPlayers);

    }
    /**
     * This method allocates Player and Armies to the country to start the game play
     */

    public void allocateArmies() {

        numberOfCountriesPerPlayer[0] = 0;
        numberOfCountriesPerPlayer[1] = 0;
        numberOfCountriesPerPlayer[2] = 0;
        numberOfCountriesPerPlayer[3] = 0;
        numberOfCountriesPerPlayer[4] = 0;

        colorForRuler[0] = "green";
        colorForRuler[1] = "grey";
        colorForRuler[2] = "yellow";
        colorForRuler[3] = "blue";
        colorForRuler[4] = "red";

        for (int i = 0; i < countryList.size(); i++) {
            //Initializing players randomly
            int playerNumber = getRandomBetweenRange(1, numberOfPlayers);
            System.out.println("playerNumber " + playerNumber);
            String PlayerName = "Player" + playerNumber;
            PlayerModel tempMyPlayers = new PlayerModel(PlayerName, 0, 0, colorForRuler[playerNumber - 1]);
            countryList.get(i).setCountryOwner(tempMyPlayers);
            countryList.get(i).setNumberofArmies(1);

            switch (PlayerName) {
                case "Player1":
                    numberOfCountriesPerPlayer[0]++;
                    break;
                case "Player2":
                    numberOfCountriesPerPlayer[1]++;
                    break;
                case "Player3":
                    numberOfCountriesPerPlayer[2]++;
                    break;
                case "Player4":
                    numberOfCountriesPerPlayer[3]++;
                    break;
                case "Player5":
                    numberOfCountriesPerPlayer[4]++;
                    break;
                default:
                    break;
            }
            System.out.println("player " + numberOfCountriesPerPlayer[0] + " " + numberOfCountriesPerPlayer[1] + " "
                    + numberOfCountriesPerPlayer[2] + " " + numberOfCountriesPerPlayer[3]);
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            if (numberOfCountriesPerPlayer[i] >= 3) {
                int tempPlayerTrop = (numberOfCountriesPerPlayer[i] / 3);
                numberOfArmiesPerPlayer[i] = numberOfCountriesPerPlayer[i] + (tempPlayerTrop - 1);
                remainArmies[i] = numberOfArmiesPerPlayer[i] - numberOfCountriesPerPlayer[i];
            } else {
                numberOfArmiesPerPlayer[i] = numberOfCountriesPerPlayer[i];
            }
        }

        System.out.println("armies " + numberOfArmiesPerPlayer[0] + " " + numberOfArmiesPerPlayer[1] + " " + numberOfArmiesPerPlayer[2]
                + " " + numberOfArmiesPerPlayer[3]);

        assignPlayerModel();
        for (int i = 0; i < countryList.size(); i++) {
            System.out.println(countryList.get(i).getCountryName());
            System.out.println(countryList.get(i).getCountryOwner());
            System.out.println(countryList.get(i).getNumberofArmies());
        }
    }
    /**
     * Method assigns Player to PlayerModel
     */
    public void assignPlayerModel() {
        for (int i = 0; i < numberOfPlayers; i++) {
            listOfPlayers.get(i).setPlayerColor(colorForRuler[i]);
            listOfPlayers.get(i).setNumberofArmies(numberOfArmiesPerPlayer[i]);
            listOfPlayers.get(i).setRemainingNumberOfArmies(remainArmies[i]);
        }
        for (int i = 0; i < listOfPlayers.size(); i++) {
            System.out.println(
                    "player Model " + listOfPlayers.get(i).getPlayerName() + " " + listOfPlayers.get(i).getNumberofArmies());
        }
    }

/**
 * This method gives the Random generation of numbers within two values
 *
 * @param min
 * @param max
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
        for (int i = 0; i < listOfPlayers.size(); i++) {
            if (listOfPlayers.get(i).getRemainingNumberOfArmies() != 0) {
                numb++;
            }
        }
        if (numb == 0) {
            mapRiskModel.setIndexOfPlayer(0);
            armiesNull = true;
            new PlayerGameController(mapRiskModel, listOfPlayers);
            if (initial == 1) {
                this.startUpView.dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(startUpView.addButton)) {
            if (startUpView.numOfTroopsComboBox.getSelectedItem() != null) {
                int selectedArmies = (int) startUpView.numOfTroopsComboBox.getSelectedItem();
                CountryModel countryName = (CountryModel) startUpView.countryListComboBox.getSelectedItem();
                System.out.println("selectedArmies " + selectedArmies);

                System.out.println("loopValue " + loopValue);
                System.out.println("playerName " + this.listOfPlayers.get(loopValue).getPlayerName());

                mapRiskModel.remainingArmiesRobinAssign(loopValue, this.listOfPlayers.get(loopValue).getPlayerName(),
                        countryName, selectedArmies, listOfPlayers);
            }
            loopValue++;

            if (loopValue < listOfPlayers.size()) {
                System.out.println("loopValue - " + loopValue);
                startUpView.welcomeLabel
                        .setText("It's " + this.listOfPlayers.get(loopValue).getPlayerName() + "'s turn");
                this.listOfPlayers.get(loopValue).callObservers();

            } else {
                System.out.println("here");
                armiesNull = false;
                checkForOverallArmies();
                if (armiesNull) {
                    loopValue = 0;
                    System.out.println("loopValue -> " + loopValue);
                    startUpView.welcomeLabel
                            .setText("It's " + this.listOfPlayers.get(loopValue).getPlayerName() + "'s turn");
                    this.listOfPlayers.get(loopValue).callObservers();
                }
            }

        } else if (e.getSource().equals(startUpView.nextButton)) {
            mapRiskModel.setIndexOfPlayer(0);
            new PlayerGameController(mapRiskModel, listOfPlayers);
            startUpView.dispose();
        }
    }
}
