package com.risk.controller;

import com.risk.helperInterfaces.Strategy;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.utilities.Validation;
import com.risk.view.AttackView;
import com.risk.view.FortificationView;
import com.risk.view.ReinforcementView;

import java.util.ArrayList;

public class HumanPlayerController implements Strategy {

    private GamePlayModel gamePlayModel;
    private ReinforcementView theReinforcementView;
    private FortificationView theFortificationView;
    private AttackView theAttackView;
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private int noOfPlayers;
    private Validation val = new Validation();

    public HumanPlayerController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
        this.gamePlayModel.getConsoleText()
                .append("Initiating for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
    }

    /**
     * reinforcement phase.
     */
    @Override
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
     * attack phase.
     */
    @Override
    public void attack() {
        System.out.println("Human - attack");
        this.gamePlayModel.getConsole()
                .append("Initiating attack " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + "'s attack");
    }

    /**
     * fortification phase
     */
    @Override
    public void fortification() {
        System.out.println("Human - fortification");
        this.gamePlayModel.getConsole()
                .append("Initiating fortification " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating Fortification for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

    }

}
