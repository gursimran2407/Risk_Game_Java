package com.risk.controller;

import com.risk.model.GamePlayModel;
import com.risk.utilities.SaveGame;
import com.risk.view.GameModeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameModeController implements ActionListener {

    /** The view. */
    private GameModeView theGameModeView;

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public GameModeController() {
        this.theGameModeView = new GameModeView();
        this.theGameModeView.setActionListener(this);
        this.theGameModeView.setVisible(true);

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(theGameModeView.singleMode)) {
            new NewGameController();
            this.theGameModeView.dispose();
        } else if (actionEvent.getSource().equals(theGameModeView.tournamentMode)) {
            new TournmentDetailController();
            this.theGameModeView.dispose();
        } else if (actionEvent.getSource().equals(theGameModeView.loadGame)) {
            int value = theGameModeView.chooseGame.showOpenDialog(theGameModeView);
            if (value == JFileChooser.APPROVE_OPTION) {
                try {
                    File GameFile = theGameModeView.chooseGame.getSelectedFile();
                    SaveGame readGame = new SaveGame();
                    GamePlayModel gamePlayModel = readGame.readFROMJSONFile(GameFile);
                    JOptionPane.showMessageDialog(theGameModeView, "Gane Loaded Successfully!", "Game Loaded",
                            JOptionPane.INFORMATION_MESSAGE);
                    new GamePlayController(gamePlayModel);
                    this.theGameModeView.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.theGameModeView.dispose();
        }
    }

}
