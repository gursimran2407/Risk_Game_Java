package com.risk.controller;

import com.risk.gameplayrequirements.MapWrite;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author gursimransingh
 */
public class MapCountryConnectController implements ListSelectionListener, ActionListener  {

    private MapRiskModel mapRiskModel;
    private MapCountryConnectView mapCountryConnectView;
    private CountryModel newCountryModel;
    private String filename = null;
    private MapWrite mapWrite;


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
