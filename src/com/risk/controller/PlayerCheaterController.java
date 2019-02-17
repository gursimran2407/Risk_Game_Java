package com.risk.controller;

import com.risk.helperInterfaces.StrategyInterface;
import com.risk.model.GamePlayModel;
/**
 * This class is for Cheater player type
 *
 * @author Namita
 */
public class PlayerCheaterController implements StrategyInterface {
    private GamePlayModel gamePlayModel;
    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayM the game play model
     */
    public PlayerCheaterController(GamePlayModel gamePlayM) {
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
