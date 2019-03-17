package com.risk.view;

import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author gursimransingh
 */
public class AttackPhaseView {
    public MapRiskModel mapRiskModel;

    /**
     * The player model.
     */
    public PlayerModel playerModel;

    /**
     * The game play model.
     */
    public GamePlayModel gamePlayModel;

    /**
     * The welcome panel.
     */
    public JPanel welcomePanel;

    /**
     * The graphic panel.
     */
    public JPanel graphicPanel;

    /**
     * The console main panel.
     */
    public JPanel consoleMainPanel;

    /**
     * The console panel.
     */
    public JScrollPane consolePanel;

    /**
     * The console text area.
     */
    public JTextArea consoleTextArea;

    /**
     * The welcome label.
     */
    public JLabel welcomeLabel;

    /**
     * The attack country list label.
     */
    public JLabel attackCountryListLabel;

    /**
     * The defend country list label.
     */
    public JLabel defendCountryListLabel;

    /**
     * The no of troops label.
     */
    public JLabel noOfTroopsLabel;

    /**
     * The no of dice attack label.
     */
    public JLabel noOfDiceAttackLabel;

    /**
     * The no of dice defend label.
     */
    public JLabel noOfDiceDefendLabel;

    /**
     * The defected country label.
     */
    public JLabel defectedCountryLabel;

    /**
     * The next button.
     */
    public JButton nextButton;

    /**
     * The move button.
     */
    public JButton moveButton;

    /**
     * The Single button.
     */
    public JButton SingleButton;

    /**
     * The allout button.
     */
    public JButton alloutButton;

    /**
     * The attack country list combo box.
     */
    public JComboBox<Object> attackCountryListComboBox;

    /**
     * The defend country list combo box.
     */
    public JComboBox<Object> defendCountryListComboBox;

    /**
     * The attack country list array.
     */
    public Object[] attackCountryListArray;

    /**
     * The defend country list array.
     */
    public Object[] defendCountryListArray;
    /**
     * The button.
     */
    public JButton[] button;
    /**
     * The num of dice attack combo box.
     */
    public JComboBox<Integer> numOfDiceAttackComboBox;
    /**
     * The num of dice defend combo box.
     */
    public JComboBox<Integer> numOfDiceDefendComboBox;
    /**
     * The num of armies to be moved combo box.
     */
    public JComboBox<Integer> numOfArmiesToBeMovedComboBox;
    /**
     * The countries view renderer.
     */
    private CountryViewRenderer countriesViewRenderer;

    public AttackPhaseView(GamePlayModel gamePlayModel) {
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

        this.playerModel = this.gameMapModel.getPlayerTurn();
        this.welcomeLabel = new JLabel("It's " + playerModel.getNamePlayer() + "'s turn");

        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);

        updateWindow(gamePlayModel, playerModel);
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
