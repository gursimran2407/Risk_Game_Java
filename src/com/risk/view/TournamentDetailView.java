package com.risk.view;

import com.risk.helperInterfaces.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Tournment Detail View. This class is responsible to view the tournment view interface
 * @author gursimransingh
 */
public class TournamentDetailView implements View {
    /**
     * The browse map.
     */
    public JButton browseMap1Button, browseMap2Button, browseMap3Button, browseMap4Button, browseMap5Button;
    public JFileChooser chooseMap1, chooseMap2, chooseMap3, chooseMap4, chooseMap5;

    /**
     * The label map file.
     */
    public JLabel labelMapFile1, labelMapFile2, labelMapFile3, labelMapFile4, labelMapFile5;
    public JLabel headingMap;

    public JButton validateMapButton;

    /**
     * The final players.
     */
    public JComboBox<String> playerName5, playerName1, playerName2, playerName3, playerName4;
    public JLabel headPlayerText, playerName, playerText;

    /**
     * the no of Maps
     */
    public JLabel noOfMapsLabel;
    public JComboBox<Integer> noOfMaps;

    /**
     * the no of Players
     */
    public JLabel noOfPlayersLabel;
    public JComboBox<Integer> noOfPlayers;

    /**
     * the no of Games
     */
    public JLabel noOfGamesLabel;
    public JComboBox<Integer> noOfGames;

    /**
     * the no of Turns
     */
    public JLabel noOfTurnsLabel;
    public JComboBox<Integer> noOfTurnsText;

    /**
     * The save and play button
     */
    public JButton saveAndPlayButton;

    /**
     * The exit button.
     */
    public JButton exitButton;

    /**
     * The heading label.
     */
    public JLabel headerLabel;

    @Override
    public void setActionListener(ActionListener actionListener) {
        saveAndPlayButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
        browseMap1Button.addActionListener(actionListener);
        browseMap2Button.addActionListener(actionListener);
        browseMap3Button.addActionListener(actionListener);
        browseMap4Button.addActionListener(actionListener);
        browseMap5Button.addActionListener(actionListener);
        validateMapButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void initialize() {
        setBounds(100, 100, 900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
