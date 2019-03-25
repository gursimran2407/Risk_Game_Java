package com.risk.controller;

import com.risk.model.*;
import com.risk.utilities.Validation;
import com.risk.view.NewGameView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
/**
 * similar to all other controllers in the project, the NewGameController also takes care of the movement of
 * data into the model corresponding to the view and the controller and also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Namita
 */
public class NewGameController implements ActionListener {

    /** The view. */
    private NewGameView theView;

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

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public NewGameController() {
        this.theView = new NewGameView();
        this.theView.setActionListener(this);
        this.theView.setVisible(true);

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(theView.browseMapButton)) {
            int value = theView.chooseMap.showOpenDialog(theView);
            if (value == JFileChooser.APPROVE_OPTION) {
                try {
                    File mapFile = theView.chooseMap.getSelectedFile();
                    gameMapModel = new GameMapModel(mapFile);

                    Validation MapValidation = new Validation();
                    boolean flag1 = MapValidation.emptyLinkCountryValidation(this.gameMapModel);

                    boolean flag3 = MapValidation.emptyContinentValidation(this.gameMapModel);
                    boolean flag2 = MapValidation.checkInterlinkedContinent(this.gameMapModel);
                    System.out.println(flag1 + " " + flag2 + " " + flag3);
                    if (!(MapValidation.emptyLinkCountryValidation(this.gameMapModel))) {
                        if ((!MapValidation.checkInterlinkedContinent(this.gameMapModel))) {
                            if (!(MapValidation.emptyContinentValidation(this.gameMapModel))) {
                                if (!(MapValidation.unlinkedContinentValidation(this.gameMapModel))) {

                                    System.out.println(" All the map validations are correct");
                                    try {
                                        JOptionPane.showMessageDialog(theView,
                                                "File Loaded Successfully! Click Next to Play!", "Map Loaded",
                                                JOptionPane.INFORMATION_MESSAGE);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    System.out.println("All continents are not linked");
                                    JOptionPane.showOptionDialog(null, "All continents are not linked", "Invalid",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                            new Object[] {}, null);

                                }

                            } else {
                                System.out.println("Empty link country validation failed");
                                JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                        new Object[] {}, null);
                            }
                        } else {
                            System.out.println("ECheck interlinked Continent validation failed");
                            JOptionPane.showOptionDialog(null, "Check interlinedContinent validation failed", "Invalid",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                    null);

                        }
                    } else {
                        System.out.println("Empty continent validation failed");
                        JOptionPane.showOptionDialog(null, "Empty link country validation failed", "Invalid",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                null);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (actionEvent.getSource().equals(theView.nextButton)) {
            noOfPlayers = (int) theView.numOfPlayers.getSelectedItem();
            try {
                playerValidation();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource().equals(theView.cancelButton)) {
            new WelcomeScreenController();
            this.theView.dispose();
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
            for (int i = 0; i < noOfPlayers; i++) {
                if (i == 0) {
                    PlayerName = theView.PlayerName1.getText();
                } else if (i == 1) {
                    PlayerName = theView.PlayerName2.getText();
                } else if (i == 2) {
                    PlayerName = theView.PlayerName3.getText();
                } else if (i == 3) {
                    PlayerName = theView.PlayerName4.getText();
                } else if (i == 4) {
                    PlayerName = theView.PlayerName5.getText();
                }
                System.out.println("PlayerName " + PlayerName);
                if (PlayerName == null || "".equals(PlayerName.trim())) {
                    PlayerName = "Player " + (i + 1);
                }
                PlayerModel pm = new PlayerModel(PlayerName, 0, Color.WHITE, 0, new ArrayList<CountryModel>(),
                        new ArrayList<CardModel>());
                listOfPlayers.add(pm);
            }
            gamePlayModel.setGameMap(gameMapModel);
            gamePlayModel.setPlayers(listOfPlayers);
            gamePlayModel.setCards(gamePlayModel.getCardFromJSON());
            new StartupController(gamePlayModel);
            this.theView.dispose();
        } else {
            JOptionPane.showMessageDialog(theView,
                    "Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
                    "Map Loaded", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}
