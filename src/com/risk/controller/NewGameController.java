package com.risk.controller;

import com.risk.Environment;
import com.risk.model.*;
import com.risk.utilities.Validation;
import com.risk.view.INewGameView;
import com.risk.view.events.ViewActionEvent;
import com.risk.view.events.ViewActionListener;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.util.ArrayList;

/**
 * In NewGameController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @version 1.0.0
 *
 */

public class NewGameController implements ViewActionListener {

    /** The view. */
    private INewGameView theView;

    /** The list of players. */
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<>();

    /** The game map model. */
    private GameMapModel gameMapModel = new GameMapModel();

    /** The game play model. */
    private GamePlayModel gamePlayModel = new GamePlayModel();

    /** The no of players. */
    private int noOfPlayers;

    /** The Player name. */
    private String PlayerName = "";

    /** The Player type. */
    private String PlayerType = "";

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public NewGameController() {
        this.theView = Environment.getInstance().getViewManager().newNewGameView();
        this.theView.addActionListener(this);
        this.theView.showView();
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param event the action event
     * @see ViewActionListener
     */
    @Override
    public void actionPerformed(ViewActionEvent event) {
        if (INewGameView.ACTION_BROWSE_MAP.equals(event.getSource())) {
            try {
                gameMapModel = theView.loadGameMapModel();
                if (gameMapModel == null) {
                    return;
                }

                Validation MapValidation = new Validation();
                boolean flag1 = MapValidation.emptyLinkCountryValidation(this.gameMapModel);

                boolean flag3 = MapValidation.emptyContinentValidation(this.gameMapModel);
                boolean flag2 = MapValidation.checkInterlinkedContinent(this.gameMapModel);
                System.out.println(flag1 + " " + flag2 + " " + flag3);
                if (!(MapValidation.nonContinentValidation(this.gameMapModel))) {
                    if (!(MapValidation.emptyLinkCountryValidation(this.gameMapModel))) {
                        if (!(MapValidation.emptyContinentValidation(this.gameMapModel))) {
                            System.out.println(" All the map validations are correct");
                            theView.showMessageDialog(
                                    "File Loaded Successfully! Click Next to Play!",
                                    "Map Loaded");
                        } else {
                            System.out.println("Empty link country validation failed");
                            theView.showOptionDialog(
                                    "Empty continent validation failed",
                                    "Invalid");
                        }
                    } else {
                        System.out.println("Empty continent validation failed");
                        theView.showOptionDialog(
                                "Empty link country validation failed",
                                "Invalid");
                    }
                } else {
                    System.out.println("One of the continent is invalid");
                    theView.showOptionDialog(
                            "Map is not linked properly",
                            "Invalid");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (INewGameView.ACTION_NEXT.equals(event.getSource())) {
            noOfPlayers = theView.getNumOfPlayers();
            try {
                playerValidation();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (INewGameView.ACTION_CANCEL.equals(event.getSource())) {
            new WelcomeScreenController();
            this.theView.hideView();
        }
    }

    /**
     * Check for the player validation.
     *
     * @throws ParseException the parse exception
     */
    private void playerValidation() throws ParseException {
        if (gameMapModel.getCountries().size() > noOfPlayers) {
            System.out.println("no of players" + noOfPlayers);
            int h = 1, a = 1, b = 1, r = 1, c = 1;
            for (int i = 0; i < noOfPlayers; i++) {
                if (i == 0) {
                    PlayerType = theView.getPlayer1Type();
                    PlayerName = theView.getPlayer1Name();
                } else if (i == 1) {
                    PlayerType = theView.getPlayer2Type();
                    PlayerName = theView.getPlayer2Name();
                } else if (i == 2) {
                    PlayerType = theView.getPlayer3Type();
                    PlayerName = theView.getPlayer3Name();
                } else if (i == 3) {
                    PlayerType = theView.getPlayer4Type();
                    PlayerName = theView.getPlayer4Name();
                } else if (i == 4) {
                    PlayerType = theView.getPlayer5Type();
                    PlayerName = theView.getPlayer5Name();
                }

                System.out.println("PlayerName " + PlayerName);
                if (PlayerType == null || "".equals(PlayerType.trim())) {
                    PlayerType = "Human";
                    if (PlayerName == null || "".equals(PlayerName.trim())) {
                        PlayerName = "Human " + h;
                        h++;
                    }
                } else if ("Human".equals(PlayerType)) {
                    PlayerType = "Human";
                    if (PlayerName == null || "".equals(PlayerName.trim())) {
                        PlayerName = "Human " + h;
                        h++;
                    }
                } else if ("Aggressive".equals(PlayerType)) {
                    PlayerType = "Aggressive";
                    if (PlayerName == null || "".equals(PlayerName.trim())) {
                        PlayerName = "Aggressive " + a;
                        a++;
                    }
                } else if ("Benevolent".equals(PlayerType)) {
                    PlayerType = "Benevolent";
                    if (PlayerName == null || "".equals(PlayerName.trim())) {
                        PlayerName = "Benevolent " + b;
                        b++;
                    }
                } else if ("Random".equals(PlayerType)) {
                    PlayerType = "Random";
                    if (PlayerName == null || "".equals(PlayerName.trim())) {
                        PlayerName = "Random " + r;
                        r++;
                    }
                } else if ("Cheater".equals(PlayerType)) {
                    PlayerType = "Cheater";
                    if (PlayerName == null || "".equals(PlayerName.trim())) {
                        PlayerName = "Cheater " + c;
                        c++;
                    }
                }

                PlayerModel pm = new PlayerModel(PlayerName, PlayerType, 0, Color.WHITE, 0,
                        new ArrayList<CountryModel>(), new ArrayList<CardModel>());
                listOfPlayers.add(pm);
            }

            gamePlayModel.setGameMap(gameMapModel);
            gamePlayModel.setPlayers(listOfPlayers);
            gamePlayModel.setCards(gamePlayModel.getCardFromJSON());
            new StartupController(gamePlayModel);
            this.theView.hideView();
        } else {
            theView.showMessageDialog(
                    "Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
                    "Map Loaded");
        }
    }

}
