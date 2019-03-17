package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * @author gursimransingh
 */
public class AttackPhaseView extends JFrame implements ViewInterface, ItemListener {
    public MapRiskModel mapRiskModel;

    public PlayerModel playerModel;
    public GamePlayModel gamePlayModel;
    public JPanel welcomePanel;
    public JPanel graphicPanel;
    public JPanel consoleMainPanel;
    public JScrollPane consolePanel;
    public JTextArea consoleTextArea;
    public JLabel welcomeLabel;
    public JLabel attackCountryListLabel;
    public JLabel defendCountryListLabel;
    public JLabel noOfTroopsLabel;
    public JLabel noOfDiceAttackLabel;
    public JLabel noOfDiceDefendLabel;
    public JLabel defectedCountryLabel;
    public JButton nextButton;
    public JButton moveButton;
    public JButton SingleButton;
    public JButton alloutButton;
    public JComboBox<Object> attackCountryListComboBox;
    public JComboBox<Object> defendCountryListComboBox;
    public Object[] attackCountryListArray;
    public Object[] defendCountryListArray;
    public JButton[] button;

    public JComboBox<Integer> numOfDiceAttackComboBox;
    public JComboBox<Integer> numOfDiceDefendComboBox;
    public JComboBox<Integer> numOfArmiesToBeMovedComboBox;
    private CountryViewRenderer countriesViewRenderer;

    public AttackPhaseView(GamePlayModel gamePlayModel) {
        this.mapRiskModel = gamePlayModel.getGameMap();
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

        this.consoleMainPanel = new JPanel();
        this.consoleMainPanel.setBorder(new BevelBorder(1));
        this.consoleTextArea = new JTextArea("Attack Phase !!!\n", 10, 500);
        this.consoleTextArea.setEditable(false);
        this.consoleTextArea.setFocusable(false);
        this.consoleTextArea.setVisible(true);
        this.consoleTextArea.setForeground(Color.WHITE);
        this.consoleTextArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        this.consolePanel = new JScrollPane(this.consoleTextArea);
        this.consolePanel.setPreferredSize(new Dimension(1580, 130));
        this.consoleMainPanel.add(this.consolePanel, BorderLayout.WEST);
        this.getContentPane().add(this.consoleMainPanel, BorderLayout.SOUTH);


        this.nextButton = new JButton("Next");
        this.moveButton = new JButton("Move");
        this.SingleButton = new JButton("Single attack");
        this.alloutButton = new JButton("All Out");
        this.add(welcomePanel);

        this.playerModel = this.mapRiskModel.getPlayerTurn();
        this.welcomeLabel = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");

        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);

