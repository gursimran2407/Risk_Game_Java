package com.risk.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;

import com.risk.helperInterfaces.View;
import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;


/**
 * This View provides the attackView of the Game Play. It also provides the
 * observer pattern when the data is modified
 *
 * @author KaranPannu
 * @version 1.0.0
 *
 */

public class AttackView extends JFrame implements View, ItemListener {

    /** The game map model. */
    public GameMapModel gameMapModel;

    /** The player model. */
    private PlayerModel playerModel;

    /** The game play model. */
    public GamePlayModel gamePlayModel;

    /** The welcome panel. */
    private JPanel welcomePanel;

    /** The graphic panel. */
    private JPanel graphicPanel;

    /** The console text area. */
    private JTextArea consoleTextArea;

    /** The welcome label. */
    public JLabel welcomeLabel;

    /** The next button. */
    public JButton nextButton;

    /** The move button. */
    public JButton moveButton;

    /** The Single button. */
    public JButton SingleButton;

    /** The allout button. */
    public JButton alloutButton;

    /** The attack country list combo box. */
    public JComboBox<Object> attackCountryListComboBox;

    /** The defend country list combo box. */
    public JComboBox<Object> defendCountryListComboBox;

    /** The button. */
    public JButton[] button;

    /** The num of dice attack combo box. */
    public JComboBox<Integer> numOfDiceAttackComboBox;

    /** The num of dice defend combo box. */
    public JComboBox<Integer> numOfDiceDefendComboBox;

    /** The num of armies to be moved combo box. */
    public JComboBox<Integer> numOfArmiesToBeMovedComboBox;

