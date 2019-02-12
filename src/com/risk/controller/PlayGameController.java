package com.risk.controller;

import com.risk.model.GamePlayModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGameController implements ActionListener {

    private GamePlayModel gamePlayModel = null;

    public PlayGameController(GamePlayModel gamePlayM) {
        gamePlayModel = gamePlayM;
        gamePlay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void gamePlay() {
        gamePlayModel.getMapRiskModelModObj()
                .setPlayerTurn(this.gamePlayModel.getPlayersList().get(this.gamePlayModel.getMapRiskModelModObj().getIndexOfPlayer()));
        new PlayerController(this.gamePlayModel);
    }
}
