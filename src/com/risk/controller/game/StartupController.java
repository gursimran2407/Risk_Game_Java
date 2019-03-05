package com.risk.controller.game;

import com.risk.Environment;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.game.IStartupView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Namita Faujdar
 */
public class StartupController {

    private final Environment environment;

    private MapRiskModel mapRiskModel;
    private IStartupView view;

    private List<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
    private List<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

    private int noOfPlayers;
    private int[] noOfCountryForRuler = new int[5];
    private String[] colorForRuler = new String[5];
    private int[] totalArmiesPlayer = new int[5];
    private int[] remainArmies = new int[5];
    private int loopValue = 0;
    private boolean armiesNull = false;
    private int initial = 0;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param listOfPlayers
     * @param gameMapModel
     */
    public StartupController(final Environment environment, List<PlayerModel> listOfPlayers, MapRiskModel gameMapModel) {
        this.environment = environment;

        this.listOfPlayers = listOfPlayers;
        mapRiskModel = gameMapModel;

        listOfCountrys = mapRiskModel.getCountryModelList();
        noOfPlayers = listOfPlayers.size();

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

            this.view = environment.getViewManager().createStartupView(mapRiskModel, this.listOfPlayers.get(loopValue));
            this.view.addTroopsListener(this::addTroops);
            this.view.addNextListener(e -> next());

            for (int i = 0; i < noOfPlayers; i++) {
                this.listOfPlayers.get(i).addObserver(this.view);
            }

            mapRiskModel.addObserver(view);
            this.view.showView();
        }
        mapRiskModel.setPlayerModelList(listOfPlayers);
    }

    private void next() {
        mapRiskModel.setIndexOfPlayer(0);
        new PlayerGameController(environment, mapRiskModel, listOfPlayers);
        this.view.hideView();
    }

    private void addTroops(final Object selectedArmiesAsItem, final CountryModel countryName) {
        if (selectedArmiesAsItem != null) {
            int selectedArmies = (int) selectedArmiesAsItem;
            System.out.println("selectedArmies " + selectedArmies);

            System.out.println("loopvlaue " + loopValue);
            System.out.println("playername " + this.listOfPlayers.get(loopValue).getPlayerName());

            mapRiskModel.remainingArmiesRobinAssign(
                    loopValue, this.listOfPlayers.get(loopValue).getPlayerName(),
                    countryName, selectedArmies, listOfPlayers);
        }
        loopValue++;

        if (loopValue < listOfPlayers.size()) {
            System.out.println("loopvlaue - " + loopValue);
            this.view.setWelcomeMessage("It's " + this.listOfPlayers.get(loopValue).getPlayerName() + "'s turn");
            this.listOfPlayers.get(loopValue).callObservers();

        } else {
            System.out.println("here");
            armiesNull = false;
            checkForOverallArmies();
            if (!armiesNull) {
                loopValue = 0;
                System.out.println("loopvlaue -> " + loopValue);
                this.view.setWelcomeMessage("It's " + this.listOfPlayers.get(loopValue).getPlayerName() + "'s turn");
                this.listOfPlayers.get(loopValue).callObservers();
            }
        }
    }

    /**
     * This method allocates Player and Armies to the country to start the game play
     */
    private void allocateArmies() {

        noOfCountryForRuler[0] = 0;
        noOfCountryForRuler[1] = 0;
        noOfCountryForRuler[2] = 0;
        noOfCountryForRuler[3] = 0;
        noOfCountryForRuler[4] = 0;

        colorForRuler[0] = "red";
        colorForRuler[1] = "blue";
        colorForRuler[2] = "green";
        colorForRuler[3] = "yellow";
        colorForRuler[4] = "grey";

        for (int i = 0; i < listOfCountrys.size(); i++) {
            int playerNumber = getRandomBetweenRange(1, noOfPlayers);
            System.out.println("playerNumber " + playerNumber);
            String namePlayer = "Player" + playerNumber;
            PlayerModel tempMyPlayers = new PlayerModel(namePlayer, 0,0, colorForRuler[playerNumber - 1]);
            listOfCountrys.get(i).setCountryOwner(tempMyPlayers);
            listOfCountrys.get(i).setNumberofArmies(1);

            switch (namePlayer) {
                case "Player1":
                    noOfCountryForRuler[0]++;
                    break;
                case "Player2":
                    noOfCountryForRuler[1]++;
                    break;
                case "Player3":
                    noOfCountryForRuler[2]++;
                    break;
                case "Player4":
                    noOfCountryForRuler[3]++;
                    break;
                case "Player5":
                    noOfCountryForRuler[4]++;
                    break;
                default:
                    break;
            }
            System.out.println(
                    "player " + noOfCountryForRuler[0] + " " + noOfCountryForRuler[1] + " " + noOfCountryForRuler[2]
                            + " " + noOfCountryForRuler[3]);
        }

        for (int i = 0; i < noOfPlayers; i++) {
            if (noOfCountryForRuler[i] >= 3) {
                int tempPlayerTrop = (noOfCountryForRuler[i] / 3);
                totalArmiesPlayer[i] = noOfCountryForRuler[i] + (tempPlayerTrop - 1);
                remainArmies[i] = totalArmiesPlayer[i] - noOfCountryForRuler[i];
            } else {
                totalArmiesPlayer[i] = noOfCountryForRuler[i];
            }
        }

        System.out.println(
                "armies " + totalArmiesPlayer[0] + " " + totalArmiesPlayer[1] + " " + totalArmiesPlayer[2]
                        + " " + totalArmiesPlayer[3]);

        assignPlayerModel();
        for (int i = 0; i < listOfCountrys.size(); i++) {
            System.out.println(listOfCountrys.get(i).getCountryName());
            System.out.println(listOfCountrys.get(i).getNumberofArmies());
            System.out.println(listOfCountrys.get(i).getNumberofArmies());
        }
    }

    /**
     * This method assign Player to PlayerModel
     */
    private void assignPlayerModel() {
        for (int i = 0; i < noOfPlayers; i++) {
            listOfPlayers.get(i).setPlayerColor(colorForRuler[i]);
            listOfPlayers.get(i).setNumberofArmies(totalArmiesPlayer[i]);
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
     * @return
     */
    private int getRandomBetweenRange(double min, double max) {
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
            new PlayerGameController(environment, mapRiskModel, listOfPlayers);
            if (initial == 1) {
                this.view.hideView();
            }
        }
    }

}
