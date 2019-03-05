package com.risk.view.awt.map.countryconnect;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.awt.AWTAbstractView;
import com.risk.view.map.countryconnect.IMapCountryConnectView;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.function.BiConsumer;

/**
 * AWTMapCountryConnectView Class represents a view for players to connect a
 * country to any other one during map creation. It contains buttons, labels,
 * and lists
 * @author Shriyans
 */
public class AWTMapCountryConnectView extends AWTAbstractView implements IMapCountryConnectView {

    private JPanel panelWelcome;
    private JPanel panelGraphic;
    private JButton buttonSave;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JLabel welcomeLabel;
    private JLabel countryListLeft;
    private JLabel countryListRight;
    private JList rightCountryParentList;
    private JList leftCountryParentList;
    private ListSelectionModel leftListSelectionModel;
    private ListSelectionModel rightListSelectionModel;
    private List<CountryModel> leftCountryList;
    private List<CountryModel> rightCountryList;
    private MapRiskModel d_mapRiskModel;

    /**
     * Constructor for AWTMapCountryConnectView
     *
     * @param new_mapRiskModel
     */
    public AWTMapCountryConnectView(MapRiskModel new_mapRiskModel) {
        d_mapRiskModel = new_mapRiskModel;
        welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");

        buttonSave = new JButton("Save");
        buttonAdd = new JButton("Add");
        buttonRemove = new JButton("Remove");

        panelWelcome = new JPanel();
        panelGraphic = new JPanel();
        getContentPane().add(panelGraphic);
        panelGraphic.setSize(1200, 800);
        panelGraphic.setBackground(Color.WHITE);
        panelGraphic.setLayout(null);

        this.setName("RISK GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1400, 800);
        this.setResizable(false);
        this.setVisible(false);
        panelWelcome.setLayout(null);
        this.add(panelWelcome);
        this.updateWindow(new_mapRiskModel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * The method "updateWindow" updates the panel view after any change
     *
     * @param mapRiskModel which is a MapRiskModel object
     */
    private void updateWindow(MapRiskModel mapRiskModel) {
        panelWelcome.removeAll();
        panelGraphic.removeAll();
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        countryListLeft = new JLabel("Country 1");
        countryListLeft.setBounds(1200, 80, 100, 25);
        panelWelcome.add(countryListLeft);

        countryListRight = new JLabel("Country 2");
        countryListRight.setBounds(1200, 280, 100, 25);
        panelWelcome.add(countryListRight);

        // left panel
        leftCountryList = mapRiskModel.getCountryModelList();
        CountryModel[] countryModelArrayLeft = new CountryModel[leftCountryList.size()];
        for (int i = 0; i < leftCountryList.size(); i++) {
            countryModelArrayLeft[i] = leftCountryList.get(i);
        }

        leftCountryParentList = new JList<CountryModel>();
        if (countryModelArrayLeft.length > 0) {
            leftCountryParentList.setListData(countryModelArrayLeft);
            leftCountryParentList.setCellRenderer(new CountryModelRenderer());

        }

        leftCountryParentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane countryParentListPaneLeft = new JScrollPane(leftCountryParentList);

        leftListSelectionModel = leftCountryParentList.getSelectionModel();
        leftCountryParentList.setSelectedIndex(d_mapRiskModel.getLeftModelIndex());
        countryParentListPaneLeft.setBounds(1200, 100, 150, 150);

        panelWelcome.add(countryParentListPaneLeft);

        // Right panel
        rightCountryList = mapRiskModel.getCountryModelList();

        CountryModel[] countryModelArrayRight = new CountryModel[rightCountryList.size()];
        for (int i = 0; i < rightCountryList.size(); i++) {
            countryModelArrayRight[i] = rightCountryList.get(i);
        }

        rightCountryParentList = new JList<CountryModel>();
        if (countryModelArrayRight.length > 0) {
            rightCountryParentList.setListData(countryModelArrayRight);
            rightCountryParentList.setCellRenderer(new CountryModelRenderer());

        }
        rightCountryParentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.rightListSelectionModel = rightCountryParentList.getSelectionModel();
        JScrollPane countryParentListPaneRight = new JScrollPane(rightCountryParentList);
        rightCountryParentList.setSelectedIndex(d_mapRiskModel.getRightModelIndex());
        countryParentListPaneRight.setBounds(1200, 300, 150, 150);
        panelWelcome.add(countryParentListPaneRight);

        buttonAdd.setFont(smallFont);
        panelWelcome.add(buttonAdd);
        buttonAdd.setBounds(1200, 250, 100, 20);

        buttonSave.setFont(smallFont);
        panelWelcome.add(buttonSave);
        buttonSave.setBounds(1200, 460, 100, 20);

        buttonRemove.setFont(smallFont);
        panelWelcome.add(buttonRemove);
        buttonRemove.setBounds(1300, 250, 100, 20);

        for (int i = 0; i < this.leftCountryList.size(); i++) {
            CountryModel country = this.leftCountryList.get(i);

            country.setBackground(this.leftCountryList.get(i).getBackgroundColor());
            country.setText(this.leftCountryList.get(i).getCountryName());
            country.setBorderColor(this.leftCountryList.get(i).getBorderColor());
            country.setOpaque(true);
            country.setBounds(this.leftCountryList.get(i).getXPosition() * 2,
                    this.leftCountryList.get(i).getYPosition() * 2, 50, 50);

            panelGraphic.add(country);
        }
        for (int i = 0; i < this.rightCountryList.size(); i++) {
            CountryModel country = this.rightCountryList.get(i);

            country.setBackground(this.rightCountryList.get(i).getBackgroundColor());
            country.setText(this.rightCountryList.get(i).getCountryName());
            country.setBorderColor(this.rightCountryList.get(i).getBorderColor());
            country.setOpaque(true);
            country.setBounds(this.rightCountryList.get(i).getXPosition() * 2,
                    this.rightCountryList.get(i).getYPosition() * 2, 50, 50);

            panelGraphic.add(country);
        }
        panelGraphic.setLayout(null);

    }

    @Override
    public void addAddNeighbouringListener(BiConsumer<CountryModel, CountryModel> listener) {
        buttonAdd.addActionListener(e -> listener.accept(
                (CountryModel) leftCountryParentList.getSelectedValue(),
                (CountryModel) rightCountryParentList.getSelectedValue()));
    }

    @Override
    public void addRemoveNeighbouringListener(BiConsumer<CountryModel, CountryModel> listener) {
        buttonRemove.addActionListener(e -> listener.accept(
                (CountryModel) leftCountryParentList.getSelectedValue(),
                (CountryModel) rightCountryParentList.getSelectedValue()));
    }

    @Override
    public void addSaveListener(ActionListener listener) {
        buttonSave.addActionListener(listener);
    }

    @Override
    public void setListSelectionListener(ListSelectionListener actionListener) {
        leftListSelectionModel.addListSelectionListener(actionListener);
        rightListSelectionModel.addListSelectionListener(actionListener);
    }

    @Override
    public ListSelectionModel getLeftListSelectionModel() {
        return leftListSelectionModel;
    }

    @Override
    public JList getLeftCountryParentList() {
        return leftCountryParentList;
    }

    @Override
    public JList getRightCountryParentList() {
        return rightCountryParentList;
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

    public void paint(final Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Point[] connectorPoints = new Point[d_mapRiskModel.getCountryModelList().size()];

        for (int i = 0; i < d_mapRiskModel.getCountryModelList().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(d_mapRiskModel.getCountryModelList().get(i), 0, 0, this);

        }

        for (int k = 0; k < d_mapRiskModel.getCountryModelList().size(); k++) {
            if (d_mapRiskModel.getCountryModelList().get(k).getConnectedCountryList() != null) {
                ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) d_mapRiskModel.getCountryModelList().get(k).getConnectedCountryList();

                for (int j = 0; j < neighbourCountries.size(); j++) {
                    for (int i = 0; i < d_mapRiskModel.getCountryModelList().size(); i++)
                        if (neighbourCountries.get(j).equals(d_mapRiskModel.getCountryModelList().get(i)))
                            g2.drawLine(connectorPoints[i].x + 25, connectorPoints[i].y + 25, connectorPoints[k].x + 25,
                                    connectorPoints[k].y + 25);

                }
            }
        }

    }
}
