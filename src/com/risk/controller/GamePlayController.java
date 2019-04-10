package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.risk.model.GamePlayModel;
import com.risk.model.strategy.AgressivePlayerStrategy;
import com.risk.model.strategy.BenevolentPlayerStrategy;
import com.risk.model.strategy.CheaterPlayerStrategy;
import com.risk.model.strategy.RandomPlayerStrategy;
import com.risk.utilities.Validation;

/**
 * In GamePlayController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @version 1.0.0
 *
 */

public class GamePlayController implements ActionListener {

    public GamePlayModel gamePlayModel = null;
    /** The val. */
    private Validation val = new Validation();
    private boolean displayFlag = false;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param gamePlayModel
     */
    public GamePlayController(GamePlayModel gamePlayModel) {
        this.gamePlayModel = gamePlayModel;
        gamePlay();
    }

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param gamePlayModel
     */
    public GamePlayController(GamePlayModel gamePlayModel, int noOfTurn) {
        this.gamePlayModel = gamePlayModel;
        this.gamePlayModel.getGameMap()
                .setPlayerTurn(this.gamePlayModel.getPlayers().get(this.gamePlayModel.getGameMap().getPlayerIndex()));
        int tempNoOfTurn = 0;
        while (noOfTurn > tempNoOfTurn) {
            if (val.endOfGame(this.gamePlayModel) == false) {
                String PlayerType = this.gamePlayModel.getGameMap().getPlayerTurn().getTypePlayer();
                if ("Aggressive".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new AgressivePlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Benevolent".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new BenevolentPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Random".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new RandomPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Cheater".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new CheaterPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                }
                int index = this.gamePlayModel.getGameMap().getPlayerIndex();

                index++;
                if (this.gamePlayModel.getPlayers().size() > index) {
                    this.gamePlayModel.getGameMap().setPlayerIndex(index);
                    this.gamePlayModel.getPlayers().get(index).callObservers();
                } else {
                    tempNoOfTurn++;
                    index = 0;
                    this.gamePlayModel.getGameMap().setPlayerIndex(index);
                    this.gamePlayModel.getPlayers().get(index).callObservers();
                }
            } else {
                tempNoOfTurn = noOfTurn + 1;
                displayFlag = true;
                String nameOfWinner = val.determineWinner(this.gamePlayModel);
                if ("draw".equals(nameOfWinner)) {
                    System.out.println(" Game is draw ");
                    JOptionPane.showOptionDialog(null, "The game is draw", "Valid", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                } else {
                    System.out.println(nameOfWinner + " is winner ");
                    JOptionPane.showOptionDialog(null,
                            "Bravo! You have won! Game is over!" + nameOfWinner + "is the winner", "Valid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                }
            }
        }
        if (displayFlag != true) {
            String nameOfWinner = val.determineWinner(this.gamePlayModel);
            if ("draw".equals(nameOfWinner)) {
                System.out.println(" Game is draw ");
                JOptionPane.showOptionDialog(null, "The game is draw", "Valid", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            } else {
                System.out.println(nameOfWinner + " is winner ");
                JOptionPane.showOptionDialog(null,
                        "Bravo! You have won! Game is over!" + nameOfWinner + "is the winner", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            }
        }
    }

    public void gamePlay() {
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
