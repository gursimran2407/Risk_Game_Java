package com.risk.view;

import com.risk.helperInterfaces.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class GameModeView extends JFrame implements View {

    public JButton singleMode;
    public JLabel gameModeLabel;
    public JButton tournamentMode;
    public JButton loadGame;
    public JFileChooser chooseGame;

    /**
     * Create the application.
     */
    public GameModeView() {
        Font mediumFont = new Font("Serif", Font.BOLD, 25);
        this.setTitle("Select Game Mode");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.setSize(1000, 840);
        getContentPane().setLayout(null);

        gameModeLabel = new JLabel("Select the Mode to play Game");
        gameModeLabel.setBounds(150, 150, 400, 100);
        gameModeLabel.setFont(mediumFont);
        getContentPane().add(gameModeLabel);

        singleMode = new JButton("Single game mode");
        singleMode.setBounds(153, 300, 150, 40);
        getContentPane().add(singleMode);

        tournamentMode = new JButton("Tournament Mode");
        tournamentMode.setBounds(350, 300, 150, 40);
        getContentPane().add(tournamentMode);

        loadGame = new JButton("Load Previous Game");
        loadGame.setBounds(225, 375, 175, 40);
        getContentPane().add(loadGame);

        chooseGame = new JFileChooser();

        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setBounds(100, 100, 700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Set the Action Listener
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        singleMode.addActionListener(actionListener);
        loadGame.addActionListener(actionListener);
        tournamentMode.addActionListener(actionListener);

    }

    /**
     * Update the view based on observer
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
