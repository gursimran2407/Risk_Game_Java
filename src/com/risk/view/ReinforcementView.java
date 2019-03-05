package com.risk.view;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.awt.AWTAbstractView;
import com.risk.view.game.IReinforcementView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.function.BiConsumer;

/**
 * This View provides the reinforcementView of the Game Play. It also provides
 * the observer pattern when the data is modified
 * @author Namita Faujdar
 */

public class ReinforcementView extends AWTAbstractView implements IReinforcementView {

    private MapRiskModel mapRiskModel;
    private PlayerModel playerModel;

    private JPanel welcomePanel;
    private JPanel graphicPanel;

    private JLabel welcomeLabel;
    private JLabel noOfTroopsLabel;

    private JComboBox<Integer> numOfTroopsComboBox;
    private JButton addButton;
    private JLabel listOfCountriesLabel;

    private JLabel countryListLabel;
    private JComboBox<Object> countryListComboBox;
    private Object[] countryListArray;
    private CountryViewRenderer countriesViewRenderer;

    private JButton[] button;

    public ReinforcementView(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
        this.setTitle("Reinforcement Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 1000);
        this.setResizable(false);
        this.setVisible(false);
        this.addButton = new JButton("Add");
        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        this.add(graphicPanel);
        graphicPanel.setSize(1200, 1000);
        graphicPanel.setBackground(Color.WHITE);

        this.addButton = new JButton("Add");
        this.add(welcomePanel);

        this.playerModel = this.mapRiskModel.getPlayerTurn();
        this.welcomeLabel = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");

        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);

        updateWindow(mapRiskModel, playerModel);
    }

    @Override
    public void addAddListener(BiConsumer<Object, CountryModel> listener) {
        addButton.addActionListener(
                e -> listener.accept(
                        numOfTroopsComboBox.getSelectedItem(), (CountryModel) countryListComboBox.getSelectedItem()));
    }

    /**
     * This is actionListener method to listen the action events in the screen
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for Reinforcement Phase
     *
     * @param mapRiskModel
     * @param playerModel
     */
    private void updateWindow(MapRiskModel mapRiskModel, PlayerModel playerModel) {

        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.mapRiskModel = mapRiskModel;
        this.playerModel = this.mapRiskModel.getPlayerTurn();

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        this.noOfTroopsLabel = new JLabel("Number of Troops :");
        noOfTroopsLabel.setBounds(1300, 140, 150, 25);
        welcomePanel.add(noOfTroopsLabel);

        Integer[] troops = new Integer[this.mapRiskModel.getPlayerTurn().getNumberofArmies()];
        for (int i = 0; i < this.mapRiskModel.getPlayerTurn().getNumberofArmies(); i++) {
            troops[i] = i + 1;
        }

        numOfTroopsComboBox = new JComboBox(troops);
        numOfTroopsComboBox.setBounds(1300, 170, 150, 25);
        welcomePanel.add(numOfTroopsComboBox);

        this.countryListLabel = new JLabel("Select Country :");
        countryListLabel.setBounds(1300, 230, 150, 25);
        welcomePanel.add(this.countryListLabel);

        ArrayList<CountryModel> listOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName()
                    .equals(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerName())) {
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

        this.addButton.setBounds(1300, 300, 150, 25);
        welcomePanel.add(this.addButton);

        int n = this.mapRiskModel.getCountryModelList().size();
        button = new JButton[n];

        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {

            button[i] = new JButton();
            button[i].setText(this.mapRiskModel.getCountryModelList().get(i).getCountryName().substring(0, 3));
            button[i].setBackground(this.mapRiskModel.getCountryModelList().get(i).getBackgroundColor());
            button[i].setToolTipText("Troops: " + this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies());
            button[i].setBorder(
                    new LineBorder(stringToColor(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerColor()), 3));
            button[i].setOpaque(true);
            button[i].setBounds(this.mapRiskModel.getCountryModelList().get(i).getXPosition() * 2,
                    this.mapRiskModel.getCountryModelList().get(i).getYPosition() * 2, 50, 50);

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
        welcomePanel.removeAll();
        graphicPanel.removeAll();

        if (obs instanceof MapRiskModel) {
            this.mapRiskModel = (MapRiskModel) obs;
        } else if (obs instanceof PlayerModel) {
            this.playerModel = (PlayerModel) obs;
        }

        this.updateWindow(this.mapRiskModel, this.playerModel);
        this.revalidate();
        this.repaint();

    }

    /**
     * This method convert string to color
     *
     * @param value
     * @return
     */
    private static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            try {
                return (Color) Color.class.getField(value).get(null);
            } catch (Exception ce) {
                return Color.black;
            }
        }
    }

}
