package com.risk.controller;

import com.risk.model.GamePlayModel;
import com.risk.view.Reinforcement;
import com.risk.view.Attack;
import com.risk.view.Fortification;


import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 * The player class in the controller package will facilitate the data flow into the
 * model object and update the view whenever the data is changed.
 *
 * @author Karan
 */

/* testing the change*/
public class PlayerController implements ActionListener, ItemListener {
    /**
     * the game play model
     */
    private GamePlayModel gamePlayObj;

    /**
     * The Reinforcement view
     */
    private Reinforcement reinforcementViewObj;

    /**
     * the Attack view
     */
    private Attack attackViewObj;

    /**
     * The Fortication view
     */
    private Fortification forticationviewObj;


    public PlayerController(GamePlayModel gamePlayModel) {
        gamePlayObj = gamePlayModel;
        if (gamePlayObj.getPhaseOfGame() == null) {
            //if (!val.endOfGame(gamePlayObj)) {
                String PlayerType = gamePlayObj.getMapRiskModelModObj().getPlayerTurn().getPlayerType();
                if ("Human".equals(PlayerType)) {
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                            .setStrategy(new PlayerHumanController(gamePlayObj));
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeReinforcement();
                    //************nothing is in reinforcement view ********************
                    /*theReinforcementView = new ReinforcementView(gamePlayObj);
                    theReinforcementView.setVisible(true);
                    theReinforcementView.setActionListener(this);
                    gamePlayObj.getMapRiskModelModObj().addObserver(theReinforcementView);
                    gamePlayObj.addObserver(theReinforcementView);*/
                } else if ("Aggressive".equals(PlayerType)) {
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                            .setStrategy(new PlayerAggresiveController(gamePlayObj));
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeReinforcement();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeAttack();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeFortification();
                } else if ("Benevolent".equals(PlayerType)) {
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                            .setStrategy(new PlayerBenevolentController(gamePlayObj));
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeReinforcement();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeAttack();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeFortification();
                } else if ("Random".equals(PlayerType)) {
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                            .setStrategy(new PlayerRandomController(gamePlayObj));
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeReinforcement();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeAttack();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeFortification();
                } else if ("Cheater".equals(PlayerType)) {
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                            .setStrategy(new PlayerCheaterController(gamePlayObj));
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeReinforcement();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeAttack();
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeFortification();
                }
                if (!"Human".equals(PlayerType)) {
                    int index = gamePlayObj.getMapRiskModelModObj().getIndexOfPlayer();

                    index++;
                    if (gamePlayObj.getPlayersList().size() > index) {
                        gamePlayObj.getMapRiskModelModObj().setIndexOfPlayer(index);
                        gamePlayObj.getPlayersList().get(index).callObservers();
                    } else {
                        index = 0;
                        gamePlayObj.getMapRiskModelModObj().setIndexOfPlayer(index);
                        gamePlayObj.getPlayersList().get(index).callObservers();
                    }
                    new PlayGameController(gamePlayObj);
                }
           /* } else {
                String nameOfWinner = val.determineWinner(gamePlayObj);
                if ("draw".equals(nameOfWinner)) {
                    System.out.println(" Game is draw ");
                    //JOptionPane.showOptionDialog(null, "The game is draw", "Valid", JOptionPane.DEFAULT_OPTION,
                        //    JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                } else {
                    System.out.println(nameOfWinner + " is winner ");
                    //JOptionPane.showOptionDialog(null,
                         //   "Bravo! You have won! Game is over!" + nameOfWinner + "is the winner", "Valid",
                          //  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                }
            }*/
        } else {
            String PlayerType = gamePlayObj.getMapRiskModelModObj().getPlayerTurn().getPlayerType();
            if ("Human".equals(PlayerType)) {
                gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                        .setStrategy(new PlayerHumanController(gamePlayObj));
                String Phase = gamePlayObj.getPhaseOfGame();
                if ("Reinforcement".equals(Phase)) {
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn()
                            .setStrategy(new PlayerHumanController(gamePlayObj));
                    gamePlayObj.getMapRiskModelModObj().getPlayerTurn().executeReinforcement();
                    /*theReinforcementView = new ReinforcementView(gamePlayObj);
                    theReinforcementView.setVisible(true);
                    theReinforcementView.setActionListener(this);
                    gamePlayObj.getMapRiskModelModObj().addObserver(theReinforcementView);
                    gamePlayObj.addObserver(theReinforcementView);*/
                } /*else if ("Attack".equals(Phase)) {
                    theReinforcementView = new ReinforcementView(gamePlayObj);
                    theReinforcementView.dispose();
                    theAttackView = new AttackView(gamePlayObj);
                    theAttackView.setActionListener(this);
                    theAttackView.setVisible(true);
                    gamePlayObj.deleteObservers();
                    gamePlayObj.addObserver(this.theAttackView);
                    gamePlayObj.setArmyToMoveText(false);
                    gamePlayObj.setCardToBeAssigned(false);
                } else if ("Fortification".equals(Phase)) {
                    theReinforcementView = new ReinforcementView(gamePlayObj);
                    theReinforcementView.dispose();
                    theAttackView = new AttackView(gamePlayObj);
                    theAttackView.dispose();
                    theFortificationView = new FortificationView(gamePlayObj);
                    theFortificationView.setActionListener(this);
                    theFortificationView.setItemListener(this);
                    theFortificationView.setVisible(true);
                    gamePlayObj.addObserver(this.theFortificationView);
                }*/
            }
            gamePlayObj.setPhaseOfGame(null);
        }
    }

    /**
     * This is the method that is required if we implement the Action Listener. This method will perform the action
     * after listening to the action event set in the view.
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public void itemStateChanged(ItemEvent itemEvent) {

    }

}
