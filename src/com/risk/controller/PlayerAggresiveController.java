package com.risk.controller;

import com.risk.helperInterfaces.StrategyInterface;
import com.risk.model.GamePlayModel;

public class PlayerAggresiveController implements StrategyInterface {

    private GamePlayModel gamePlayModel;
    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayM the game play model
     */
    public PlayerAggresiveController(GamePlayModel gamePlayM) {
        gamePlayModel = gamePlayM;
        gamePlayModel.getConsoleOutput()
                .append("Initiating for " + gamePlayM.getMapRiskModelModObj().getPlayerTurn().getPlayerName());
    }

    @Override
    public void fortificationPhase() {

    }

    @Override
    public void reinforcementPhase() {

    }

    @Override
    public void attackPhase() {

    }
}
