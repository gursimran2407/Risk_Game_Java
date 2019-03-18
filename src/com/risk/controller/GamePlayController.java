package com.risk.controller;

import com.risk.model.GamePlayModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author gursimransingh
 */
public class GamePlayController implements ActionListener {\
    GamePlayModel gamePlayModel;

    public GamePlayController(GamePlayModel gamePlayModel) {
        this.gamePlayModel = gamePlayModel;
        gamePlay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void gamePlay() {

        this.gamePlayModel.getGameMap()
                .setPlayerTurn(this.gamePlayModel.getPlayers().get(this.gamePlayModel.getGameMap().getIndexOfPlayer()));
        new PlayerController(this.gamePlayModel);
    }
}
