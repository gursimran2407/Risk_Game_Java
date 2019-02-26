package com.risk.controller;

import com.risk.gameplayrequirements.MapValidation;
import com.risk.gameplayrequirements.MapWrite;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCountryConnectView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * In MapCountryConnectController, the data flow into model object and updates the
 * view whenever data changes.
 * @author gursimransingh
 */
public class MapCountryConnectController implements ListSelectionListener, ActionListener  {

    private MapRiskModel mapRiskModel;
    private MapCountryConnectView mapCountryConnectView;
    private CountryModel countryModel;
    private String filename = null;
    private MapWrite mapWrite;

    private List<CountryModel> countryModelList;
    private List<CountryModel> countryLinks;

    /**Constructor initializes values and sets the screen to visible
     * @param d_mapRiskModel
     */
    public MapCountryConnectController(MapRiskModel d_mapRiskModel) {
        mapRiskModel = d_mapRiskModel;
        this.countryModelList = d_mapRiskModel.getCountryModelList();
        this.countryLinks = new ArrayList<>();

        this.mapCountryConnectView = new MapCountryConnectView(d_mapRiskModel);
        this.mapCountryConnectView.setActionListener(this);
        this.mapCountryConnectView.setListSelectionListener(this);
        this.mapCountryConnectView.setVisible(true);
        mapRiskModel.addObserver(this.mapCountryConnectView);
    }

    /**
     * Listener for action event set in view.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mapCountryConnectView.buttonAdd)) {
            if (mapCountryConnectView.leftCountryParentList.getSelectedValue().equals(mapCountryConnectView.rightCountryParentList.getSelectedValue())) {
                JOptionPane.showOptionDialog(null, "Cannot create a self link", "Invalid", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                return;
            } else {
                //If adding connection between two countries then setneighbouring countries to all the countries
                mapRiskModel.setNeighbouringCountry((CountryModel) mapCountryConnectView.leftCountryParentList.getSelectedValue(), (CountryModel) this.mapCountryConnectView.rightCountryParentList.getSelectedValue());

            }
        } else if (e.getSource().equals(this.mapCountryConnectView.buttonSave)) {
            MapValidation mapValidation = new MapValidation();
            boolean flag1 = mapValidation.emptyLinkCountryValidation(mapRiskModel);

            boolean flag3 = mapValidation.emptyContinentValidation(mapRiskModel);
            boolean flag2 = mapValidation.checkInterlinkedContinent(mapRiskModel);
            System.out.println(flag1 + " " + flag2 + " " + flag3);
            if (!(mapValidation.emptyLinkCountryValidation(mapRiskModel))) {
                if ((!mapValidation.checkInterlinkedContinent(mapRiskModel))) {
                    if (!(mapValidation.emptyContinentValidation(mapRiskModel))) {
                        if (!(mapValidation.unlinkedContinentValidation(mapRiskModel))) {

                            System.out.println(" All the map validations are correct");
                            filename = JOptionPane.showInputDialog("File Name");
                            try {
                                System.out.println(filename);
                                mapWrite = new MapWrite();
                                mapWrite.writeMapToFile(filename, mapRiskModel);
                                JOptionPane.showMessageDialog(null, "Map has been created select it before you play");
                                new MainGame();
                                this.mapCountryConnectView.dispose();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            System.out.println("All continents are not linked");
                            JOptionPane.showOptionDialog(null, "All continents are not linked", "Invalid",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                    null);

                        }

                    } else {
                        System.out.println("Empty link country validation failed");
                        JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                null);
                    }
                } else {
                    System.out.println("ECheck interlinked Continent validation failed");
                    JOptionPane.showOptionDialog(null, "Check interlinedContinent validation failed", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);

                }
            } else {
                System.out.println("Empty continent validation failed");
                JOptionPane.showOptionDialog(null, "Empty link country validation failed", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            }

        } else if (e.getSource().equals(this.mapCountryConnectView.buttonRemove)) {

            mapRiskModel.removeNeighbouringCountry(
                    (CountryModel) this.mapCountryConnectView.leftCountryParentList.getSelectedValue(),
                    (CountryModel) this.mapCountryConnectView.rightCountryParentList.getSelectedValue());

        }

    }
    /**
     * To check whether the values in the list is changed
     *
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        System.out.println("Event for indexes " + firstIndex + " - " + lastIndex + "; isAdjusting is " + isAdjusting
                + "; selected indexes:");

        if (listSelectionModel.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
            // Looking which index is being selected
            int minRightIndex = listSelectionModel.getMinSelectionIndex();
            int maxRightIndex = listSelectionModel.getMaxSelectionIndex();
            int finalRightModelIndex = 0;
            for (int i = minRightIndex; i <= maxRightIndex; i++) {
                if (this.mapCountryConnectView.leftListSelectionModel.isSelectedIndex(i)) {
                    finalRightModelIndex = i;
                }
            }
            System.out.println(finalRightModelIndex);
        }

        if (e.getSource().equals(this.mapCountryConnectView.leftCountryParentList)) {

            mapRiskModel.setCountryColor(
                    (CountryModel) this.mapCountryConnectView.leftCountryParentList.getSelectedValue(), Color.GREEN);

        } else if (e.getSource().equals(this.mapCountryConnectView.rightCountryParentList)) {

            mapRiskModel.setCountryColor(
                    (CountryModel) this.mapCountryConnectView.rightCountryParentList.getSelectedValue(), Color.YELLOW);

        }
    }
}
