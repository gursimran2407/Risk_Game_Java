package com.risk.controller;

import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.model.TournamentModel;
import com.risk.view.TournamentDetailView;

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
    public void actionPerformed(ActionEvent e) {

    }
}
