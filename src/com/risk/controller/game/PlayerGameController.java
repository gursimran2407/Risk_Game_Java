package com.risk.controller.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.risk.Environment;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

/**
 * In PlayerGameController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Karandeep
 * @version 1.0.0
 *
 */

public class PlayerGameController implements ActionListener {

    private final Environment environment;

    private MapRiskModel mapRiskModel;
    private List<PlayerModel> listOfPlayers;

    public PlayerGameController(final Environment environment, MapRiskModel mapRiskModel, List<PlayerModel> listOfPlayers) {
        this.environment = environment;
        this.mapRiskModel = mapRiskModel;
        this.listOfPlayers = listOfPlayers;

        gamePlay();
    }

    public void gamePlay() {
        this.mapRiskModel.setPlayerTurn(
                this.listOfPlayers.get(this.mapRiskModel.getIndexOfPlayer()));
        new ReinforcementController(environment, this.mapRiskModel);

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
