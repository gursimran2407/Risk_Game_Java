package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrandNewGameView extends JFrame implements ViewInterface {
    public JButton openMapButton;
    public JButton nextButton;
    public JButton cancelButton;

    public JComboBox<Integer> numberOfPlayersComboBox;
    public JFileChooser openMapFileChooser;

    public JLabel playerLabel, mapNameLabel;

    public BrandNewGameView() throws HeadlessException {
        getContentPane().setLayout(null);

        playerLabel = new JLabel("Number of Players?");
        playerLabel.setBounds(53, 47, 311, 27);
        getContentPane().add(playerLabel);

        Integer[] items = {2, 3, 4, 5};
        numberOfPlayersComboBox = new JComboBox<>(items);
        numberOfPlayersComboBox.setBounds(202, 49, 116, 22);
        getContentPane().add(numberOfPlayersComboBox);

        mapNameLabel = new JLabel("Please Select Map File");
        mapNameLabel.setBounds(53, 133, 157, 27);
        getContentPane().add(mapNameLabel);

        openMapButton = new JButton("Browse");
        openMapButton.setBounds(202, 134, 116, 27);
        getContentPane().add(openMapButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(202, 237, 116, 25);
        getContentPane().add(nextButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(53, 237, 97, 25);
        getContentPane().add(cancelButton);

        openMapFileChooser = new JFileChooser();

        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        openMapButton.addActionListener(actionListener);
        nextButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
