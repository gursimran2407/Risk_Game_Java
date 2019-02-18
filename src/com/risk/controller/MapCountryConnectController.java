package com.risk.controller;

import com.risk.gameplayrequirements.MapWrite;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapCountryConnectView;
import sun.applet.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gursimransingh
 */
public class MapCountryConnectController implements ListSelectionListener, ActionListener  {

    private MapRiskModel d_mapRiskModel;
    private MapCountryConnectView mapCountryConnectView;
    private CountryModel countryModel;
    private String filename = null;
    private MapWrite mapWrite;

    private List<CountryModel> countryModelList;
    private List<CountryModel> countryLinks;

    public MapCountryConnectController(MapRiskModel d_mapRiskModel) {
        d_mapRiskModel = d_mapRiskModel;
        this.countryModelList = d_mapRiskModel.getCountryModelList();
        this.countryLinks = new ArrayList<CountryModel>();

        this.mapCountryConnectView = new MapCountryConnectView(d_mapRiskModel);
        this.mapCountryConnectView.setActionListener(this);
        this.mapCountryConnectView.setListSelectionListener(this);
        this.mapCountryConnectView.setVisible(true);
        d_mapRiskModel.addObserver(this.mapCountryConnectView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mapCountryConnectView.buttonAdd)) {
            if (mapCountryConnectView.leftCountryParentList.getSelectedValue()
                    .equals(mapCountryConnectView.rightCountryParentList.getSelectedValue())) {
                JOptionPane.showOptionDialog(null, "Cannot create a self link", "Invalid", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                return;
            } else {

                d_mapRiskModel.setNeighbouringCountry(
                        (CountryModel) this.mapCountryConnectView.leftCountryParentList.getSelectedValue(),
                        (CountryModel) this.mapCountryConnectView.rightCountryParentList.getSelectedValue());

            }
        } else if (e.getSource().equals(this.mapCountryConnectView.buttonSave)) {
            Validation MapValidation = new Validation();
            boolean flag1 = MapValidation.emptyLinkCountryValidation(d_mapRiskModel);

            boolean flag3 = MapValidation.emptyContinentValidation(d_mapRiskModel);
            boolean flag2 = MapValidation.checkInterlinkedContinent(d_mapRiskModel);
            System.out.println(flag1 + " " + flag2 + " " + flag3);
            if (!(MapValidation.emptyLinkCountryValidation(d_mapRiskModel))) {
                if ((!MapValidation.checkInterlinkedContinent(d_mapRiskModel))) {
                    if (!(MapValidation.emptyContinentValidation(d_mapRiskModel))) {
                        if (!(MapValidation.unlinkedContinentValidation(d_mapRiskModel))) {

                            System.out.println(" All the map validations are correct");
                            filename = JOptionPane.showInputDialog("File Name");
                            try {
                                System.out.println(filename);
                                mapWrite = new MapWrite();
                                mapWrite.writeMapToFile(filename, d_mapRiskModel);
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

            d_mapRiskModel.removeNeighbouringCountry(
                    (CountryModel) this.mapCountryConnectView.leftCountryParentList.getSelectedValue(),
                    (CountryModel) this.mapCountryConnectView.rightCountryParentList.getSelectedValue());

        }

    }

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

            d_mapRiskModel.setCountryColor(
                    (CountryModel) this.mapCountryConnectView.leftCountryParentList.getSelectedValue(), Color.GREEN);

        } else if (e.getSource().equals(this.mapCountryConnectView.rightCountryParentList)) {

            d_mapRiskModel.setCountryColor(
                    (CountryModel) this.mapCountryConnectView.rightCountryParentList.getSelectedValue(), Color.YELLOW);

        }
    }
}
