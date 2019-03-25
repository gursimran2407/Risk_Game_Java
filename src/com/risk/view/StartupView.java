package com.risk.view;

import java.awt.*;
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
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;

import com.risk.helperInterfaces.View;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;

/**
 * This class file is the view (observer) for Start Up Phase of Game Play
 *
 * @author Shriyans
 * @version 1.0.0
 */

public class StartUpView extends JFrame implements View {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;


    public MapRiskModel mapRiskModel;
    private PlayerModel playerModel;
    public GamePlayModel gamePlayModel;

    private JPanel welcomePanel;
    private JPanel graphicPanel;

    private JTextArea consoleTextArea;

    public JLabel welcomeLabel;
    private JLabel welcomeLabel1;

    public JComboBox<Integer> numOfTroopsComboBox;
    public JButton addButton;

    public JComboBox<Object> countryListComboBox;

    public JButton[] button;
    public JButton nextButton;

    /**
     * constructor for Start Up Phase View where the variables are initialized
     *
     * @param gamePlayModel Game play model
     * @param playerModel Player model
     */
    public StartUpView(GamePlayModel gamePlayModel, PlayerModel playerModel) {

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

        JPanel consoleMainPanel = new JPanel();
        consoleMainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.consoleTextArea = new JTextArea("Start up Phase !!!\n", 10, 500);
        this.consoleTextArea.setEditable(false);
        this.consoleTextArea.setFocusable(false);
        this.consoleTextArea.setVisible(true);
        this.consoleTextArea.setForeground(Color.WHITE);
        this.consoleTextArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        JScrollPane consolePanel = new JScrollPane(this.consoleTextArea);
        consolePanel.setPreferredSize(new Dimension(1580, 130));
        consoleMainPanel.add(consolePanel,BorderLayout.WEST);
        this.getContentPane().add(consoleMainPanel,BorderLayout.SOUTH);
        this.addButton = new JButton("Add");
        this.add(welcomePanel);
        this.playerModel = playerModel;
        this.gamePlayModel = gamePlayModel;
        this.welcomeLabel = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");
        this.welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getNumberofArmies());
        updateWindow(gamePlayModel, playerModel);
        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for Start Up Phase
     *
     * @param gamePlayModel Game play model
     * @param playerModel Player model
     */
    private void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {

        welcomePanel.removeAll();
        graphicPanel.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.mapRiskModel = gamePlayModel.getGameMap();
        this.gamePlayModel = gamePlayModel;
        this.playerModel = playerModel;

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        if(this.gamePlayModel.getConsoleText()!=null) {
            this.consoleTextArea.setText(this.gamePlayModel.getConsoleText().toString());
        }

        JLabel noOfTroopsLabel = new JLabel("Number of Troops :");
        noOfTroopsLabel.setBounds(1300, 140, 150, 25);
        welcomePanel.add(noOfTroopsLabel);

        Integer[] troops = new Integer[playerModel.getNumberofArmies()];
        for (int i = 0; i < playerModel.getNumberofArmies(); i++) {
            troops[i] = i + 1;
        }

        numOfTroopsComboBox = new JComboBox<>(troops);
        numOfTroopsComboBox.setBounds(1300, 170, 150, 25);
        numOfTroopsComboBox.setEnabled(false);
        welcomePanel.add(numOfTroopsComboBox);

        welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getNumberofArmies());
        welcomeLabel1.setBounds(1450, 170, 300, 25);
        welcomeLabel1.setFont(smallFont);
        welcomePanel.add(welcomeLabel1);

        JLabel countryListLabel = new JLabel("Select Country :");
        countryListLabel.setBounds(1300, 230, 150, 25);
        welcomePanel.add(countryListLabel);

        ArrayList<CountryModel> listOfCountries = new ArrayList<>();
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName()
                    .equals(this.mapRiskModel.getCountryModelList().get(i).getRulerName())) {
                listOfCountries.add(this.mapRiskModel.getCountryModelList().get(i));
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
            country.setText(this.mapRiskModel.getCountryModelList().get(i).getCountryCode());
            country.setToolTipText("Troops: " + this.mapRiskModel.getCountryModelList().get(i).getArmies());
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
     * @see Window#paint(Graphics)
     */
    public void paint(final Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Point[] connectorPoints = new Point[this.mapRiskModel.getCountryModelList().size()];

        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(this.mapRiskModel.getCountryModelList().get(i), 0, 0, this);

        }

        for (int k = 0; k < this.mapRiskModel.getCountryModelList().size(); k++) {
            if (this.mapRiskModel.getCountryModelList().get(k).getLinkedCountries() != null) {
                ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.mapRiskModel.getCountryModelList()
                        .get(k).getLinkedCountries();

                for (CountryModel neighbourCountry : neighbourCountries) {
                    for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++)
                        if (neighbourCountry.equals(this.mapRiskModel.getCountryModelList().get(i)))
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
     * @see Observer#update(Observable, Object)
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
     *
     *
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.nextButton.addActionListener(actionListener);
    }
}