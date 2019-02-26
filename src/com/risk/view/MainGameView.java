package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * @author gursimransingh
 */
public class MainGameView extends JFrame implements ViewInterface {

    public JButton createMapButton = new JButton("Create Map");
    public JButton editMapButton = new JButton("Edit Map");
    public JButton playMapButton = new JButton("Play");
    public JButton exitButton = new JButton("Exit");

    /**
     * Constructor to create welcome screen that is start of the game.
     */
    public MainGameView() throws HeadlessException {
        JLabel welcomeLabel = new JLabel("<html><font color='red'>Welcome to the risky game of \"RISK\"</font></html>");
        JLabel questionLabel = new JLabel("<html><font color='red'>Select the required Option.</font></html>");
        JPanel panel = new JPanel();
        this.setName("Risk Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(700, 500);
        this.setResizable(false);
        this.setVisible(false);
        panel.setBackground(Color.CYAN);
        panel.setLayout(null);
        this.add(panel);

        Font largeFont = new Font("Serif", Font.BOLD, 30);
        Font mediumFont = new Font("Serif", Font.BOLD, 18);
        Font smallFont = new Font("Serif", Font.BOLD, 14);

        welcomeLabel.setFont(largeFont);
        panel.add(welcomeLabel);
        welcomeLabel.setBounds(100, 0, 600, 200);

        questionLabel.setFont(mediumFont);
        panel.add(questionLabel);
        questionLabel.setBounds(100, 50, 500, 200);

        createMapButton.setFont(smallFont);
        createMapButton.setBackground(Color.GRAY);
        panel.add(createMapButton);
        createMapButton.setBounds(100, 200, 200, 40);

        editMapButton.setFont(smallFont);
        editMapButton.setBackground(Color.GRAY);
        panel.add(editMapButton);
        editMapButton.setBounds(350, 200, 200, 40);

        playMapButton.setFont(smallFont);
        playMapButton.setBackground(Color.GRAY);
        panel.add(playMapButton);
        playMapButton.setBounds(100, 300, 200, 40);

        exitButton.setFont(smallFont);
        exitButton.setBackground(Color.GRAY);
        panel.add(exitButton);
        exitButton.setBounds(350, 300, 200, 40);

    }

    /**
     * @param actionListener Listens to actions for each button from controller class
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        createMapButton.addActionListener(actionListener);
        editMapButton.addActionListener(actionListener);
        playMapButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
    }

    /**
     * @param o   getting reference from model
     * @param arg getting argument from observable model
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
