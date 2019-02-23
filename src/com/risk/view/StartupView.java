package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

/**
 * @author gursimransingh
 */
public class StartupView extends JFrame implements ViewInterface {

    private static final long serialVersionUID = 1L;
    public MapRiskModel mapRiskModel;
    public PlayerModel playerModel;

    public JPanel welcomePanel;
    public JPanel graphicPanel;

    public JLabel welcomeLabel;
    public JLabel welcomeLabel1;
    public JLabel noOfTroopsLabel;

    public JComboBox<Integer> numOfTroopsComboBox;
    public JButton addButton;
    public JLabel listOfCountriesLabel;

    public JLabel countryListLabel;
    public JComboBox<Object> countryListComboBox;
    public Object[] countryListArray;
    public JButton[] button;
    public JButton nextButton;
    public JButton button2;
    public JButton button3;
    private CountryViewRenderer countriesViewRenderer;

    public StartupView(MapRiskModel mapRiskModel, PlayerModel playerModel) {

        this.setTitle("Startup Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 1000);
        this.setResizable(false);
        this.setVisible(false);
        this.addButton = new JButton("Add");
        this.nextButton = new JButton("Next");
        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        this.add(graphicPanel);
        graphicPanel.setSize(1200, 1000);
        graphicPanel.setBackground(Color.WHITE);
        graphicPanel.setLayout(null);
        this.addButton = new JButton("Add");
        this.add(welcomePanel);
        this.playerModel = playerModel;
        this.welcomeLabel = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");
        this.welcomeLabel1 = new JLabel("Remaining Armies: " + playerModel.getRemainingNumberOfArmies());
        updateWindow(mapRiskModel, playerModel);
        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);
    }

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

    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.nextButton.addActionListener(actionListener);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MapRiskModel) {
            this.mapRiskModel = (MapRiskModel) o;
        } else if (o instanceof PlayerModel) {
            this.playerModel = (PlayerModel) o;
        }
        this.updateWindow(this.mapRiskModel, this.playerModel);
        this.revalidate();
        this.repaint();
    }

    public void updateWindow(MapRiskModel mapRiskModel, PlayerModel playerModel) {

        welcomePanel.removeAll();
        graphicPanel.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.mapRiskModel = mapRiskModel;
        this.playerModel = playerModel;

        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        this.noOfTroopsLabel = new JLabel("Number of Troops :");
        noOfTroopsLabel.setBounds(1300, 140, 150, 25);
        welcomePanel.add(noOfTroopsLabel);

        Integer[] troops = new Integer[playerModel.getRemainingNumberOfArmies()];
        for (int i = 0; i < playerModel.getRemainingNumberOfArmies(); i++) {
            troops[i] = i + 1;
        }

        numOfTroopsComboBox = new JComboBox(troops);
        numOfTroopsComboBox.setBounds(1300, 170, 150, 25);
        numOfTroopsComboBox.setEnabled(false);
        welcomePanel.add(numOfTroopsComboBox);

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

            Border border = BorderFactory.createLineBorder(stringToColor(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerColor()), 3);

            country.setBorder(border);

            country.setOpaque(true);
            country.setBounds(this.mapRiskModel.getCountryModelList().get(i).getXPosition() * 2,
                    this.mapRiskModel.getCountryModelList().get(i).getYPosition() * 2, 50, 50);

            country.setMargin(new Insets(0, 0, 0, 0));

            graphicPanel.add(country);
        }
    }

    public void paint(final Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Point[] connectorPoints = new Point[this.mapRiskModel.getCountryModelList().size()];

        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(this.mapRiskModel.getCountryModelList().get(i), 0, 0, this);

        }

        for (int k = 0; k < this.mapRiskModel.getCountryModelList().size(); k++) {
            if (this.mapRiskModel.getCountryModelList().get(k).getConnectedCountryList() != null) {
                ArrayList<CountryModel> neighbourCountries = this.mapRiskModel.getCountryModelList().get(k).getConnectedCountryList();

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