        updateWindow(gamePlayModel, playerModel);
    }

    private void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {


        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.gamePlayModel = gamePlayModel;
        this.mapRiskModel = gamePlayModel.getGameMap();
        this.playerModel = this.mapRiskModel.getPlayerTurn();

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        if (this.gamePlayModel.getConsoleText() != null) {
            this.consoleTextArea.setText(this.gamePlayModel.getConsoleText().toString());
        }

        this.attackCountryListLabel = new JLabel("Select attacker country:");
        attackCountryListLabel.setBounds(1300, 140, 150, 25);
        welcomePanel.add(attackCountryListLabel);

        // attack country comboBox
        ArrayList<CountryModel> attackListOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName().equals(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner())) {
                if (this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies() > 1) {
                    attackListOfCountries.add(this.mapRiskModel.getCountryModelList().get(i));
                }
            }
        }

        countriesViewRenderer = new CountryViewRenderer();
        attackCountryListArray = attackListOfCountries.toArray();

        if (attackListOfCountries != null && !(attackListOfCountries.isEmpty())) {
            attackCountryListComboBox = new JComboBox(attackCountryListArray);
            if (attackListOfCountries.size() > gamePlayModel.getSelectedAttackComboBoxIndex()) {
                attackCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedAttackComboBoxIndex());
            } else {
                attackCountryListComboBox.setSelectedIndex(0);
            }
        }

        welcomePanel.add(this.attackCountryListComboBox);
        if (attackCountryListArray.length > 0) {
            attackCountryListComboBox.setRenderer(countriesViewRenderer);
        }
        attackCountryListComboBox.setBounds(1300, 180, 150, 25);

        this.noOfDiceAttackLabel = new JLabel("Number of Dice to Roll(for attack) :");
        noOfDiceAttackLabel.setBounds(1300, 215, 200, 35);
        welcomePanel.add(noOfDiceAttackLabel);

        CountryModel attackCountry = (CountryModel) this.attackCountryListComboBox.getSelectedItem();
        System.out.print("country name " + attackCountry.getCountryName());
        Integer[] attackTroops = new Integer[3];
        if (attackListOfCountries != null && !(attackListOfCountries.isEmpty())) {
            if (attackCountry.getNumberofArmies() > 3) {
                for (int i = 0; i < 3; i++) {
                    attackTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            } else {
                for (int i = 0; i < (attackCountry.getNumberofArmies() - 1); i++) {
                    attackTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            }
        }

        numOfDiceAttackComboBox = new JComboBox(attackTroops);
        numOfDiceAttackComboBox.setBounds(1300, 250, 150, 25);
        welcomePanel.add(numOfDiceAttackComboBox);

        this.defendCountryListLabel = new JLabel("Select defend Country :");
        defendCountryListLabel.setBounds(1300, 280, 150, 25);
        welcomePanel.add(this.defendCountryListLabel);

        // defend country comboBox
        ArrayList<CountryModel> defendListOfCountries = new ArrayList<CountryModel>();
        defendListOfCountries = gamePlayModel.getDefendCountryList(attackCountry);

        countriesViewRenderer = new CountryViewRenderer();
        defendCountryListArray = defendListOfCountries.toArray();

        defendCountryListComboBox = new JComboBox(defendCountryListArray);
        System.out.println("defendListOfCountries " + defendListOfCountries.size());
        if (defendListOfCountries != null && !(defendListOfCountries.isEmpty())) {
            if (defendListOfCountries.size() > gamePlayModel.getSelectedDefendComboBoxIndex()) {
                defendCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedDefendComboBoxIndex());
            } else {
                defendCountryListComboBox.setSelectedIndex(0);
            }
        }

        welcomePanel.add(this.defendCountryListComboBox);

        if (defendCountryListArray.length > 0) {
            defendCountryListComboBox.setRenderer(countriesViewRenderer);
        }
        defendCountryListComboBox.setBounds(1300, 310, 150, 25);

        this.noOfDiceDefendLabel = new JLabel("Number of Dice to Roll(for defend) :");
        noOfDiceDefendLabel.setBounds(1300, 350, 200, 45);
        welcomePanel.add(noOfDiceDefendLabel);

        Integer[] defendTroops = new Integer[2];

        if (defendListOfCountries != null && !(defendListOfCountries.isEmpty())) {
            CountryModel defendCountry = (CountryModel) this.defendCountryListComboBox.getSelectedItem();
            if (defendCountry.getNumberofArmies() > 2) {
                for (int i = 0; i < 2; i++) {
                    defendTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            } else {
                for (int i = 0; i < defendCountry.getNumberofArmies(); i++) {
                    defendTroops[i] = i + 1;
                    System.out.println("i " + i);
                }
            }
        }

        numOfDiceDefendComboBox = new JComboBox(defendTroops);
        numOfDiceDefendComboBox.setBounds(1300, 380, 150, 25);
        welcomePanel.add(numOfDiceDefendComboBox);

        this.SingleButton.setBounds(1210, 450, 150, 25);
        welcomePanel.add(this.SingleButton);

        this.alloutButton.setBounds(1375, 450, 150, 25);
        welcomePanel.add(this.alloutButton);

        if (gamePlayModel.getArmyToMoveText() == false) {
            this.SingleButton.setEnabled(true);
            this.alloutButton.setEnabled(true);
            this.attackCountryListComboBox.setEnabled(true);
        }

        // move troop after conquer
        if (gamePlayModel.getArmyToMoveText() == true) {
            this.attackCountryListComboBox.setEnabled(false);
            this.SingleButton.setEnabled(false);
            this.alloutButton.setEnabled(false);
            this.defectedCountryLabel = new JLabel("Move troops to defeated country :");
            defectedCountryLabel.setBounds(1300, 480, 200, 45);
            welcomePanel.add(defectedCountryLabel);

            Integer[] moveTroops = new Integer[attackCountry.getArmies()];
            for (int i = 0; i < (attackCountry.getArmies() - 1); i++) {
                moveTroops[i] = i + 1;
            }

            numOfArmiesToBeMovedComboBox = new JComboBox(moveTroops);
            numOfArmiesToBeMovedComboBox.setBounds(1300, 510, 150, 25);
            welcomePanel.add(numOfArmiesToBeMovedComboBox);

            this.moveButton.setBounds(1300, 540, 150, 25);
            welcomePanel.add(this.moveButton);
        }

        this.nextButton.setBounds(1300, 600, 150, 25);
        welcomePanel.add(this.nextButton);

        int n = this.gameMapModel.getCountries().size();
        button = new JButton[n];

        PlayerModel pm = new PlayerModel();
        CountryModel cm = new CountryModel();

        for (int i = 0; i < gameMapModel.getCountries().size(); i++) {

            button[i] = new JButton();
            button[i].setText(gameMapModel.getCountries().get(i).getCountryName().substring(0, 3));
            button[i].setBackground(gameMapModel.getCountries().get(i).getBackgroundColor());
            button[i].setToolTipText("Troops: " + gameMapModel.getCountries().get(i).getArmies());
            cm = gameMapModel.getCountries().get(i);
            pm = gamePlayModel.getPlayer(cm);
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

        Point[] connectorPoints = new Point[this.mapRiskModel().size()];

        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(this.mapRiskModel.getCountryModelList().get(i), 0, 0, this);

        }

        for (int k = 0; k < this.mapRiskModel.getCountryModelList().size(); k++) {
            if (this.mapRiskModel.getCountryModelList().get(k).getConnectedCountryList() != null) {
                ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.mapRiskModel.getCountryModelList()
                        .get(k).getConnectedCountryList();

                for (int j = 0; j < neighbourCountries.size(); j++) {
                    for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++)
                        if (neighbourCountries.get(j).equals(this.mapRiskModel.getCountryModelList().get(i)))
                            g2.drawLine(connectorPoints[i].x + 25, connectorPoints[i].y + 25, connectorPoints[k].x + 25,
                                    connectorPoints[k].y + 25);

                }
            }
        }

    }

    public class CountryViewRenderer extends BasicComboBoxRenderer {

        /**
         * (non-Javadoc)
         *
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

}