    /**
     * Instantiates a new attack view.
     *
     * @param gamePlayModel the game play model
     */
    public AttackView(GamePlayModel gamePlayModel) {
        this.gameMapModel = gamePlayModel.getGameMap();
        this.setTitle("Attack Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 840);
        this.setResizable(false);
        this.setVisible(false);
        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        this.add(graphicPanel);
        graphicPanel.setSize(1200, 650);
        graphicPanel.setBackground(Color.WHITE);

        /* The console main panel. */
        JPanel consoleMainPanel = new JPanel();
        consoleMainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.consoleTextArea = new JTextArea("Attack Phase !!!\n", 10, 500);
        this.consoleTextArea.setEditable(false);
        this.consoleTextArea.setFocusable(false);
        this.consoleTextArea.setVisible(true);
        this.consoleTextArea.setForeground(Color.WHITE);
        this.consoleTextArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        /* The console panel. */
        JScrollPane consolePanel = new JScrollPane(this.consoleTextArea);
        consolePanel.setPreferredSize(new Dimension(1580, 130));
        consoleMainPanel.add(consolePanel,BorderLayout.WEST);
        this.getContentPane().add(consoleMainPanel,BorderLayout.SOUTH);


        this.nextButton = new JButton("Next");
        this.moveButton = new JButton("Move");
        this.SingleButton = new JButton("Single attack");
        this.alloutButton = new JButton("All Out");
        this.add(welcomePanel);

        this.playerModel = this.gameMapModel.getPlayerTurn();
        this.welcomeLabel = new JLabel("It's " + playerModel.getNamePlayer() + "'s turn");

        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);

        updateWindow(gamePlayModel, playerModel);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for attack Phase
     *
     * @param gamePlayModel the game play model
     * @param playerModel the player model
     */
    private void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {
        Font largeFont = new Font("Serif", Font.BOLD, 18);

        this.gamePlayModel = gamePlayModel;
        this.gameMapModel = gamePlayModel.getGameMap();
        this.playerModel = this.gameMapModel.getPlayerTurn();

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        if(this.gamePlayModel.getConsoleText()!=null) {
            this.consoleTextArea.setText(this.gamePlayModel.getConsoleText().toString());
        }

        /* The attack country list label. */
        JLabel attackCountryListLabel = new JLabel("Select attacker country:");
        attackCountryListLabel.setBounds(1300, 140, 150, 25);
        welcomePanel.add(attackCountryListLabel);

        // attack country comboBox
        ArrayList<CountryModel> attackListOfCountries = new ArrayList<>();
        for (int i = 0; i < this.gameMapModel.getCountries().size(); i++) {
            if (playerModel.getNamePlayer().equals(this.gameMapModel.getCountries().get(i).getRulerName())) {
                if (this.gameMapModel.getCountries().get(i).getArmies() > 1) {
                    attackListOfCountries.add(this.gameMapModel.getCountries().get(i));
                }
            }
        }

        /* The countries view renderer. */
        CountryViewRenderer countriesViewRenderer = new CountryViewRenderer();
        /* The attack country list array. */
        Object[] attackCountryListArray = attackListOfCountries.toArray();

        if (!attackListOfCountries.isEmpty()) {
            attackCountryListComboBox = new JComboBox<>(attackCountryListArray);
            if (attackListOfCountries.size() > gamePlayModel.getSelectedAttackComboBoxIndex() ) {
                attackCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedAttackComboBoxIndex());
            }else {
                attackCountryListComboBox.setSelectedIndex(0);
            }
        }

        welcomePanel.add(this.attackCountryListComboBox);

        if (attackCountryListArray.length > 0) {
            attackCountryListComboBox.setRenderer(countriesViewRenderer);
        }
        attackCountryListComboBox.setBounds(1300, 180, 150, 25);

        /* The no of dice attack label. */
        JLabel noOfDiceAttackLabel = new JLabel("Number of Dice to Roll(for attack) :");
        noOfDiceAttackLabel.setBounds(1300, 215, 200, 35);
        welcomePanel.add(noOfDiceAttackLabel);

        CountryModel attackCountry = (CountryModel) this.attackCountryListComboBox.getSelectedItem();
        System.out.print("country name " + Objects.requireNonNull(attackCountry).getCountryName());
        Integer[] attackTroops = new Integer[3];
        if (!attackListOfCountries.isEmpty()) {
            if (attackCountry.getArmies() > 3) {
                for (int i = 0; i < 3; i++) {
                    attackTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            } else {
                for (int i = 0; i < (attackCountry.getArmies() - 1); i++) {
                    attackTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            }
        }

        numOfDiceAttackComboBox = new JComboBox<>(attackTroops);
        numOfDiceAttackComboBox.setBounds(1300, 250, 150, 25);
        welcomePanel.add(numOfDiceAttackComboBox);

        /* The defend country list label. */
        JLabel defendCountryListLabel = new JLabel("Select defend Country :");
        defendCountryListLabel.setBounds(1300, 280, 150, 25);
        welcomePanel.add(defendCountryListLabel);

        // defend country comboBox
        ArrayList<CountryModel> defendListOfCountries = gamePlayModel.getDefendCountryList(attackCountry);

        countriesViewRenderer = new CountryViewRenderer();
        /* The defend country list array. */
        Object[] defendCountryListArray = defendListOfCountries.toArray();

        defendCountryListComboBox = new JComboBox<>(defendCountryListArray);
        System.out.println("defendListOfCountries " + defendListOfCountries.size());
        if (!defendListOfCountries.isEmpty()) {
            if (defendListOfCountries.size() > gamePlayModel.getSelectedDefendComboBoxIndex() ) {
                defendCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedDefendComboBoxIndex());
            }else {
                defendCountryListComboBox.setSelectedIndex(0);
            }
        }

        welcomePanel.add(this.defendCountryListComboBox);

        if (defendCountryListArray.length > 0) {
            defendCountryListComboBox.setRenderer(countriesViewRenderer);
        }
        defendCountryListComboBox.setBounds(1300, 310, 150, 25);

        /* The no of dice defend label. */
        JLabel noOfDiceDefendLabel = new JLabel("Number of Dice to Roll(for defend) :");
        noOfDiceDefendLabel.setBounds(1300, 350, 200, 45);
        welcomePanel.add(noOfDiceDefendLabel);

        Integer[] defendTroops = new Integer[2];

        if (!defendListOfCountries.isEmpty()) {
            CountryModel defendCountry = (CountryModel) this.defendCountryListComboBox.getSelectedItem();
            assert defendCountry != null;
            if (defendCountry.getArmies() > 2) {
                for (int i = 0; i < 2; i++) {
                    defendTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            } else {
                for (int i = 0; i < defendCountry.getArmies(); i++) {
                    defendTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            }
        }

        numOfDiceDefendComboBox = new JComboBox<>(defendTroops);
        numOfDiceDefendComboBox.setBounds(1300, 380, 150, 25);
        welcomePanel.add(numOfDiceDefendComboBox);

        this.SingleButton.setBounds(1210, 450, 150, 25);
        welcomePanel.add(this.SingleButton);

        this.alloutButton.setBounds(1375, 450, 150, 25);
        welcomePanel.add(this.alloutButton);

        if (!gamePlayModel.getArmyToMoveText()) {
            this.SingleButton.setEnabled(true);
            this.alloutButton.setEnabled(true);
            this.attackCountryListComboBox.setEnabled(true);
        }

        // move troop after conquer
        if (gamePlayModel.getArmyToMoveText()) {
            this.attackCountryListComboBox.setEnabled(false);
            this.SingleButton.setEnabled(false);
            this.alloutButton.setEnabled(false);
            /* The defected country label. */
            JLabel defectedCountryLabel = new JLabel("Move troops to defeated country :");
            defectedCountryLabel.setBounds(1300, 480, 200, 45);
            welcomePanel.add(defectedCountryLabel);

            Integer[] moveTroops = new Integer[attackCountry.getArmies()];
            for (int i = 0; i < (attackCountry.getArmies() - 1); i++) {
                moveTroops[i] = i + 1;
            }

            numOfArmiesToBeMovedComboBox = new JComboBox<>(moveTroops);
            numOfArmiesToBeMovedComboBox.setBounds(1300, 510, 150, 25);
            welcomePanel.add(numOfArmiesToBeMovedComboBox);

            this.moveButton.setBounds(1300, 540, 150, 25);
            welcomePanel.add(this.moveButton);
        }

        this.nextButton.setBounds(1300, 600, 150, 25);
        welcomePanel.add(this.nextButton);

        int n = this.gameMapModel.getCountries().size();
        button = new JButton[n];

        for (int i = 0; i < gameMapModel.getCountries().size(); i++) {

            button[i] = new JButton();
            button[i].setText(gameMapModel.getCountries().get(i).getCountryCode());
            button[i].setBackground(gameMapModel.getCountries().get(i).getBackgroundColor());
            button[i].setToolTipText("Troops: " + gameMapModel.getCountries().get(i).getArmies());
            CountryModel cm = gameMapModel.getCountries().get(i);
            PlayerModel pm = gamePlayModel.getPlayer(cm);
            Border border = BorderFactory.createLineBorder(pm.getColor(), 3);

            button[i].setBorder(border);
            button[i].setOpaque(true);
            button[i].setBounds(gameMapModel.getCountries().get(i).getXPosition() * 2,
                    gameMapModel.getCountries().get(i).getYPosition() * 2, 50, 50);

            graphicPanel.add(button[i]);
        }

        this.setItemListener(this);
    }

    /**
     * Countries are rendered as button and linked with Swing using Graphics.
     *
     * @param g the g
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

                for (int j = 0; j < neighbourCountries.size(); j++) {
                    for (int i = 0; i < this.gameMapModel.getCountries().size(); i++)
                        if (neighbourCountries.get(j).equals(this.gameMapModel.getCountries().get(i)))
                            g2.drawLine(connectorPoints[i].x + 25, connectorPoints[i].y + 25, connectorPoints[k].x + 25,
                                    connectorPoints[k].y + 25);

                }
            }
        }

    }

    /**
     * Getter method that provides us a map model corresponding to a map name.
     */

    public class CountryViewRenderer extends BasicComboBoxRenderer {

        /* (non-Javadoc)
         * @see javax.swing.plaf.basic.BasicComboBoxRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
         */
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
     * @param obs the obs
     * @param arg the arg
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
     * This is actionListener method to listen the action events in the screen.
     *
     * @param actionListener the new action listener
     * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
     */
    public void setActionListener(ActionListener actionListener) {
        this.nextButton.addActionListener(actionListener);
        this.SingleButton.addActionListener(actionListener);
        this.alloutButton.addActionListener(actionListener);
        this.defendCountryListComboBox.addActionListener(actionListener);
        this.attackCountryListComboBox.addActionListener(actionListener);
        this.moveButton.addActionListener(actionListener);
    }

    /**
     * Sets the item listener.
     *
     * @param itemListener the new item listener
     */
    private void setItemListener(ItemListener itemListener) {
        this.attackCountryListComboBox.addItemListener(itemListener);
        this.defendCountryListComboBox.addItemListener(itemListener);

    }

    /**
     * Item Listener.
     *
     * @param itemEvent the item event
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.attackCountryListComboBox)) {
            this.gamePlayModel.setSelectedAttackComboBoxIndex(this.attackCountryListComboBox.getSelectedIndex());
        } else if (itemEvent.getSource().equals(this.defendCountryListComboBox)) {
            this.gamePlayModel.setSelectedDefendComboBoxIndex(this.defendCountryListComboBox.getSelectedIndex());
        }

    }

}