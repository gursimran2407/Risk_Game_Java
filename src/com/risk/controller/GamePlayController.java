package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.risk.model.GamePlayModel;

/**
 * In GamePlayController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author KaranPannu
 * @version 1.0.0
 *
 */

public class GamePlayController implements ActionListener {

    public final GamePlayModel gamePlayModel;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param gamePlayModel Game play model
     */
    GamePlayController(GamePlayModel gamePlayModel) {
        this.gamePlayModel = gamePlayModel;
        gamePlay();
    }

    private void gamePlay() {

        this.gamePlayModel.getGameMap()
                .setPlayerTurn(this.gamePlayModel.getPlayers().get(this.gamePlayModel.getGameMap().getPlayerIndex()));
        new PlayerController(this.gamePlayModel);
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

}
