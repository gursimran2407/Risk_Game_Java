package com.risk.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;

import com.risk.helperInterfaces.View;
import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;


/**
 * "ConnectCountryView" Class displays a view to enable the players to connect a
 * country to any other one during map creation. It contains buttons, labels,
 * and lists
 *
 * @author KaranPannu
 *
 */
public class ConnectCountryView extends JFrame implements View, Observer {

    /** The welcome panel. */
    private JPanel welcomePanel;

    /** The graphic panel. */
    private JPanel graphicPanel;

    /** The save button. */
    public JButton saveButton;

    /** The add button. */
    public JButton addButton;

    /** The remove button. */
    public JButton removeButton;

    /** The welcome label. */
    public JLabel welcomeLabel;

    /** The country parent list right. */
    public JList countryParentListRight;

    /** The country parent list left. */
    public JList countryParentListLeft;

    /** The list selection model left. */
    public ListSelectionModel listSelectionModelLeft;

    /** The list selection model right. */
    private ListSelectionModel listSelectionModelRight;

    /** The game map model. */
    public GameMapModel gameMapModel;


    /**
     * Constructor method of ConnectCountryView.
     *
     * @param gameMapModel the game map model
     */
    public ConnectCountryView(GameMapModel gameMapModel) {
        this.gameMapModel = gameMapModel;
        welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");

        saveButton = new JButton("Save");
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        getContentPane().add(graphicPanel);
        graphicPanel.setSize(1200, 800);
        graphicPanel.setBackground(Color.WHITE);
        graphicPanel.setLayout(null);

        this.setName("RISK GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1400, 800);
        this.setResizable(false);
        this.setVisible(false);
        welcomePanel.setLayout(null);
        this.add(welcomePanel);
        this.updateWindow(gameMapModel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * The method "updateWindow" updates the panel view after any change.
     *
     * @param gmm which is a GameMapModel object
     */
    private void updateWindow(GameMapModel gmm) {
        welcomePanel.removeAll();
        graphicPanel.removeAll();

        Font smallFont = new Font("Serif", Font.BOLD, 12);

        /* The country list label left. */
        JLabel countryListLabelLeft = new JLabel("Country 1");
        countryListLabelLeft.setBounds(1200, 80, 100, 25);
        welcomePanel.add(countryListLabelLeft);

        /* The country list label right. */
        JLabel countryListLabelRight = new JLabel("Country 2");
        countryListLabelRight.setBounds(1200, 280, 100, 25);
        welcomePanel.add(countryListLabelRight);

        // left panel
        /* The left country list. */
        List<CountryModel> leftCountryList = gmm.getCountries();
        CountryModel[] countryModelArrayLeft = new CountryModel[leftCountryList.size()];
        for (int i = 0; i < leftCountryList.size(); i++) {
            countryModelArrayLeft[i] = leftCountryList.get(i);
        }

        countryParentListLeft = new JList<CountryModel>();
        if (countryModelArrayLeft.length > 0) {
            countryParentListLeft.setListData(countryModelArrayLeft);
            countryParentListLeft.setCellRenderer(new CountryModelRenderer());

        }

        countryParentListLeft.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane countryParentListPaneLeft = new JScrollPane(countryParentListLeft);

        this.listSelectionModelLeft = countryParentListLeft.getSelectionModel();
        countryParentListLeft.setSelectedIndex(this.gameMapModel.getLeftModelIndex());
        countryParentListPaneLeft.setBounds(1200, 100, 150, 150);

        welcomePanel.add(countryParentListPaneLeft);

        // Right panel
        /* The right country list. */
        List<CountryModel> rightCountryList = gmm.getCountries();

        CountryModel[] countryModelArrayRight = new CountryModel[rightCountryList.size()];
        for (int i = 0; i < rightCountryList.size(); i++) {
            countryModelArrayRight[i] = rightCountryList.get(i);
        }

        countryParentListRight = new JList<CountryModel>();
        if (countryModelArrayRight.length > 0) {
            countryParentListRight.setListData(countryModelArrayRight);
            countryParentListRight.setCellRenderer(new CountryModelRenderer());

        }
        countryParentListRight.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.listSelectionModelRight = countryParentListRight.getSelectionModel();
        JScrollPane countryParentListPaneRight = new JScrollPane(countryParentListRight);
        countryParentListRight.setSelectedIndex(this.gameMapModel.getRightModelIndex());
        countryParentListPaneRight.setBounds(1200, 300, 150, 150);
        welcomePanel.add(countryParentListPaneRight);

        addButton.setFont(smallFont);
        welcomePanel.add(addButton);
        addButton.setBounds(1200, 250, 100, 20);

        saveButton.setFont(smallFont);
        welcomePanel.add(saveButton);
        saveButton.setBounds(1200, 460, 100, 20);

        removeButton.setFont(smallFont);
        welcomePanel.add(removeButton);
        removeButton.setBounds(1300, 250, 100, 20);

        for (CountryModel country : leftCountryList) {
            country.setBackground(country.getBackgroundColor());
            country.setText(country.getCountryName());
            country.setBorderColor(country.getBorderColor());
            country.setOpaque(true);
            country.setBounds(country.getXPosition() * 2,
                    country.getYPosition() * 2, 50, 50);

            graphicPanel.add(country);
        }

        for (CountryModel country : rightCountryList) {
            country.setBackground(country.getBackgroundColor());
            country.setText(country.getCountryName());
            country.setBorderColor(country.getBorderColor());
            country.setOpaque(true);
            country.setBounds(country.getXPosition() * 2,
                    country.getYPosition() * 2, 50, 50);

            graphicPanel.add(country);
        }

        graphicPanel.setLayout(null);

    }

    /* (non-Javadoc)
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

                for (CountryModel countryModel : neighbourCountries) {
                    for (int i = 0; i < this.gameMapModel.getCountries().size(); i++)
                        if (countryModel.equals(this.gameMapModel.getCountries().get(i)))
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
     * Method "update" updates the gameMapModel associated with.
     *
     * @param gameMapModel the game map model
     * @param arg1 the arg 1
     */
    @Override
    public void update(Observable gameMapModel, Object arg1) {

        this.updateWindow(((GameMapModel) gameMapModel));
        this.repaint();
        this.revalidate();

    }

    /**
     * Does the actions regarding each button pushed saveButton, addButton, and
     * removeButton.
     *
     * @param actionListener the new action listener
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.saveButton.addActionListener(actionListener);
        this.addButton.addActionListener(actionListener);
        this.removeButton.addActionListener(actionListener);
    }

    /**
     * Sets lists of selections.
     *
     * @param listSelectionListener the new list selection listener
     */
    public void setListSelectionListener(ListSelectionListener listSelectionListener) {
        this.listSelectionModelLeft.addListSelectionListener(listSelectionListener);
        this.listSelectionModelRight.addListSelectionListener(listSelectionListener);
    }

    /**
     * "CountryModelRenderer" changes ....
     */
    class CountryModelRenderer extends JLabel implements ListCellRenderer<CountryModel> {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 1L;

        /**
         * Instantiates a new country model renderer.
         */
        CountryModelRenderer() {
            setOpaque(true);
        }

        /**
         * Get List of Cell Renderer Component.
         *
         * @param arg0 the arg 0
         * @param arg1 the arg 1
         * @param arg2 the arg 2
         * @param arg3 the arg 3
         * @param arg4 the arg 4
         * @return the list cell renderer component
         * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList,
         *      java.lang.Object, int, boolean, boolean)
         */
        @Override
        public Component getListCellRendererComponent(JList<? extends CountryModel> arg0, CountryModel arg1, int arg2,
                                                      boolean arg3, boolean arg4) {

            if (arg1 != null) {
                setText(arg1.getCountryName());
            }

            if (arg3) {
                setBackground(new Color(0, 0, 128));
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(Color.black);
            }

            return this;
        }
    }

}