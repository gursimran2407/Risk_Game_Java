package com.risk.view;

import com.risk.helperInterfaces.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;
public class NewGameView extends JFrame implements View {

    public JButton browseMapButton;
    public JButton nextButton;
    public JButton cancelButton;
    public JComboBox<Integer> numOfPlayers;
    public JFileChooser chooseMap;
    public JTextField playerName1, playerName2, playerName3, playerName4, playerName5;
    public JComboBox<String> playerType1, playerType2, playerType3, playerType4, playerType5;

    /**
     * Create the application.
     */
    public NewGameView() {
        getContentPane().setLayout(null);

        /* The label map file. */
        JLabel labelPlayers = new JLabel("Number of Players?");
        labelPlayers.setBounds(53, 47, 311, 27);
        getContentPane().add(labelPlayers);

        Integer[] items = { 2, 3, 4, 5 };
        numOfPlayers = new JComboBox<>(items);
        numOfPlayers.setBounds(202, 49, 116, 22);
        getContentPane().add(numOfPlayers);

        JLabel playerText2 = new JLabel("Fill the names as per the number selected");
        playerText2.setBounds(53, 83, 311, 27);
        getContentPane().add(playerText2);

        String[] playerType = new String[5];
        playerType[0] = "Human";
        playerType[1] = "Aggressive";
        playerType[2] = "Benevolent";
        playerType[3] = "Random";
        playerType[4] = "Cheater";

        JLabel playerText = new JLabel("Player 1 Name: ");
        playerText.setBounds(53, 110, 311, 27);
        getContentPane().add(playerText);

        playerType1 = new JComboBox(playerType);
        playerType1.setBounds(202, 116, 150, 27);
        getContentPane().add(playerType1);

        playerName1 = new JTextField();
        playerName1.setBounds(202, 116, 100, 20);
        getContentPane().add(playerName1);

        playerText = new JLabel("Player 2 Name: ");
        playerText.setBounds(53, 140, 311, 27);
        getContentPane().add(playerText);

        playerType2 = new JComboBox(playerType);
        playerType2.setBounds(202, 146, 150, 27);
        getContentPane().add(playerType2);

        playerName2 = new JTextField();
        playerName2.setBounds(202, 146, 100, 20);
        getContentPane().add(playerName2);

        playerText = new JLabel("Player 3 Name: ");
        playerText.setBounds(53, 170, 311, 27);
        getContentPane().add(playerText);

        playerType3 = new JComboBox(playerType);
        playerType3.setBounds(202, 176, 150, 27);
        getContentPane().add(playerType3);

        playerName3 = new JTextField();
        playerName3.setBounds(202, 176, 100, 20);
        getContentPane().add(playerName3);

        playerText = new JLabel("Player 4 Name: ");
        playerText.setBounds(53, 200, 311, 27);
        getContentPane().add(playerText);

        playerType4 = new JComboBox(playerType);
        playerType4.setBounds(202, 206, 150, 27);
        getContentPane().add(playerType4);

        playerName4 = new JTextField();
        playerName4.setBounds(202, 206, 100, 20);
        getContentPane().add(playerName4);

        playerText = new JLabel("Player 5 Name: ");
        playerText.setBounds(53, 230, 311, 27);
        getContentPane().add(playerText);

        playerType5 = new JComboBox(playerType);
        playerType5.setBounds(202, 236, 150, 27);
        getContentPane().add(playerType5);

        playerName5 = new JTextField();
        playerName5.setBounds(202, 236, 100, 20);
        getContentPane().add(playerName5);

        JLabel labelMapFile = new JLabel("Please Select Map File");
        labelMapFile.setBounds(53, 280, 157, 27);
        getContentPane().add(labelMapFile);

        browseMapButton = new JButton("Browse");
        browseMapButton.setBounds(202, 279, 116, 27);
        getContentPane().add(browseMapButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(202, 337, 116, 25);
        getContentPane().add(nextButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(53, 337, 97, 25);
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
     * @see com.risk.helperInterfaces.View#setActionListener(java.awt.event.ActionListener)
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        browseMapButton.addActionListener(actionListener);
        nextButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);

    }

    /**
     * Update the view based on observer
     *
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
    }
}
