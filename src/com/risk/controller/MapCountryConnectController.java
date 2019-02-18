package com.risk.controller;

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

    public MapCountryConnectController(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
        this.countryModelList = this.mapRiskModel.getCountryModelList();
        this.countryLinks = new ArrayList<CountryModel>();

        this.mapCountryConnectView = new MapCountryConnectView(this.mapRiskModel);
        this.mapCountryConnectView.setActionListener(this);
        this.mapCountryConnectView.setListSelectionListener(this);
        this.mapCountryConnectView.setVisible(true);
        this.mapRiskModel.addObserver(this.mapCountryConnectView);
    }

    /**
     * Constructor
     */


    @Override
    public void actionPerformed(ActionEvent e) {

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
                if (this.mapCountryConnectView.listSelectionModelLeft.isSelectedIndex(i)) {
                    finalRightModelIndex = i;
                }
            }
            System.out.println(finalRightModelIndex);
        }

        if (e.getSource().equals(this.mapCountryConnectView.countryParentListLeft)) {

            this.mapRiskModel.setCountryColor(
                    (CountryModel) this.mapCountryConnectView.countryParentListLeft.getSelectedValue(), Color.GREEN);

        } else if (e.getSource().equals(this.mapCountryConnectView.countryParentListRight)) {

            this.mapRiskModel.setCountryColor(
                    (CountryModel) this.mapCountryConnectView.countryParentListRight.getSelectedValue(), Color.YELLOW);

        }
    }
}
