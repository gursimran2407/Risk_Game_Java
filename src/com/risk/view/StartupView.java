package com.risk.view;

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
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class represents the armies and countries allocated to player in the starting of the game
 * @author gursimransingh
 */
public class StartupView extends JFrame implements ViewInterface {

    private static final long serialVersionUID = 1L;
    public MapRiskModel mapRiskModel;
    public PlayerModel playerModel;
    public GamePlayModel gamePlayModel;

    public JPanel welcomePanel;
    public JPanel graphicPanel;

    public JPanel consoleMainPanel;
    public JScrollPane consolePanel;
    public JTextArea consoleTextArea;

    public JLabel welcomeLabel;
    public JLabel welcomeLabel1;
    public JLabel noOfArmiesLabel;

    public JComboBox<Integer> numOfArmiesComboBox;
    public JButton addButton;

    public JLabel countryListLabel;
    public JComboBox<Object> countryListComboBox;
    public Object[] countryListArray;
    private CountryViewRenderer countriesViewRenderer;

    public JButton[] button;
    public JButton nextButton;

    /**
     * This is the constructor for Start Up Phase View where the variables are initialized
     *
     * @param gamePlayModel
     * @param playerModel
     */
    public StartupView(GamePlayModel gamePlayModel, PlayerModel playerModel) {

        this.setTitle("Startup Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 840);
        this.setResizable(false);
        this.setVisible(false);
        this.addButton = new JButton("Add");
        this.nextButton = new JButton("Next");
        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        this.add(graphicPanel);
        graphicPanel.setSize(1200, 650);
        graphicPanel.setBackground(Color.WHITE);
        graphicPanel.setLayout(null);

        this.consoleMainPanel = new JPanel();
        this.consoleMainPanel.setBorder(new BevelBorder(1));
        this.consoleTextArea = new JTextArea("Start up Phase !!!\n", 10, 500);
        this.consoleTextArea.setEditable(false);
        this.consoleTextArea.setFocusable(false);
        this.consoleTextArea.setVisible(true);
        this.consoleTextArea.setForeground(Color.WHITE);
        this.consoleTextArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        this.consolePanel = new JScrollPane(this.consoleTextArea);
        this.consolePanel.setPreferredSize(new Dimension(1580, 130));
        this.consoleMainPanel.add(this.consolePanel,BorderLayout.WEST);
        this.getContentPane().add(this.consoleMainPanel,BorderLayout.SOUTH);
        this.addButton = new JButton("Add");
        this.add(welcomePanel);
        this.playerModel = playerModel;
        this.gamePlayModel = gamePlayModel;
        this.welcomeLabel = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");
        this.welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getRemainingNumberOfArmies());
        updateWindow(gamePlayModel, playerModel);
        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for Start Up Phase
     *
     * @param gamePlayModel
     * @param playerModel
     */
    public void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {

        welcomePanel.removeAll();
        graphicPanel.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.mapRiskModel = gamePlayModel.getMapRiskModel();
        this.gamePlayModel = gamePlayModel;
        this.playerModel = playerModel;

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        if(this.gamePlayModel.getConsoleText()!=null) {
            this.consoleTextArea.setText(this.gamePlayModel.getConsoleText().toString());
        }

        this.noOfArmiesLabel = new JLabel("Number of Troops :");
        noOfArmiesLabel.setBounds(1300, 140, 150, 25);
        welcomePanel.add(noOfArmiesLabel);

        Integer[] troops = new Integer[playerModel.getRemainingNumberOfArmies()];
        for (int i = 0; i < playerModel.getRemainingNumberOfArmies(); i++) {
            troops[i] = i + 1;
        }

        numOfArmiesComboBox = new JComboBox(troops);
        numOfArmiesComboBox.setBounds(1300, 170, 150, 25);
        numOfArmiesComboBox.setEnabled(false);
        welcomePanel.add(numOfArmiesComboBox);

        welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getRemainingNumberOfArmies());
        welcomeLabel1.setBounds(1450, 170, 300, 25);
        welcomeLabel1.setFont(smallFont);
        welcomePanel.add(welcomeLabel1);

        this.countryListLabel = new JLabel("Select Country :");
        countryListLabel.setBounds(1300, 230, 150, 25);
        welcomePanel.add(this.countryListLabel);

        ArrayList<CountryModel> listOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName()
                    .equals(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner())) {
                listOfCountries.add(this.mapRiskModel.getCountryModelList().get(i));
            }
        }

        countriesViewRenderer = new CountryViewRenderer();
        countryListArray = listOfCountries.toArray();
        countryListComboBox = new JComboBox(countryListArray);
        welcomePanel.add(this.countryListComboBox);

        if (countryListArray.length > 0) {
            countryListComboBox.setRenderer(countriesViewRenderer);
        }
        countryListComboBox.setBounds(1300, 260, 150, 25);
        welcomePanel.add(countryListComboBox);

        this.addButton.setBounds(1300, 300, 150, 25);
        welcomePanel.add(this.addButton);

        this.nextButton.setBounds(1400, 600, 150, 25);
        welcomePanel.add(this.nextButton);

        int n = this.mapRiskModel.getCountryModelList().size();
        button = new JButton[n];
        for (int i = 0; i < n; i++) {
            CountryModel country = this.mapRiskModel.getCountryModelList().get(i);

            country.setBackground(this.mapRiskModel.getCountryModelList().get(i).getBackgroundColor());
            country.setText(this.mapRiskModel.getCountryModelList().get(i).getCountryName().substring(0, 3));
            country.setToolTipText("Troops: " + this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies());
            country.setFont(smallFont);
            PlayerModel pm = this.gamePlayModel.getPlayer(country);
            Border border = BorderFactory
                    .createLineBorder(pm.getPlayerColor(), 3);

            country.setBorder(border);

            country.setOpaque(true);
            country.setBounds(this.mapRiskModel.getCountryModelList().get(i).getXPosition() * 2,
                    this.mapRiskModel.getCountryModelList().get(i).getYPosition() * 2, 50, 50);

            country.setMargin(new Insets(0, 0, 0, 0));

            graphicPanel.add(country);
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

        Point[] connectorPoints = new Point[this.mapRiskModel.getCountryModelList().size()];

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

        if (obs instanceof MapRiskModel) {
            this.mapRiskModel = (MapRiskModel) obs;
        } else if (obs instanceof PlayerModel) {
            this.playerModel = (PlayerModel) obs;
        }
        this.updateWindow(this.gamePlayModel, this.playerModel);
        this.revalidate();
        this.repaint();

    }

    /**
     * This is actionListener method to listen the action events in the screen
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.nextButton.addActionListener(actionListener);
    }

    /**
     * This method convert string to color
     *
     * @param value
     * @return color
     */

    public static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            try {
                final Field f = Color.class.getField(value);

                return (Color) f.get(null);
            } catch (Exception ce) {
                return Color.black;
            }
        }
    }

}
