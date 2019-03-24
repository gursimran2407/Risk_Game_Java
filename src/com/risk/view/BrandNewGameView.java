package com.risk.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrandNewGameView extends JFrame implements ViewInterface {

    public JButton browse;
    public JButton nextButton;
    public JButton cancelButton;
    public JComboBox<Integer> numOfPlayers;
    public JFileChooser chooseMap;
    public JLabel labelPlayers, labelMapFile, text, textHeading;
    public int finalPlayers;
    public JTextField player1, player2, player3, player4, player5;

    /**
     * Create the application.
     */
    public BrandNewGameView() {
        getContentPane().setLayout(null);

        labelPlayers = new JLabel("Number of Players?");
        labelPlayers.setBounds(53, 47, 311, 27);
        getContentPane().add(labelPlayers);

        Integer[] items = { 2, 3, 4, 5 };
        numOfPlayers = new JComboBox<>(items);
        numOfPlayers.setBounds(202, 49, 116, 22);
        getContentPane().add(numOfPlayers);

        textHeading = new JLabel("Fill the names as per the number selected");
        textHeading.setBounds(53, 83, 311, 27);
        getContentPane().add(textHeading);

        text = new JLabel("Player 1 Name: ");
        text.setBounds(53, 110, 311, 27);
        getContentPane().add(text);

        player1 = new JTextField();
        player1.setBounds(202, 116, 100, 20);
        getContentPane().add(player1);

        text = new JLabel("Player 2 Name: ");
        text.setBounds(53, 140, 311, 27);
        getContentPane().add(text);

        player2 = new JTextField();
        player2.setBounds(202, 146, 100, 20);
        getContentPane().add(player2);

        text = new JLabel("Player 3 Name: ");
        text.setBounds(53, 170, 311, 27);
        getContentPane().add(text);

        player3 = new JTextField();
        player3.setBounds(202, 176, 100, 20);
        getContentPane().add(player3);

        text = new JLabel("Player 4 Name: ");
        text.setBounds(53, 200, 311, 27);
        getContentPane().add(text);

        player4 = new JTextField();
        player4.setBounds(202, 206, 100, 20);
        getContentPane().add(player4);

        text = new JLabel("Player 5 Name: ");
        text.setBounds(53, 230, 311, 27);
        getContentPane().add(text);

        player5 = new JTextField();
        player5.setBounds(202, 236, 100, 20);
        getContentPane().add(player5);

        labelMapFile = new JLabel("Please Select Map File");
        labelMapFile.setBounds(53, 133, 157, 27);
        getContentPane().add(labelMapFile);

        browse = new JButton("Browse");
        browse.setBounds(202, 134, 116, 27);
        getContentPane().add(browse);

        nextButton = new JButton("Next");
        nextButton.setBounds(202, 237, 116, 25);
        getContentPane().add(nextButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(53, 237, 97, 25);
        getContentPane().add(cancelButton);

        chooseMap = new JFileChooser();

        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Set the Action Listener
     *
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        browse.addActionListener(actionListener);
        nextButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);

    }

    /**
     * Update the view based on observer
     *
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
