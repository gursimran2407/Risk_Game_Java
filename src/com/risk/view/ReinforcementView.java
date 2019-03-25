package com.risk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.DefaultCaret;

import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;

/**
 * This View provides the reinforcementView of the Game Play. It also provides
 * the observer pattern when the data is modified
 *
 * @author Namita
 * @version 1.0.0
 *
 */

public class ReinforcementView extends JFrame implements Observer {

    public GameMapModel gameMapModel;
    private PlayerModel playerModel;
    public GamePlayModel gamePlayModel;

    private JPanel welcomePanel;
    private JPanel graphicPanel;

    private JTextArea consoleTextArea;

    public JLabel welcomeLabel;

    public JComboBox<Integer> numOfTroopsComboBox;
    public JButton addButton;
    public JButton addMoreButton;
    public JButton exitCardButton;
    public JTextField cardIdField;

    public JComboBox<Object> countryListComboBox;

    public JButton[] button;

    /**
     * Instantiates a new reinforcement view.
     *
     * @param gamePlayModel the game play model
     */
    public ReinforcementView(GamePlayModel gamePlayModel) {
        this.gameMapModel = gamePlayModel.getGameMap();
        this.setTitle("Reinforcement Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 840);
        this.setResizable(false);
        this.setVisible(false);
        this.addButton = new JButton("Add");
        this.addMoreButton = new JButton("Add More Card");
        this.exitCardButton = new JButton("Exit Card Addition");
        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        this.add(graphicPanel);
        graphicPanel.setSize(1200, 650);
        graphicPanel.setBackground(Color.WHITE);

        JPanel consoleMainPanel = new JPanel();
        consoleMainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        this.consoleTextArea = new JTextArea("Life is a risk, instead play risk !!!\n", 10, 500);
        this.consoleTextArea.setEditable(false);
        this.consoleTextArea.setFocusable(false);
        this.consoleTextArea.setVisible(true);
        this.consoleTextArea.setForeground(Color.WHITE);
        this.consoleTextArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);

        JScrollPane consolePanel = new JScrollPane(this.consoleTextArea);
        consolePanel.setPreferredSize(new Dimension(1580, 130));

        consoleMainPanel.add(consolePanel, BorderLayout.WEST);

        this.getContentPane().add(consoleMainPanel, BorderLayout.SOUTH);

        this.addButton = new JButton("Add");
        this.add(welcomePanel);

        this.playerModel = this.gameMapModel.getPlayerTurn();
        this.welcomeLabel = new JLabel("It's " + playerModel.getNamePlayer() + "'s turn");

        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);

        updateWindow(gamePlayModel, playerModel);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for Reinforcement Phase
     *
     * @param gamePlayModel Game play model
     * @param playerModel Player model
     */
    private void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {
        Font largeFont = new Font("Serif", Font.BOLD, 18);

        this.gamePlayModel = gamePlayModel;
        this.gameMapModel = gamePlayModel.getGameMap();
        this.playerModel = this.gameMapModel.getPlayerTurn();

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        if (this.gamePlayModel.getConsoleText() != null) {
            this.consoleTextArea.setText(this.gamePlayModel.getConsoleText().toString());
        }

        if (this.playerModel.getOwnedCards().size() > 0 && this.playerModel.getShowReinforcementCard()) {
            JLabel reinnforcementCardLabel = new JLabel("Enter the id of the card :");
            reinnforcementCardLabel.setBounds(1300, 120, 150, 25);
            welcomePanel.add(reinnforcementCardLabel);

            cardIdField = new JTextField();
            cardIdField.setBounds(1300, 150, 150, 25);
            welcomePanel.add(cardIdField);

            this.addMoreButton.setBounds(1300, 190, 150, 35);
            welcomePanel.add(this.addMoreButton);

            this.exitCardButton.setBounds(1300, 230, 150, 35);
            welcomePanel.add(this.exitCardButton);
        }
        if (this.playerModel.getOwnedCards().size() == 0 || !this.playerModel.getShowReinforcementCard()) {
            JLabel noOfTroopsLabel = new JLabel("Number of Troops :");
            noOfTroopsLabel.setBounds(1300, 120, 150, 25);
            welcomePanel.add(noOfTroopsLabel);

            Integer[] troops = new Integer[this.gameMapModel.getPlayerTurn().getremainTroop()];
            for (int i = 0; i < this.gameMapModel.getPlayerTurn().getremainTroop(); i++) {
                troops[i] = i + 1;
            }

            numOfTroopsComboBox = new JComboBox<>(troops);
            numOfTroopsComboBox.setBounds(1300, 150, 150, 25);
            welcomePanel.add(numOfTroopsComboBox);

            JLabel countryListLabel = new JLabel("Select Country :");
            countryListLabel.setBounds(1300, 180, 150, 25);
            welcomePanel.add(countryListLabel);

            ArrayList<CountryModel> listOfCountries = new ArrayList<>();
            for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
                if (playerModel.getNamePlayer().equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
                    listOfCountries.add(this.gameMapModel.getCountries().get(i));
                }
            }

            /* The countries view renderer. */
            CountryViewRenderer countriesViewRenderer = new CountryViewRenderer();
            Object[] countryListArray = listOfCountries.toArray();
            countryListComboBox = new JComboBox<>(countryListArray);
            welcomePanel.add(this.countryListComboBox);

            if (countryListArray.length > 0) {
                countryListComboBox.setRenderer(countriesViewRenderer);
            }
            countryListComboBox.setBounds(1300, 220, 150, 25);

            this.addButton.setBounds(1300, 260, 150, 25);
            welcomePanel.add(this.addButton);
        }

