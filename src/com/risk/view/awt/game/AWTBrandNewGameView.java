package com.risk.view.awt.game;

import com.risk.view.awt.AWTAbstractView;
import com.risk.view.game.IBrandNewGameView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

/**
 * This class AWTBrandNewGameView is view of the game play. It also provides
 * the observer pattern when the data is modified.
 */
public class AWTBrandNewGameView extends AWTAbstractView implements IBrandNewGameView {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The browse map. */
    private JButton browseMapButton;

    /** The next button. */
    private JButton nextButton;

    /** The cancel button. */
    private JButton cancelButton;

    /** The choose map. */
    private JComboBox<Integer> numOfPlayers;

    /**
     * Create the application.
     */
    public AWTBrandNewGameView() {
        getContentPane().setLayout(null);

        final JLabel labelPlayers = new JLabel("Number of Players?");
        labelPlayers.setBounds(53, 47, 311, 27);
        getContentPane().add(labelPlayers);

        Integer[] items = { 2, 3, 4, 5 };
        numOfPlayers = new JComboBox<>(items);
        numOfPlayers.setBounds(202, 49, 116, 22);
        getContentPane().add(numOfPlayers);

        final JLabel labelMapFile = new JLabel("Please Select Map File");
        labelMapFile.setBounds(53, 133, 157, 27);
        getContentPane().add(labelMapFile);

        browseMapButton = new JButton("Browse");
        browseMapButton.setBounds(202, 134, 116, 27);
        getContentPane().add(browseMapButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(202, 237, 116, 25);
        getContentPane().add(nextButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(53, 237, 97, 25);
        getContentPane().add(cancelButton);

        initialize();

    }

    /**
     * adds the add browse map Action Listener
     */
    @Override
    public void addBrowseMapListener(ActionListener listener) {
        browseMapButton.addActionListener(listener);

    }
    /**
     * adds the add play Action Listener
     */
    @Override
    public void addPlayListener(Consumer<Integer> listener) {
        nextButton.addActionListener(e -> listener.accept((int) numOfPlayers.getSelectedItem()));
    }
    /**
     * adds the add cancel Action Listener
     */
    @Override
    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
