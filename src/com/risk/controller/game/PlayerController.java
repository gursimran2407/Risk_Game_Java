package com.risk.controller.game;

import com.risk.model.GamePlayModel;
import com.risk.view.FortificationView;
import com.risk.view.ReinforcementView;


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
     * The ReinforcementController view
     */
    private ReinforcementView reinforcementViewObj;

    /**
     * The Fortication view
     */
    private FortificationView forticationviewObj;


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
