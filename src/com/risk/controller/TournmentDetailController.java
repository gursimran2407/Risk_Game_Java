package com.risk.controller;

import com.risk.model.*;
import com.risk.utilities.Validation;
import com.risk.view.TournamentDetailView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * @author gursimransingh
 */

public class TournmentDetailController implements ActionListener {

    /**
     * The view.
     */
    private TournamentDetailView theTournamentDetailView;

    /** The tournament model. */
    private TournamentModel theTournamentModel = new TournamentModel();

    /** The map file. */
    private File mapFile[] = new File[5];

    /** The no of maps. */
    private int noOfMaps;

    /** The game play model. */
    private GamePlayModel gamePlayModel;

    /** The map loaded. */
    private boolean mapLoaded = false;

    /** The valid game. */
    private boolean validGame = true;

    /** The no of games. */
    private int noOfGames;

    /** The no of players. */
    private int noOfPlayers;

    /** The Player name. */
    private String turns, PlayerType, PlayerName;

    /** The no of turns. */
    private int noOfTurns;
    /** The list of players. */
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public TournmentDetailController() {
        this.theTournamentDetailView = new TournamentDetailView();
        this.theTournamentDetailView.setActionListener(this);
        this.theTournamentDetailView.setVisible(true);
        for (int i = 0; i < 5; i++) {
            mapFile[i] = null;
        }
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(theTournamentDetailView.saveAndPlayButton)) {
            validGame = true;
            noOfGames = (int) theTournamentDetailView.noOfGames.getSelectedItem();
            this.theTournamentModel.setNoOfGames(noOfGames);
            noOfPlayers = (int) theTournamentDetailView.noOfPlayers.getSelectedItem();
            try {
                playerValidation();
            } catch (ParseException e) {
                validGame = false;
                e.printStackTrace();
            }
            noOfTurns = (int) theTournamentDetailView.noOfTurnsText.getSelectedItem();
            if (mapLoaded != true) {
                validGame = false;
            }
            if (validGame == true) {
                for (int i = 0; i < noOfGames; i++) {
                    for (int j = 0; j < this.theTournamentModel.getGamePlay().size(); j++) {
                        new StartUpTournamentController(this.theTournamentModel.getGamePlay().get(j), noOfTurns);
                    }
                }
                this.theTournamentDetailView.dispose();
            }
        } else if (actionEvent.getSource().equals(theTournamentDetailView.exitButton)) {
            this.theTournamentDetailView.dispose();
        } else if (actionEvent.getSource().equals(theTournamentDetailView.browseMap1Button)) {
            int value = theTournamentDetailView.chooseMap1.showOpenDialog(theTournamentDetailView);
            if (value == JFileChooser.APPROVE_OPTION) {
                mapFile[0] = theTournamentDetailView.chooseMap1.getSelectedFile();
            }
        } else if (actionEvent.getSource().equals(theTournamentDetailView.browseMap2Button)) {
            int value = theTournamentDetailView.chooseMap2.showOpenDialog(theTournamentDetailView);
            if (value == JFileChooser.APPROVE_OPTION) {
                mapFile[1] = theTournamentDetailView.chooseMap2.getSelectedFile();
            }
        } else if (actionEvent.getSource().equals(theTournamentDetailView.browseMap3Button)) {
            int value = theTournamentDetailView.chooseMap3.showOpenDialog(theTournamentDetailView);
            if (value == JFileChooser.APPROVE_OPTION) {
                mapFile[2] = theTournamentDetailView.chooseMap3.getSelectedFile();
            }
        } else if (actionEvent.getSource().equals(theTournamentDetailView.browseMap4Button)) {
            int value = theTournamentDetailView.chooseMap4.showOpenDialog(theTournamentDetailView);
            if (value == JFileChooser.APPROVE_OPTION) {
                mapFile[3] = theTournamentDetailView.chooseMap4.getSelectedFile();
            }
        } else if (actionEvent.getSource().equals(theTournamentDetailView.browseMap5Button)) {
            int value = theTournamentDetailView.chooseMap5.showOpenDialog(theTournamentDetailView);
            if (value == JFileChooser.APPROVE_OPTION) {
                mapFile[4] = theTournamentDetailView.chooseMap5.getSelectedFile();
            }
        } else if (actionEvent.getSource().equals(theTournamentDetailView.validateMapButton)) {

            //To be rectified
        }
    }

    /**
     * Map verification.
     *
     * @param mapFile the map file
     * @param index   the index
     * @return true, if successful
     */
    public boolean mapVerification(File mapFile, int index) {
        gamePlayModel = new GamePlayModel();
        boolean validMap = true;
        GameMapModel gameMapModel = new GameMapModel(mapFile);

        Validation MapValidation = new Validation();
        boolean flag1 = MapValidation.emptyLinkCountryValidation(gameMapModel);

        boolean flag3 = MapValidation.emptyContinentValidation(gameMapModel);
        boolean flag2 = MapValidation.checkInterlinkedContinent(gameMapModel);
        System.out.println(flag1 + " " + flag2 + " " + flag3);
        if (!(MapValidation.nonContinentValidation(gameMapModel))) {
            if (!(MapValidation.emptyLinkCountryValidation(gameMapModel))) {
                if (!(MapValidation.emptyContinentValidation(gameMapModel))) {
                    gamePlayModel.setGameMap(gameMapModel);
                    this.theTournamentModel.getGamePlay().add(gamePlayModel);
                    System.out.println(" All the map validations are correct");
                    try {
                        JOptionPane.showMessageDialog(theTournamentDetailView,
                                "File Loaded Successfully! Click Next to Play!", "Map Loaded",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception e) {
                        validMap = false;
                        e.printStackTrace();
                    }

                } else {
                    validMap = false;
                    System.out.println("Empty link country validation failed");
                    JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                }
            } else {
                validMap = false;
                System.out.println("Empty continent validation failed");
                JOptionPane.showOptionDialog(null, "Empty link country validation failed", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
            }
        } else {
            validMap = false;
            System.out.println("One of the continent is invalid");
            JOptionPane.showOptionDialog(null, "Map is not linked properly", "Invalid", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);

        }
        return validMap;
    }

    /**
     * Check for the player validation.
     *
     * @throws ParseException the parse exception
     */
    public void playerValidation() throws ParseException {
        int a = 1, b = 1, r = 1, c = 1;
        listOfPlayers = new ArrayList<PlayerModel>();
        for (int i = 0; i < noOfPlayers; i++) {
            if (i == 0) {
                PlayerType = theTournamentDetailView.playerName1.getSelectedItem().toString();
            } else if (i == 1) {
                PlayerType = theTournamentDetailView.playerName2.getSelectedItem().toString();
            } else if (i == 2) {
                PlayerType = theTournamentDetailView.playerName3.getSelectedItem().toString();
            } else if (i == 3) {
                PlayerType = theTournamentDetailView.playerName4.getSelectedItem().toString();
            }
            if ("Aggressive".equals(PlayerType)) {
                PlayerName = "Aggressive " + a;
                a++;
            } else if ("Benevolent".equals(PlayerType)) {
                PlayerName = "Benevolent " + b;
                b++;
            } else if ("Random".equals(PlayerType)) {
                PlayerName = "Random " + r;
                r++;
            } else if ("Cheater".equals(PlayerType)) {
                PlayerName = "Cheater " + c;
                c++;
            }

            PlayerModel pm = new PlayerModel(PlayerName, PlayerType, 0, Color.WHITE, 0, new ArrayList<CountryModel>(),
                    new ArrayList<CardModel>());
            listOfPlayers.add(pm);
        }

        for (int i = 0; i < noOfMaps; i++) {
            this.theTournamentModel.getGamePlay().get(i).setPlayers(listOfPlayers);
            this.theTournamentModel.getGamePlay().get(i).setCards(gamePlayModel.getCardFromJSON());
        }

    }
}
