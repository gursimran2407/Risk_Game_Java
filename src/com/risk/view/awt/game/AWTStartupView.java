package com.risk.view.awt.game;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.awt.AWTAbstractView;
import com.risk.view.game.IStartupView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.function.BiConsumer;

/**
 * The startup View Class is responsible for the the startup view
 * @author gursimransingh
 */

public class AWTStartupView extends AWTAbstractView implements IStartupView {

    private static final long serialVersionUID = 1L;
    private MapRiskModel mapRiskModel;
    private PlayerModel playerModel;

    private JPanel pnlWelcome;
    private JPanel pnlGraphic;

    private JLabel lblWelcome;
    private JLabel lblWelcome1;
    private JLabel lblNoOfTroops;

    private JComboBox<Integer> cbxNumOfTroops;
    private JButton btnAdd;

    private JLabel lblCountryList;
    private JComboBox<Object> cbxCountryList;
    private Object[] countryListArray;
    private CountryViewRenderer countriesViewRenderer;

    private JButton[] button;
    private JButton btnNext;

    /**
     * constructor for Start Up Phase View where the variables are initialized
     *
     * @param mapRiskModel
     * @param playerModel
     */
    public AWTStartupView(MapRiskModel mapRiskModel, PlayerModel playerModel) {

        this.setTitle("Startup Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 1000);
        this.setResizable(false);
        this.setVisible(false);
        this.btnAdd = new JButton("Add");
        this.btnNext = new JButton("Next");
        pnlWelcome = new JPanel();
        pnlGraphic = new JPanel();
        this.add(pnlGraphic);
        pnlGraphic.setSize(1200, 1000);
        pnlGraphic.setBackground(Color.WHITE);
        pnlGraphic.setLayout(null);
        this.btnAdd = new JButton("Add");
        this.add(pnlWelcome);
        this.playerModel = playerModel;
        this.lblWelcome = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");
        this.lblWelcome1 = new JLabel("Remaining Armies: " + playerModel.getRemainingNumberOfArmies());
        updateWindow(mapRiskModel, playerModel);
        pnlWelcome.setLayout(null);
        pnlGraphic.setLayout(null);
    }

    @Override
    public void setWelcomeMessage(String message) {
        lblWelcome.setText(message);
    }

    @Override
    public void addTroopsListener(BiConsumer<Object, CountryModel> listener) {
        this.btnAdd.addActionListener(
                e -> listener.accept(
                        cbxNumOfTroops.getSelectedItem(), (CountryModel) cbxCountryList.getSelectedItem()));
    }

    @Override
    public void addNextListener(ActionListener listener) {
        this.btnNext.addActionListener(listener);
    }

    /**
     * This updateWindow method is called whenever the model is updated. It updates
     * the Screen for Start Up Phase
     *
     * @param mapRiskModel
     * @param playerModel
     */
    private void updateWindow(MapRiskModel mapRiskModel, PlayerModel playerModel) {

        pnlWelcome.removeAll();
        pnlGraphic.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.mapRiskModel = mapRiskModel;
        this.playerModel = playerModel;

        lblWelcome.setBounds(1300, 80, 300, 25);
        lblWelcome.setFont(largeFont);
        pnlWelcome.add(lblWelcome);

        this.lblNoOfTroops = new JLabel("Number of Troops :");
        lblNoOfTroops.setBounds(1300, 140, 150, 25);
        pnlWelcome.add(lblNoOfTroops);

        Integer[] troops = new Integer[playerModel.getRemainingNumberOfArmies()];
        for (int i = 0; i < playerModel.getRemainingNumberOfArmies(); i++) {
            troops[i] = i + 1;
        }

        cbxNumOfTroops = new JComboBox(troops);
        cbxNumOfTroops.setBounds(1300, 170, 150, 25);
        cbxNumOfTroops.setEnabled(false);
        pnlWelcome.add(cbxNumOfTroops);

        lblWelcome1 = new JLabel("Remaining Armies: " + playerModel.getRemainingNumberOfArmies());
        lblWelcome1.setBounds(1450, 170, 300, 25);
        lblWelcome1.setFont(smallFont);
        pnlWelcome.add(lblWelcome1);

        this.lblCountryList = new JLabel("Select Country :");
        lblCountryList.setBounds(1300, 230, 150, 25);
        pnlWelcome.add(this.lblCountryList);

        ArrayList<CountryModel> listOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName()
                    .equals(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerName())) {
                listOfCountries.add(this.mapRiskModel.getCountryModelList().get(i));
            }
        }

        countriesViewRenderer = new CountryViewRenderer();
        countryListArray = listOfCountries.toArray();
        cbxCountryList = new JComboBox(countryListArray);
        pnlWelcome.add(this.cbxCountryList);

        if (countryListArray.length > 0) {
            cbxCountryList.setRenderer(countriesViewRenderer);
        }
        cbxCountryList.setBounds(1300, 260, 150, 25);
        pnlWelcome.add(cbxCountryList);

        this.btnAdd.setBounds(1300, 300, 150, 25);
        pnlWelcome.add(this.btnAdd);

        this.btnNext.setBounds(1400, 600, 150, 25);
        pnlWelcome.add(this.btnNext);

        int n = this.mapRiskModel.getCountryModelList().size();
        button = new JButton[n];
        for (int i = 0; i < n; i++) {
            CountryModel country = this.mapRiskModel.getCountryModelList().get(i);

            country.setBackground(this.mapRiskModel.getCountryModelList().get(i).getBackgroundColor());
            country.setText(this.mapRiskModel.getCountryModelList().get(i).getCountryName().substring(0, 3));
            country.setToolTipText("Troops: " + this.mapRiskModel.getCountryModelList().get(i).getNumberofArmies());
            country.setFont(smallFont);

            Border border = BorderFactory
                    .createLineBorder(stringToColor(this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().getPlayerColor()), 3);

            country.setBorder(border);

            country.setOpaque(true);
            country.setBounds(this.mapRiskModel.getCountryModelList().get(i).getXPosition() * 2,
                    this.mapRiskModel.getCountryModelList().get(i).getYPosition() * 2, 50, 50);

            country.setMargin(new Insets(0, 0, 0, 0));

            pnlGraphic.add(country);
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

        this.updateWindow(this.mapRiskModel, this.playerModel);
        this.revalidate();
        this.repaint();
    }

    /**
     * This method convert string to color
     *
     * @param value
     * @return color
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