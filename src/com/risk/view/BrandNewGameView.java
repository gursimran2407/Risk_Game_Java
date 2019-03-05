package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * This class is to represents the start of th game and updates the view
 */

public class BrandNewGameView extends JFrame implements ViewInterface {

    /** The browse map. */
    public JButton browse;

    /** The next button. */
    public JButton nextButton;

    /** The cancel button. */
    public JButton cancelButton;

    /** The choose map. */
    public JComboBox<Integer> numOfPlayers;

    public JFileChooser chooseMap;

    /** The label map file. */
    public JLabel labelPlayers, labelMapFile;

    /** The final players. */
    public int finalPlayers;

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
