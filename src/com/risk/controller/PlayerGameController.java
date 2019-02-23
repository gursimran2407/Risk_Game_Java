package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

/**
 * In GamePlayController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Karandeep
 * @version 1.0.0
 *
 */

public class PlayerGameController implements ActionListener {

    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    public MapRiskModel mapRiskModel = null;

    public PlayerGameController(MapRiskModel gameMapModel, ArrayList<PlayerModel> listOfPlayers) {
        this.mapRiskModel = mapRiskModel;
        this.listOfPlayers = listOfPlayers;
        gamePlay();
    }

    public void gamePlay() {

        this.mapRiskModel.setPlayerTurn(this.listOfPlayers.get(this.mapRiskModel.getIndexOfPlayer()));
        new ReinforcementController(this.mapRiskModel);

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO Auto-generated method stub

    }
}
