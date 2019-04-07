package com.risk.controller;

import com.risk.helperInterfaces.Strategy;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.utilities.Validation;
import com.risk.view.AttackView;
import com.risk.view.FortificationView;
import com.risk.view.ReinforcementView;

import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * This controller as any other controller class in the observer pattern will update the view whenever it is changed and is
 * responsible for data flow into the model
 *
 * @author KaranbirPannu
 *
 */

public class HumanPlayerController implements Strategy {

    /** The game play model. */
    private GamePlayModel gamePlayModel;

    /** The reinforcement view. */
    private ReinforcementView theReinforcementView;

    /** The fortification view. */
    private FortificationView theFortificationView;

    /** The attack view. */
    private AttackView theAttackView;

    /** The list of players. */
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

    /** The no of players. */
    private int noOfPlayers;

    /** The val. */
    private Validation val = new Validation();

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayModel the game play model
     */
    public HumanPlayerController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
        this.gamePlayModel.getConsoleText()
                .append("Initiating for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
    }

    /**
     * This method is called in reinforcement phase.
     *
     */
    public void reinforcement() {
        System.out.println("Human - reinforcement");
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.callObservers();
        this.gamePlayModel.getConsoleText()
                .append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
        this.gamePlayModel.getConsole()
                .append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
        this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
                + this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));
        if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size() > 0) {
            this.gamePlayModel.getConsoleText().append("\n Reinforcement View - Please find the list of Cards: \n");
            for (int i = 0; i < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); i++) {
                this.gamePlayModel.getConsoleText()
                        .append(gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(i).getCardId() + "\n ");
            }
            this.gamePlayModel.getGameMap().getPlayerTurn().setShowReinforcementCard(true);
        }
    }

    /**
     * This method is called in fortification phase.
     */
    public void fortification() {
        System.out.println("Human - fortification");
        this.gamePlayModel.getConsole()
                .append("Initiating fortification " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating Fortification for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

    }

    /**
     * This method is called in attack phase.
     */
    public void attack() {
        System.out.println("Human - attack");
        this.gamePlayModel.getConsole()
                .append("Initiating attack " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + "'s attack");
    }
}
