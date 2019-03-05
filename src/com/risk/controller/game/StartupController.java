package com.risk.controller.game;

import com.risk.controller.game.PlayerGameController;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.StartupView;

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
    private String[] colorForOwner = new String[5];
    private int[] totalArmiesPerPlayer = new int[5];
    private int[] remainingArmies = new int[5];
    private int loopValue = 0;
    private boolean armiesNull = false;
    private int initial = 0;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param new_playersList
     * @param new_mapRiskModel
     */
    public StartupController(ArrayList<PlayerModel> new_playersList, MapRiskModel new_mapRiskModel) {

        this.playersList = new_playersList;
        mapRiskModel = new_mapRiskModel;

        countriesList = mapRiskModel.getCountryModelList();
        noOfPlayers = new_playersList.size();

        allocateArmies();
        checkForOverallArmies();
        initial = 1;

        if (!armiesNull) {
            while (new_playersList.get(loopValue).getRemainingNumberOfArmies() == 0) {
                loopValue++;
                if (loopValue > new_playersList.size()) {
                    loopValue = 0;
                    break;
                }
            }
            this.startupView = new StartupView(mapRiskModel, this.playersList.get(loopValue));
            this.startupView.setActionListener(this);
            for (int i = 0; i < noOfPlayers; i++) {
                this.playersList.get(i).addObserver(this.startupView);
            }
            mapRiskModel.addObserver(startupView);
            this.startupView.setVisible(true);
        }
        mapRiskModel.setPlayerModelList(new_playersList);
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

        colorForOwner[0] = "red";
        colorForOwner[1] = "blue";
        colorForOwner[2] = "green";
        colorForOwner[3] = "yellow";
        colorForOwner[4] = "brown";

        for (int i = 0; i < countriesList.size(); i++) {
            int playerNumber = getRandomBetweenRange(1, noOfPlayers);
            System.out.println("playerNumber " + playerNumber);
            String namePlayer = "Player" + playerNumber;
            PlayerModel tempMyPlayers = new PlayerModel(namePlayer, 0,0, colorForOwner[playerNumber - 1]);
            countriesList.get(i).setCountryOwner(tempMyPlayers);
            countriesList.get(i).setNumberofArmies(1);
            switch (namePlayer) {
                case "Player1":
                    noOfCountryForOwner[0]++;
                    break;
                case "Player2":
                    noOfCountryForOwner[1]++;
                    break;
                case "Player3":
                    noOfCountryForOwner[2]++;
                    break;
                case "Player4":
                    noOfCountryForOwner[3]++;
                    break;
                case "Player5":
                    noOfCountryForOwner[4]++;
                    break;
                default:
                    break;
            }
            System.out.println("player " + noOfCountryForOwner[0] + " " + noOfCountryForOwner[1] + " "
                    + noOfCountryForOwner[2] + " " + noOfCountryForOwner[3]);
        }

        for (int i = 0; i < noOfPlayers; i++) {
            if (noOfCountryForOwner[i] >= 3) {
                int tempPlayerArmies = (noOfCountryForOwner[i] / 3);
                totalArmiesPerPlayer[i] = noOfCountryForOwner[i] + (tempPlayerArmies - 1);
                remainingArmies[i] = totalArmiesPerPlayer[i] - noOfCountryForOwner[i];
            } else {
                totalArmiesPerPlayer[i] = noOfCountryForOwner[i];
            }
        }

        System.out.println("armies " + totalArmiesPerPlayer[0] + " " + totalArmiesPerPlayer[1] + " " + totalArmiesPerPlayer[2]
                + " " + totalArmiesPerPlayer[3]);

        assignPlayerModel();
        for (int i = 0; i < countriesList.size(); i++) {
            System.out.println(countriesList.get(i).getCountryName());
            System.out.println(countriesList.get(i).getNumberofArmies());
            System.out.println(countriesList.get(i).getNumberofArmies());
        }
    }

    /**
     * This method assign Player to PlayerModel
     */
    public void assignPlayerModel() {
        for (int i = 0; i < noOfPlayers; i++) {
            playersList.get(i).setPlayerColor(colorForOwner[i]);
            playersList.get(i).setNumberofArmies(totalArmiesPerPlayer[i]);
            playersList.get(i).setRemainingNumberOfArmies(remainingArmies[i]);
        }
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println(
                    "player Model " + playersList.get(i).getPlayerName() + " " + playersList.get(i).getNumberofArmies());
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
                System.out.println("playername " + this.playersList.get(loopValue).getPlayerName());

                mapRiskModel.remainingArmiesRobinAssign(loopValue, this.playersList.get(loopValue).getPlayerName(),
                        countryName, selectedArmies, playersList);
            }
            loopValue++;

            if (loopValue < playersList.size()) {
                System.out.println("loopvlaue - " + loopValue);
                this.startupView.welcomeLabel
                        .setText("It's " + this.playersList.get(loopValue).getPlayerName() + "'s turn");
                this.playersList.get(loopValue).callObservers();

            } else {
                System.out.println("here");
                armiesNull = false;
                checkForOverallArmies();
                if (armiesNull == false) {
                    loopValue = 0;
                    System.out.println("loopvlaue -> " + loopValue);
                    this.startupView.welcomeLabel
                            .setText("It's " + this.playersList.get(loopValue).getPlayerName() + "'s turn");
                    this.playersList.get(loopValue).callObservers();
                }
            }

        } else if (actionEvent.getSource().equals(startupView.nextButton)) {
            mapRiskModel.setIndexOfPlayer(0);
            new PlayerGameController(mapRiskModel, playersList);
            this.startupView.dispose();
        }
    }

    /**
     * Check if the remaining armies allocated to each player has been reached to
     * zero.
     */
    private void checkForOverallArmies() {
        int numb = 0;
        for (int i = 0; i < playersList.size(); i++) {
            if (playersList.get(i).getRemainingNumberOfArmies() != 0) {
                numb++;
            }
        }
        if (numb == 0) {
            mapRiskModel.setIndexOfPlayer(0);
            armiesNull = true;
            new PlayerGameController(mapRiskModel, playersList);
            if (initial == 1) {
                this.startupView.dispose();
            }
        }
    }

}
