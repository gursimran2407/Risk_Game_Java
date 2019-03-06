package com.risk.view.awt.main;

import com.risk.view.awt.AWTAbstractView;
import com.risk.view.main.IMainGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author gursimransingh
 */
public class AWTMainGameView extends AWTAbstractView implements IMainGameView {

    private static final Font FONT_LARGE = new Font("Serif", Font.BOLD, 30);
    private static final Font FONT_MEDIUM = new Font("Serif", Font.BOLD, 18);
    private static final Font FONT_SMALL = new Font("Serif", Font.BOLD, 14);

    private JButton btnCreateMap = null;
    private JButton btnEditMap = null;
    private JButton btnPlayMap = null;
    private JButton btnExit = null;

    /**
     * Constructor to create welcome screen that is start of the game.
     */
    public AWTMainGameView() throws HeadlessException {
        createUI();
    }

    /**
     * The createUI method updates the UI
     */
    private void createUI() {

        // Windows setup
        this.setName("Risk Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(700, 500);
        this.setResizable(false);
        this.setVisible(false);

        // Root panel
        final JPanel pnlRoot = new JPanel();
        pnlRoot.setBackground(Color.ORANGE);
        pnlRoot.setLayout(null);
        this.add(pnlRoot);

        // Welcome message
        final JLabel lblWelcome = new JLabel("Welcome to the risky game of \"RISK\"");
        lblWelcome.setFont(FONT_LARGE);
        lblWelcome.setForeground(Color.BLACK);
        lblWelcome.setBounds(100, 0, 600, 200);
        pnlRoot.add(lblWelcome);

        // Question message
        final JLabel lblQuestion = new JLabel("Select the required Option");
        lblQuestion.setFont(FONT_MEDIUM);
        lblQuestion.setForeground(Color.BLACK);
        lblQuestion.setBounds(103, 50, 500, 180);
        pnlRoot.add(lblQuestion);

        // Buttons
        pnlRoot.add(btnCreateMap = createButton("Create Map", 100, 160, 200, 40));
        pnlRoot.add(btnEditMap = createButton("Edit Map", 100, 210, 200, 40));
        pnlRoot.add(btnPlayMap = createButton("Play", 100, 260, 200, 40));
        pnlRoot.add(btnExit = createButton("Exit", 100, 310, 200, 40));
    }

    private JButton createButton(
            final String label, final int x, final int y, final int width, final int height) {
        final JButton button = new JButton(label);
        button.setFont(FONT_SMALL);
        button.setBackground(Color.GRAY);
        button.setBounds(x, y, width, height);

        return button;
    }

    @Override
    public void addCreateMapListener(final ActionListener listener) {
        btnCreateMap.addActionListener(listener);
    }

    @Override
    public void addEditMapListener(final ActionListener listener) {
        btnEditMap.addActionListener(listener);
    }

    @Override
    public void addPlayMapListener(final ActionListener listener) {
        btnPlayMap.addActionListener(listener);
    }

    @Override
    public void addExitListener(final ActionListener listener) {
        btnExit.addActionListener(listener);
    }
}
