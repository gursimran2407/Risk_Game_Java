package com.risk.controller.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.risk.Environment;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

/**
 * The PlayerGameController class, also takes care of the movement of
 * data into the model corresponding to the view and the controller and
 * also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Karandeep
 * @version 1.0.0
 */

public class PlayerGameController implements ActionListener {

    private final Environment environment;

    private MapRiskModel mapRiskModel;
    private List<PlayerModel> listOfPlayers;

    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
     * @param environment
     * @param mapRiskModel
     * @param listOfPlayers
     */
    public PlayerGameController(final Environment environment, MapRiskModel mapRiskModel, List<PlayerModel> listOfPlayers) {
        this.environment = environment;
        this.mapRiskModel = mapRiskModel;
        this.listOfPlayers = listOfPlayers;

        gamePlay();
    }

    /**
     * This method is to invoke the reinforcement controller by getting the
     * player number from the saved data.
     */
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
