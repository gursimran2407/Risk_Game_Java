package com.risk.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.risk.helperInterfaces.View;


/**
 * This is the Welcome Screen of the game. It displays Welcome Message and the
 * options for user to start the game with default map or to create/edit any map
 * before starting the game
 *
 * @author KaranPannu
 */
public class WelcomeScreenView extends JFrame implements View {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5402570613272324763L;

    /** The create map button. */
    public JButton createMapButton = new JButton("Create Map");

    /** The edit map button. */
    public JButton editMapButton = new JButton("Edit Map");

    /** The play map button. */
    public JButton playMapButton = new JButton("Play");

    /** The exit button. */
    public JButton exitButton = new JButton("Exit");

    /**
     * Constructor.
     */
    public WelcomeScreenView() {
        // Sets up the view and adds the components
        JPanel welcomePanel = new JPanel();
        this.setName("RISK GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(800, 700);
        this.setResizable(false);
        this.setVisible(false);

        welcomePanel.setLayout(null);
        this.add(welcomePanel);

        Font largeFont = new Font("Serif", Font.BOLD, 30);
        Font mediumFont = new Font("Serif", Font.BOLD, 18);
        Font smallFont = new Font("Serif", Font.BOLD, 14);

        /* The welcome label. */
        JLabel welcomeLabel = new JLabel("<html>WELCOME TO ONLINE \"RISK\" GAME</html>");
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);
        welcomeLabel.setBounds(100, 0, 600, 200);

        /* The question text. */
        JLabel questionLabel = new JLabel("<html>PLEASE SELECT AN OPTION</html>");
        questionLabel.setFont(mediumFont);
        welcomePanel.add(questionLabel);
        questionLabel.setBounds(100, 50, 500, 200);

        createMapButton.setFont(smallFont);
        welcomePanel.add(createMapButton);
        createMapButton.setBounds(100, 200, 200, 40);

        editMapButton.setFont(smallFont);
        welcomePanel.add(editMapButton);
        editMapButton.setBounds(100, 300, 200, 40);

        playMapButton.setFont(smallFont);
        welcomePanel.add(playMapButton);
        playMapButton.setBounds(100, 400, 200, 40);

        exitButton.setFont(smallFont);
        welcomePanel.add(exitButton);
        exitButton.setBounds(100, 500, 200, 40);

    }

    /**
     * Set the Action Listener.
     *
     * @param actionListener the new action listener
     * @see com.risk.helperInterfaces.View#setActionListener(java.awt.event.ActionListener)
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        createMapButton.addActionListener(actionListener);

        editMapButton.addActionListener(actionListener);

        playMapButton.addActionListener(actionListener);

        exitButton.addActionListener(actionListener);

    }

    /**
     * Update the view based on changes.
     *
     * @param arg0 the arg 0
     * @param arg1 the arg 1
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable arg0, Object arg1) {

    }

}
