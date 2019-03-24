package com.risk.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.utilities.Validation;
import com.risk.utilities.WriteMap;
import com.risk.view.ConnectCountryView;


/**
 * In ConnectCountryController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @author Karan
 *
 *
 */

public class ConnectCountryController implements ActionListener, ListSelectionListener {

    /** The game map model. */
    private GameMapModel gameMapModel;

    /** The connect country view. */
    private ConnectCountryView connectCountryView;

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param mapModel the map model
     */
    ConnectCountryController(GameMapModel mapModel) {

        this.gameMapModel = mapModel;
        this.connectCountryView = new ConnectCountryView(this.gameMapModel);

        this.connectCountryView.setActionListener(this);
        this.connectCountryView.setListSelectionListener(this);
        this.connectCountryView.setVisible(true);
        this.gameMapModel.addObserver(this.connectCountryView);
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.connectCountryView.addButton)) {
            if (connectCountryView.countryParentListLeft.getSelectedValue()
                    .equals(connectCountryView.countryParentListRight.getSelectedValue())) {
                JOptionPane.showOptionDialog(null, "Cannot create a self link", "Invalid", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            } else {

                this.gameMapModel.setNeighbouringCountry(
                        (CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue(),
                        (CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue());

            }
        } else if (actionEvent.getSource().equals(this.connectCountryView.saveButton)) {
            Validation MapValidation = new Validation();
            boolean flag1 = MapValidation.emptyLinkCountryValidation(this.gameMapModel);

            boolean flag3 = MapValidation.emptyContinentValidation(this.gameMapModel);
            boolean flag2 = MapValidation.checkInterlinkedContinent(this.gameMapModel);
            System.out.println(flag1 + " " + flag2 + " " + flag3);
            if (!(MapValidation.emptyLinkCountryValidation(this.gameMapModel))) {
                if ((!MapValidation.checkInterlinkedContinent(this.gameMapModel))) {
                    if (!(MapValidation.emptyContinentValidation(this.gameMapModel))) {
                        if (!(MapValidation.unlinkedContinentValidation(this.gameMapModel))) {

                            System.out.println(" All the map validations are correct");
                            /* The filename. */
                            String filename = JOptionPane.showInputDialog("File Name");
                            try {
                                System.out.println(filename);
                                /* The temp write. */
                                WriteMap tempWrite = new WriteMap();
                                tempWrite.writeMapToFile(filename, this.gameMapModel);
                                JOptionPane.showMessageDialog(null, "Map has been created select it before you play");
                                new WelcomeScreenController();
                                this.connectCountryView.dispose();
                            } catch (Exception e) {
                                e.printStackTrace();
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

        } else if (actionEvent.getSource().equals(this.connectCountryView.removeButton)) {

            this.gameMapModel.removeNeighbouringCountry(
                    (CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue(),
                    (CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue());

        }

    }

    /**
     * Check for the List is changed.
     *
     * @param e the e
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

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
                if (this.connectCountryView.listSelectionModelLeft.isSelectedIndex(i)) {
                    finalRightModelIndex = i;
                }
            }
            System.out.println(finalRightModelIndex);
        }

        if (e.getSource().equals(this.connectCountryView.countryParentListLeft)) {

            this.gameMapModel.setColorToCountry(
                    (CountryModel) this.connectCountryView.countryParentListLeft.getSelectedValue(), Color.GREEN);

        } else if (e.getSource().equals(this.connectCountryView.countryParentListRight)) {

            this.gameMapModel.setColorToCountry(
                    (CountryModel) this.connectCountryView.countryParentListRight.getSelectedValue(), Color.YELLOW);

        }
    }

}
