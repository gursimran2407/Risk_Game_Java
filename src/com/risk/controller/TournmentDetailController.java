package com.risk.controller;

import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.model.TournamentModel;
import com.risk.view.TournamentDetailView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
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

    /**
     * The tournament model.
     */
    private TournamentModel theTournamentModel = new TournamentModel();

    /**
     * The map file.
     */
    private File mapFile[] = new File[5];

    /**
     * The no of maps.
     */
    private int noOfMaps;

    /**
     * The game play model.
     */
    private GamePlayModel gamePlayModel;

    /**
     * The map loaded.
     */
    private boolean mapLoaded = false;

    /**
     * The valid game.
     */
    private boolean validGame = true;

    /**
     * The no of games.
     */
    private int noOfGames;

    /**
     * The no of players.
     */
    private int noOfPlayers;

    /**
     * The Player name.
     */
    private String turns, PlayerType, PlayerName;

    /**
     * The no of turns.
     */
    private int noOfTurns;
    /**
     * The list of players.
     */
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

    public TournmentDetailController() {
        this.theTournamentDetailView = new TournamentDetailView();
        this.theTournamentDetailView.setActionListener(this);
        this.theTournamentDetailView.setVisible(true);
        for (int i = 0; i < 5; i++) {
            mapFile[i] = null;
        }
    }

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

            noOfMaps = (int) theTournamentDetailView.noOfMaps.getSelectedItem();
            for (int i = 0; i < noOfMaps; i++) {
                if (mapFile[i] == null) {
                    JOptionPane.showOptionDialog(null, "Select the " + (i + 1) + " appropriate maps", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                } else {
                    if (mapVerification(mapFile[i], i)) {
                        if (noOfMaps == (i + 1)) {
                            mapLoaded = true;
                        }
                    }
                }
            }
        }

    }
}
