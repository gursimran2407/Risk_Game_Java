package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.risk.view.WelcomeScreenView;


/**
 * "WelcomeScreenController" is the main class. It represents a welcome window
 * containing main buttons to edit/create map file and start playing game
 *
 * @author gursimransingh
 */
public class WelcomeScreenController implements ActionListener {
    public static void main(String[] args) {
        new WelcomeScreenController();
    }

    private WelcomeScreenView theView;

    /**
     * Constructor initializes values and sets the screen too visible
     */
    public WelcomeScreenController() {
        this.theView = new WelcomeScreenView();

        this.theView.setActionListener(this);
        this.theView.setVisible(true);

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(theView.createMapButton)) {
            // open new game window
            this.showCreateMapWindow();
        } else if (actionEvent.getSource().equals(theView.editMapButton)) {
            // open load game window
            this.showEditGameWindow();
        } else if (actionEvent.getSource().equals(theView.playMapButton)) {
            // open create game window
            this.showPlayGameWindow();
        } else if (actionEvent.getSource().equals(theView.exitButton)) {
            // exit game
            this.exitGame();
        }
    }

    /**
     * exit game
     */
    private void exitGame() {
        this.theView.dispose();
    }

    /**
     * show play game window
     */
    private void showPlayGameWindow() {
        new GameModeController();
        this.theView.dispose();
    }

    /**
     * show edit game window
     */
    private void showEditGameWindow() {
        new EditContinentController();
        this.theView.dispose();
    }

    /**
     * show create map window
     */
    private void showCreateMapWindow() {
        new CreateContinentController();
        this.theView.dispose();
    }
}
