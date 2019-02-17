package com.risk.controller;

import com.risk.view.MainGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This is the main starting point of the program containig main function
 *
 * @author gursimransingh
 */

public class MainGame implements ActionListener {
    private MainGameView d_mainGameView;

    public MainGame() {
        d_mainGameView = new MainGameView();
        d_mainGameView.setActionListener(this);
        d_mainGameView.setVisible(true);
    }

    public static void main(String[] args) {
        //launch(args);
        MainGame mainGame = new MainGame();
    }


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
     *
     */
    private void exitGame() {
        this.d_mainGameView.dispose();
    }

    /**
     * show play game window
     */
    private void showPlayGameWindow() {
        //new NewGameController();
        this.d_mainGameView.dispose();
    }

    /**
     * show edit game window
     */
    private void showEditGameWindow() {
        //new EditContinentController();
        this.d_mainGameView.dispose();
    }

    /**
     * show create map window
     */
    private void showCreateMapWindow() {
        // new CreateContinentController();
        this.d_mainGameView.dispose();
    }
}


