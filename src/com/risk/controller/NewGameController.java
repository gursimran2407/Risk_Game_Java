package com.risk.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import com.risk.model.CardModel;
import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.utilities.Validation;
import com.risk.view.NewGameView;

/**
 * In NewGameController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Namita
 *
 */

public class NewGameController implements ActionListener {

    private NewGameView theView;
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<>();
    private GameMapModel gameMapModel = new GameMapModel();
    private GamePlayModel gamePlayModel = new GamePlayModel();
    private int noOfPlayers;
    private String playerName = "";
    private String playerType = "";

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
                    if (!(MapValidation.nonContinentValidation(this.gameMapModel))) {
                        if (!(MapValidation.emptyLinkCountryValidation(this.gameMapModel))) {
                            if (!(MapValidation.emptyContinentValidation(this.gameMapModel))) {

                                System.out.println(" All the map validations are correct");
                                try {
                                    JOptionPane.showMessageDialog(theView,
                                            "File Loaded Successfully! Click Next to Play!", "Map Loaded",
                                            JOptionPane.INFORMATION_MESSAGE);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } else {
                                System.out.println("Empty link country validation failed");
                                JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                        new Object[] {}, null);
                            }
                        } else {
                            System.out.println("Empty continent validation failed");
                            JOptionPane.showOptionDialog(null, "Empty link country validation failed", "Invalid",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                    null);
                        }
                    } else {
                        System.out.println("One of the continent is invalid");
                        JOptionPane.showOptionDialog(null, "Map is not linked properly", "Invalid",
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
            int h = 1, a = 1, b = 1, r = 1, c = 1;
            for (int i = 0; i < noOfPlayers; i++) {
                if (i == 0) {
                    playerType = theView.playerType1.getSelectedItem().toString();
                    playerName = theView.playerName1.getText();
                } else if (i == 1) {
                    playerType = theView.playerType2.getSelectedItem().toString();
                    playerName = theView.playerName2.getText();
                } else if (i == 2) {
                    playerType = theView.playerType3.getSelectedItem().toString();
                    playerName = theView.playerName3.getText();
                } else if (i == 3) {
                    playerType = theView.playerType4.getSelectedItem().toString();
                    playerName = theView.playerName4.getText();
                } else if (i == 4) {
                    playerType = theView.playerType5.getSelectedItem().toString();
                    playerName = theView.playerName5.getText();
                }
                System.out.println("playerName " + playerName);
                if (playerType == null || "".equals(playerType.trim())) {
                    playerType = "Human";
                    if (playerName == null || "".equals(playerName.trim())) {
                        playerName = "Human " + h;
                        h++;
                    }
                } else if ("Human".equals(playerType)) {
                    playerType = "Human";
                    if (playerName == null || "".equals(playerName.trim())) {
                        playerName = "Human " + h;
                        h++;
                    }
                } else if ("Aggressive".equals(playerType)) {
                    playerType = "Aggressive";
                    if (playerName == null || "".equals(playerName.trim())) {
                        playerName = "Aggressive " + a;
                        a++;
                    }
                } else if ("Benevolent".equals(playerType)) {
                    playerType = "Benevolent";
                    if (playerName == null || "".equals(playerName.trim())) {
                        playerName = "Benevolent " + b;
                        b++;
                    }
                } else if ("Random".equals(playerType)) {
                    playerType = "Random";
                    if (playerName == null || "".equals(playerName.trim())) {
                        playerName = "Random " + r;
                        r++;
                    }
                } else if ("Cheater".equals(playerType)) {
                    playerType = "Cheater";
                    if (playerName == null || "".equals(playerName.trim())) {
                        playerName = "Cheater " + c;
                        c++;
                    }
                }

                PlayerModel pm = new PlayerModel(playerName, playerType, 0, Color.WHITE, 0,
                        new ArrayList<CountryModel>(), new ArrayList<CardModel>());
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