        int n = this.gameMapModel.getCountries().size();
        button = new JButton[n];

        for (int i = 0; i < gameMapModel.getCountries().size(); i++) {

            button[i] = new JButton();
            button[i].setText(gameMapModel.getCountries().get(i).getCountryCode());
            button[i].setBackground(gameMapModel.getCountries().get(i).getBackgroundColor());
            button[i].setToolTipText("Troops: " + gameMapModel.getCountries().get(i).getArmies());
            CountryModel cm = gameMapModel.getCountries().get(i);
            PlayerModel pm = gamePlayModel.getPlayer(cm);
            Color col = pm.getColor();
            Border border = BorderFactory.createLineBorder(col, 3);

            button[i].setBorder(border);
            button[i].setOpaque(true);
            button[i].setBounds(gameMapModel.getCountries().get(i).getXPosition() * 2,
                    gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);

            graphicPanel.add(button[i]);
        }

    }

    /**
     * Countries are rendered as button and linked with Swing using Graphics.
     *
     * @see java.awt.Window#paint(java.awt.Graphics)
     */
    public void paint(final Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Point[] connectorPoints = new Point[this.gameMapModel.getCountries().size()];

        for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(this.gameMapModel.getCountries().get(i), 0, 0, this);

        }

        for (int k = 0; k < this.gameMapModel.getCountries().size(); k++) {
            if (this.gameMapModel.getCountries().get(k).getLinkedCountries() != null) {
                ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.gameMapModel.getCountries()
                        .get(k).getLinkedCountries();

                for (CountryModel neighbourCountry : neighbourCountries) {
                    for (int i = 0; i < this.gameMapModel.getCountries().size(); i++)
                        if (neighbourCountry.equals(this.gameMapModel.getCountries().get(i)))
                            g2.drawLine(
                                    connectorPoints[i].x + 25,
                                    connectorPoints[i].y + 25,
                                    connectorPoints[k].x + 25,
                                    connectorPoints[k].y + 25);

                }
            }
        }

    }

    /**
     * Getter method that provides us a map model corresponding to a map name
     *
     */

    public class CountryViewRenderer extends BasicComboBoxRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            CountryModel countryModel = (CountryModel) value;
            if (countryModel != null)
                setText(countryModel.getCountryName());

            return this;
        }
    }

    /**
     * Update method is to Update the start up Phase. This is declared as
     * observable. so when the values are changed the view is updated automatically
     * by notifying the observer.
     *
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        welcomePanel.removeAll();
        graphicPanel.removeAll();
        if (obs instanceof GameMapModel) {
            this.gameMapModel = (GameMapModel) obs;
        } else if (obs instanceof PlayerModel) {
            this.playerModel = (PlayerModel) obs;
        } else if (obs instanceof GamePlayModel) {
            this.gamePlayModel = (GamePlayModel) obs;
            this.gameMapModel = this.gamePlayModel.getGameMap();
        }
        this.updateWindow(this.gamePlayModel, this.playerModel);
        this.revalidate();
        this.repaint();

    }

    /**
     * This is actionListener method to listen the action events in the screen
     *
     * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
     */
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.addMoreButton.addActionListener(actionListener);
        this.exitCardButton.addActionListener(actionListener);
    }
}