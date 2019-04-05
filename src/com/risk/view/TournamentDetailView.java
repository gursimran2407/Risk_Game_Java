package com.risk.view;

import com.risk.helperInterfaces.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Tournment Detail View. This class is responsible to view the tournment view interface
 * @author gursimransingh
 */
public class TournamentDetailView extends JFrame implements View {
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

    public TournamentDetailView() {
        Font mediumFont = new Font("Serif", Font.BOLD, 25);
        Font smallFont = new Font("Serif", Font.BOLD, 18);
        this.setTitle("Tournament Mode");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setSize(1000, 840);
        getContentPane().setLayout(null);

        headerLabel = new JLabel("Tournament Mode ");
        headerLabel.setBounds(25, 47, 311, 27);
        headerLabel.setFont(mediumFont);
        getContentPane().add(headerLabel);

        noOfMapsLabel = new JLabel("Select the number of Maps ");
        noOfMapsLabel.setBounds(25, 100, 250, 27);
        noOfMapsLabel.setFont(smallFont);
        getContentPane().add(noOfMapsLabel);

        Integer[] itemsMap = {1, 2, 3, 4, 5};
        noOfMaps = new JComboBox<>(itemsMap);
        noOfMaps.setBounds(25, 150, 150, 22);
        getContentPane().add(noOfMaps);

        headingMap = new JLabel("Select the maps to play");
        headingMap.setBounds(25, 180, 250, 22);
        getContentPane().add(headingMap);

        labelMapFile1 = new JLabel("Map 1");
        labelMapFile1.setBounds(25, 220, 75, 22);
        getContentPane().add(labelMapFile1);

        browseMap1Button = new JButton("Browse Map 1");
        browseMap1Button.setBounds(100, 220, 150, 22);
        getContentPane().add(browseMap1Button);

        labelMapFile2 = new JLabel("Map 2");
        labelMapFile2.setBounds(25, 260, 75, 22);
        getContentPane().add(labelMapFile2);

        browseMap2Button = new JButton("Browse Map 2");
        browseMap2Button.setBounds(100, 260, 150, 22);
        getContentPane().add(browseMap2Button);

        labelMapFile3 = new JLabel("Map 3");
        labelMapFile3.setBounds(25, 300, 75, 22);
        getContentPane().add(labelMapFile3);

        browseMap3Button = new JButton("Browse Map 3");
        browseMap3Button.setBounds(100, 300, 150, 22);
        getContentPane().add(browseMap3Button);

        labelMapFile4 = new JLabel("Map 4");
        labelMapFile4.setBounds(25, 340, 75, 22);
        getContentPane().add(labelMapFile4);

        browseMap4Button = new JButton("Browse Map 4");
        browseMap4Button.setBounds(100, 340, 150, 22);
        getContentPane().add(browseMap4Button);

        labelMapFile5 = new JLabel("Map 5");
        labelMapFile5.setBounds(25, 380, 75, 22);
        getContentPane().add(labelMapFile5);

        browseMap5Button = new JButton("Browse Map 5");
        browseMap5Button.setBounds(100, 380, 150, 22);
        getContentPane().add(browseMap5Button);

        validateMapButton = new JButton("Validate Map");
        validateMapButton.setBounds(30, 450, 150, 30);
        getContentPane().add(validateMapButton);

        noOfPlayersLabel = new JLabel("Select the number of Players ");
        noOfPlayersLabel.setBounds(300, 100, 250, 27);
        noOfPlayersLabel.setFont(smallFont);
        getContentPane().add(noOfPlayersLabel);

        Integer[] itemsPlayer = {2, 3, 4};
        noOfPlayers = new JComboBox<>(itemsPlayer);
        noOfPlayers.setBounds(300, 150, 150, 22);
        getContentPane().add(noOfPlayers);

        headPlayerText = new JLabel("Select the type of Player as per the number selected");
        headPlayerText.setBounds(300, 190, 300, 27);
        getContentPane().add(headPlayerText);

        playerText = new JLabel("Select Player 1 ");
        playerText.setBounds(300, 240, 100, 27);
        getContentPane().add(playerText);

        String[] typePlayer = new String[4];
        typePlayer[0] = "Aggressive";
        typePlayer[1] = "Benevolent";
        typePlayer[2] = "Random";
        typePlayer[3] = "Cheater";

        playerName1 = new JComboBox(typePlayer);
        playerName1.setBounds(410, 240, 150, 27);
        getContentPane().add(playerName1);

        playerText = new JLabel("Select Player 2 ");
        playerText.setBounds(300, 300, 100, 27);
        getContentPane().add(playerText);

        playerName2 = new JComboBox(typePlayer);
        playerName2.setBounds(410, 300, 150, 27);
        getContentPane().add(playerName2);

        playerText = new JLabel("Select Player 3 ");
        playerText.setBounds(300, 360, 100, 27);
        getContentPane().add(playerText);

        playerName3 = new JComboBox(typePlayer);
        playerName3.setBounds(410, 360, 150, 27);
        getContentPane().add(playerName3);

        playerText = new JLabel("Select Player 4 ");
        playerText.setBounds(300, 430, 100, 27);
        getContentPane().add(playerText);

        playerName4 = new JComboBox(typePlayer);
        playerName4.setBounds(410, 430, 150, 27);
        getContentPane().add(playerName4);

        noOfGamesLabel = new JLabel("Select the number of Games ");
        noOfGamesLabel.setBounds(600, 100, 250, 27);
        noOfGamesLabel.setFont(smallFont);
        getContentPane().add(noOfGamesLabel);

        Integer[] itemsGames = {1, 2, 3, 4, 5};
        noOfGames = new JComboBox<>(itemsGames);
        noOfGames.setBounds(600, 150, 150, 22);
        getContentPane().add(noOfGames);

        noOfTurnsLabel = new JLabel("Select the number of Turns ");
        noOfTurnsLabel.setBounds(600, 250, 250, 27);
        noOfTurnsLabel.setFont(smallFont);
        getContentPane().add(noOfTurnsLabel);

        Integer[] turns = {10, 15, 20, 25, 30, 35, 40, 45, 50};
        noOfTurnsText = new JComboBox<>(turns);
        noOfTurnsText.setBounds(600, 300, 150, 22);
        getContentPane().add(noOfTurnsText);

        saveAndPlayButton = new JButton("Play Game");
        saveAndPlayButton.setBounds(650, 550, 116, 25);
        getContentPane().add(saveAndPlayButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(650, 650, 116, 25);
        getContentPane().add(exitButton);

        chooseMap1 = new JFileChooser();
        chooseMap2 = new JFileChooser();
        chooseMap3 = new JFileChooser();
        chooseMap4 = new JFileChooser();
        chooseMap5 = new JFileChooser();

        initialize();

    }
}
