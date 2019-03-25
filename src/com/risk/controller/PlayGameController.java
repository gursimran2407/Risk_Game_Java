package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.risk.model.GamePlayModel;

/**
 * In PlayGameController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Gursimran Singh
 * @version 1.0.0
 *
 */

public class PlayGameController implements ActionListener {

    public GamePlayModel gamePlayModel;

    public PlayGameController(GamePlayModel gamePlayModel) {
        this.gamePlayModel = gamePlayModel;
        gamePlay();
    }

    public void gamePlay() {

        this.gamePlayModel.getGameMap()
                .setPlayerTurn(this.gamePlayModel.getPlayers().get(this.gamePlayModel.getGameMap().getIndexOfPlayer()));
        new PlayerController(this.gamePlayModel);
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
