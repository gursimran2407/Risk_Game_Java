package com.risk.controller;

import com.risk.view.MainGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This is the main starting point of the program containing main function
 *
 * @author gursimransingh
 */

public class MainGame implements ActionListener {
    private MainGameView d_mainGameView;

    /**
     * This Constructor initializes the values and sets the screen to visible.
     */
    public MainGame() {
        d_mainGameView = new MainGameView();
        d_mainGameView.setActionListener(this);
        d_mainGameView.setVisible(true);
    }

    public static void main(String[] args) {
        //launch(args);
        MainGame mainGame = new MainGame();
    }
    /**
     * Method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(d_mainGameView.createMapButton)) {
            // open new game window
            this.showCreateMapWindow();
        } else if (e.getSource().equals(d_mainGameView.editMapButton)) {
            // open load game window
            this.showEditGameWindow();
        } else if (e.getSource().equals(d_mainGameView.playMapButton)) {
            // open create game window
            this.showPlayGameWindow();
        } else if (e.getSource().equals(d_mainGameView.exitButton)) {
            // exit game
            this.exitGame();
        }
    }


    /**
     * exit the game
     */
    private void exitGame() {
        this.d_mainGameView.dispose();
    }

    /**
     * show play game window
     */
    private void showPlayGameWindow() {
        //Starting a new game
        new StartBrandNewGameController();
        this.d_mainGameView.dispose();
    }

    /**
     * show edit game window
     */
    private void showEditGameWindow() {
        new MapEditController();
        this.d_mainGameView.dispose();
    }

    /**
     * show create map window
     */
    private void showCreateMapWindow() {
        new MapCreateContinentController();
        this.d_mainGameView.dispose();
    }
}