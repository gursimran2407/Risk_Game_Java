package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Namita Faujdar
 */

public class ReinforcementView extends JFrame implements ViewInterface {

    public MapRiskModel mapRiskModel;
    public PlayerModel playerModel;

    public JPanel panelWelcome;
    public JPanel panelGraphic;

    public JLabel labelWelcome;
    public JLabel labelNumberOfArmies;

    public JComboBox<Integer> noOfArmiesComboBox;
    public JButton buttonAdd;

    public JLabel countryListLabel;
    public JComboBox<Object> countryListComboBox;
    public Object[] countryList;
    private CountryViewRenderer countryView;

    public JButton[] button;

    public ReinforcementView(MapRiskModel new_mapRiskModel) {
        mapRiskModel = new_mapRiskModel;
        this.setTitle("Reinforcement Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 1000);
        this.setResizable(false);
        this.setVisible(false);
        buttonAdd = new JButton("Add");
        panelWelcome = new JPanel();
        panelGraphic = new JPanel();
        this.add(panelGraphic);
        panelGraphic.setSize(1200, 1000);
        panelGraphic.setBackground(Color.WHITE);

        buttonAdd = new JButton("Add");
        this.add(panelWelcome);

        playerModel = mapRiskModel.getPlayerTurn();
        labelWelcome = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");

        panelWelcome.setLayout(null);
        panelGraphic.setLayout(null);

        updateWindow(mapRiskModel, playerModel);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for Reinforcement Phase
     *
     * @param new_mapRiskModel object of MapRiskModel
     * @param playerModel object of PlayerModel
     */
    public void updateWindow(MapRiskModel new_mapRiskModel, PlayerModel playerModel) {

        Font largeFont = new Font("Serif", Font.BOLD, 18);
        /*Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);*/

        mapRiskModel = new_mapRiskModel;
        this.playerModel = mapRiskModel.getPlayerTurn();

        labelWelcome.setBounds(1300, 80, 300, 25);
        labelWelcome.setFont(largeFont);
        panelWelcome.add(labelWelcome);

        this.labelNumberOfArmies = new JLabel("Number of Troops :");
        labelNumberOfArmies.setBounds(1300, 140, 150, 25);
        panelWelcome.add(labelNumberOfArmies);

        Integer[] troops = new Integer[mapRiskModel.getPlayerTurn().getNumberofArmies()];
        for (int i = 0; i < mapRiskModel.getPlayerTurn().getNumberofArmies(); i++) {
            troops[i] = i + 1;
        }

        noOfArmiesComboBox = new JComboBox(troops);
        noOfArmiesComboBox.setBounds(1300, 170, 150, 25);
        panelWelcome.add(noOfArmiesComboBox);

        this.countryListLabel = new JLabel("Select Country :");
        countryListLabel.setBounds(1300, 230, 150, 25);
        panelWelcome.add(this.countryListLabel);

        ArrayList<CountryModel> listOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < mapRiskModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName()
                    .equals(mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerName())) {
                listOfCountries.add(mapRiskModel.getCountryModelList().get(i));
            }
        }

        countryView = new CountryViewRenderer();
        countryList = listOfCountries.toArray();
        countryListComboBox = new JComboBox(countryList);
        panelWelcome.add(this.countryListComboBox);

        if (countryList.length > 0) {
            countryListComboBox.setRenderer(countryView);
        }
        countryListComboBox.setBounds(1300, 260, 150, 25);

        this.buttonAdd.setBounds(1300, 300, 150, 25);
        panelWelcome.add(this.buttonAdd);

        int n = mapRiskModel.getCountryModelList().size();
        button = new JButton[n];

        for (int i = 0; i < mapRiskModel.getCountryModelList().size(); i++) {

            button[i] = new JButton();
            button[i].setText(mapRiskModel.getCountryModelList().get(i).getCountryName().substring(0, 3));
            button[i].setBackground(mapRiskModel.getCountryModelList().get(i).getBackgroundColor());
            button[i].setToolTipText("Troops: " + mapRiskModel.getCountryModelList().get(i).getNumberofArmies());
            button[i].setBorder(
                    new LineBorder(stringToColor(mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerColor()), 3));
            button[i].setOpaque(true);
            button[i].setBounds(mapRiskModel.getCountryModelList().get(i).getXPosition() * 2,
                    mapRiskModel.getCountryModelList().get(i).getYPosition() * 2, 50, 50);

            panelGraphic.add(button[i]);
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

        Point[] connectorPoints = new Point[mapRiskModel.getCountryModelList().size()];

        for (int i = 0; i < mapRiskModel.getCountryModelList().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(mapRiskModel.getCountryModelList().get(i), 0, 0, this);

        }

        for (int k = 0; k < mapRiskModel.getCountryModelList().size(); k++) {
            if (mapRiskModel.getCountryModelList().get(k).getConnectedCountryList() != null) {
                ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) mapRiskModel.getCountryModelList()
                        .get(k).getConnectedCountryList();

                for (int j = 0; j < neighbourCountries.size(); j++) {
                    for (int i = 0; i < mapRiskModel.getCountryModelList().size(); i++)
                        if (neighbourCountries.get(j).equals(mapRiskModel.getCountryModelList().get(i)))
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

        panelWelcome.removeAll();
        panelGraphic.removeAll();
        if (obs instanceof MapRiskModel) {
            mapRiskModel = (MapRiskModel) obs;
        } else if (obs instanceof PlayerModel) {
            this.playerModel = (PlayerModel) obs;
        }
        this.updateWindow(mapRiskModel, this.playerModel);
        this.revalidate();
        this.repaint();

    }

    /**
     * This is actionListener method to listen the action events in the screen
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.buttonAdd.addActionListener(actionListener);
    }

    /**
     * This method convert string to color
     *
     * @param value setting color
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
