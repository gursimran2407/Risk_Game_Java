package com.risk.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import com.risk.Environment;
import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.utilities.Validation;
import com.risk.utilities.WriteMap;
import com.risk.view.IConnectCountryView;
import com.risk.view.events.ViewActionEvent;
import com.risk.view.events.ViewActionListener;
import com.risk.view.events.ViewListSelectionEvent;
import com.risk.view.events.ViewListSelectionListener;

/**
 * In ConnectCountryController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @version 1.0.0
 *
 */

public class ConnectCountryController implements ViewActionListener, ViewListSelectionListener {

    /** The game map model. */
    private GameMapModel gameMapModel;

    /** The connect country view. */
    private IConnectCountryView connectCountryView;

    /** The country list. */
    private List<CountryModel> countryList;

    /** The country list links. */
    private List<CountryModel> countryListLinks;

    /** The new country model. */
    private CountryModel newCountryModel;

    /** The filename. */
    private String filename = null;

    /** The temp write. */
    private WriteMap tempWrite;

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param mapModel the map model
     */
    public ConnectCountryController(GameMapModel mapModel) {

        this.gameMapModel = mapModel;
        this.countryList = this.gameMapModel.getCountries();
        this.countryListLinks = new ArrayList<CountryModel>();
        this.connectCountryView =
                Environment.getInstance().getViewManager().newConnectCountryView(this.gameMapModel);

        this.connectCountryView.addActionListener(this);
        this.connectCountryView.addListSelectionListener(this);
        this.connectCountryView.showView();
        this.gameMapModel.addObserver(this.connectCountryView);
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param event the action event
     * @see ViewActionListener
     */
    @Override
    public void actionPerformed(ViewActionEvent event) {
        if (IConnectCountryView.ACTION_ADD.equals(event.getSource())) {
            if (connectCountryView.getCountryParentLeft()
                    .equals(connectCountryView.getCountryParentRight())) {
                JOptionPane.showOptionDialog(null, "Cannot create a self link", "Invalid", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                return;
            } else {

                this.gameMapModel.setNeighbouringCountry(
                        this.connectCountryView.getCountryParentLeft(),
                        this.connectCountryView.getCountryParentRight());

            }
        } else if (IConnectCountryView.ACTION_SAVE.equals(event.getSource())) {
            Validation MapValidation = new Validation();
            boolean flag1 = MapValidation.emptyLinkCountryValidation(this.gameMapModel);

            boolean flag3 = MapValidation.emptyContinentValidation(this.gameMapModel);
            boolean flag2 = MapValidation.checkInterlinkedContinent(this.gameMapModel);
            System.out.println(flag1 + " " + flag2 + " " + flag3);
            if (!(MapValidation.emptyLinkCountryValidation(this.gameMapModel))) {
                if ((!MapValidation.checkInterlinkedContinent(this.gameMapModel))) {
                    if (!(MapValidation.emptyContinentValidation(this.gameMapModel))) {

                        System.out.println(" All the map validations are correct");
                        filename = JOptionPane.showInputDialog("File Name");
                        try {
                            System.out.println(filename);
                            tempWrite = new WriteMap();
                            tempWrite.writeMapToFile(filename, this.gameMapModel);
                            JOptionPane.showMessageDialog(null, "Map has been created select it before you play");
                            new WelcomeScreenController();
                            this.connectCountryView.hideView();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    System.out.println("Empty link country validation failed");
                    JOptionPane.showOptionDialog(null, "Empty continent validation failed", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                }
            } else {
                System.out.println("ECheck interlinked Continent validation failed");
                JOptionPane.showOptionDialog(null, "Check interlinedContinent validation failed", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);

            }
        } else if (IConnectCountryView.ACTION_REMOVE.equals(event.getSource())) {
            this.gameMapModel.removeNeighbouringCountry(
                    this.connectCountryView.getCountryParentLeft(),
                    this.connectCountryView.getCountryParentRight());
        } else {
            System.out.println("All continents are not linked");
            JOptionPane.showOptionDialog(null, "One of the continent is invalid", "Invalid", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
        }
    }

    /**
     * Check for the List is changed.
     *
     * @param e the e
     * @see ViewListSelectionListener
     */
    @Override
    public void valueChanged(ViewListSelectionEvent e) {
        ListSelectionModel lsm = e.getListSelectionModel();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        System.out.println("Event for indexes " + firstIndex + " - " + lastIndex + "; isAdjusting is " + isAdjusting
                + "; selected indexes:");

        if (lsm.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
            // Find out which indexes are selected.
            int minRightIndex = lsm.getMinSelectionIndex();
            int maxRightIndex = lsm.getMaxSelectionIndex();
            int finalRightModelIndex = 0;
            for (int i = minRightIndex; i <= maxRightIndex; i++) {
                if (this.connectCountryView.isSelectedLeftModelIndex(i)) {
                    finalRightModelIndex = i;
                }
            }
            System.out.println(finalRightModelIndex);
        }

        if (IConnectCountryView.SELECTED_CHANGED_LEFT.equals(e.getSource())) {
            this.gameMapModel.setColorToCountry(
                    this.connectCountryView.getCountryParentLeft(), Color.GREEN);
        } else if (IConnectCountryView.SELECTED_CHANGED_RIGHT.equals(e.getSource())) {
            this.gameMapModel.setColorToCountry(
                    this.connectCountryView.getCountryParentRight(), Color.YELLOW);
        }
    }
}
