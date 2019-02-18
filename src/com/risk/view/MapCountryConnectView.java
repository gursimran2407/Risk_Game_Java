package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Shriyans
 */
public class MapCountryConnectView extends JFrame implements ViewInterface, Observer  {

    public JPanel welcomePanel;
    public JPanel graphicPanel;
    public JButton saveButton;
    public JButton addButton;
    public JButton removeButton;
    public JLabel welcomeLabel;
    public JLabel countryListLabelLeft;
    public JLabel countryListLabelRight;
    public JList countryParentListRight;
    public JList countryParentListLeft;
    public ListSelectionModel listSelectionModelLeft;
    public ListSelectionModel listSelectionModelRight;
    public List<CountryModel> leftCountryList;
    public List<CountryModel> rightCountryList;
    public MapRiskModel d_mapRiskModel;

    /**
     * Constructor method of ConnectCountryView
     *
     * @param new_mapRiskModel
     */
    public MapCountryConnectView(MapRiskModel new_mapRiskModel) {
        d_mapRiskModel = new_mapRiskModel;
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
        this.updateWindow(new_mapRiskModel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * The method "updateWindow" updates the panel view after any change
     *
     * @param mrr which is a GameMapModel object
     */
    private void updateWindow(MapRiskModel mrr) {
        welcomePanel.removeAll();
        graphicPanel.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        countryListLabelLeft = new JLabel("Country 1");
        countryListLabelLeft.setBounds(1200, 80, 100, 25);
        welcomePanel.add(countryListLabelLeft);

        countryListLabelRight = new JLabel("Country 2");
        countryListLabelRight.setBounds(1200, 280, 100, 25);
        welcomePanel.add(countryListLabelRight);

        // left panel
        this.leftCountryList = mrr.getCountryModelList();
        CountryModel[] countryModelArrayLeft = new CountryModel[this.leftCountryList.size()];
        for (int i = 0; i < this.leftCountryList.size(); i++) {
            countryModelArrayLeft[i] = this.leftCountryList.get(i);
        }

        countryParentListLeft = new JList<CountryModel>();
        if (countryModelArrayLeft.length > 0) {
            countryParentListLeft.setListData(countryModelArrayLeft);
            countryParentListLeft.setCellRenderer(new CountryModelRenderer());

        }

        countryParentListLeft.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane countryParentListPaneLeft = new JScrollPane(countryParentListLeft);

        this.listSelectionModelLeft = countryParentListLeft.getSelectionModel();
        countryParentListLeft.setSelectedIndex(d_mapRiskModel.getLeftModelIndex());
        countryParentListPaneLeft.setBounds(1200, 100, 150, 150);

        welcomePanel.add(countryParentListPaneLeft);

        // Right panel
        this.rightCountryList = mrr.getCountryModelList();

        CountryModel[] countryModelArrayRight = new CountryModel[this.rightCountryList.size()];
        for (int i = 0; i < this.rightCountryList.size(); i++) {
            countryModelArrayRight[i] = this.rightCountryList.get(i);
        }

        countryParentListRight = new JList<CountryModel>();
        if (countryModelArrayRight.length > 0) {
            countryParentListRight.setListData(countryModelArrayRight);
            countryParentListRight.setCellRenderer(new CountryModelRenderer());

        }
        countryParentListRight.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.listSelectionModelRight = countryParentListRight.getSelectionModel();
        JScrollPane countryParentListPaneRight = new JScrollPane(countryParentListRight);
        countryParentListRight.setSelectedIndex(d_mapRiskModel.getRightModelIndex());
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

        for (int i = 0; i < this.leftCountryList.size(); i++) {
            CountryModel country = this.leftCountryList.get(i);

            country.setBackground(this.leftCountryList.get(i).getBackgroundColor());
            country.setText(this.leftCountryList.get(i).getCountryName());
            country.setBorderColor(this.leftCountryList.get(i).getBorderColor());
            country.setOpaque(true);
            country.setBounds(this.leftCountryList.get(i).getXPosition() * 2,
                    this.leftCountryList.get(i).getYPosition() * 2, 50, 50);

            graphicPanel.add(country);
        }
        for (int i = 0; i < this.rightCountryList.size(); i++) {
            CountryModel country = this.rightCountryList.get(i);

            country.setBackground(this.rightCountryList.get(i).getBackgroundColor());
            country.setText(this.rightCountryList.get(i).getCountryName());
            country.setBorderColor(this.rightCountryList.get(i).getBorderColor());
            country.setOpaque(true);
            country.setBounds(this.rightCountryList.get(i).getXPosition() * 2,
                    this.rightCountryList.get(i).getYPosition() * 2, 50, 50);

            graphicPanel.add(country);
        }
        graphicPanel.setLayout(null);

    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
        addButton.addActionListener(actionListener);
        removeButton.addActionListener(actionListener);
    }

    public void setListSelectionListener(ListSelectionListener actionListener) {
        listSelectionModelLeft.addListSelectionListener(actionListener);
        listSelectionModelRight.addListSelectionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.updateWindow(d_mapRiskModel);
        this.repaint();
        this.revalidate();
    }

    /**
     * "CountryModelRenderer" changes ....
     */
    class CountryModelRenderer extends JLabel implements ListCellRenderer<CountryModel> {

        private static final long serialVersionUID = 1L;

        public CountryModelRenderer() {
            setOpaque(true);
        }

        /**
         * Get List of Cell Renderer Component
         *
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