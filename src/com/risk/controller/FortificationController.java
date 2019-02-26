package com.risk.controller;

import com.risk.gameplayrequirements.MapValidation;
import com.risk.model.MapRiskModel;
import com.risk.view.Fortification;
import com.risk.model.CountryModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * In FortificationController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @author Karandeep
 */

    public class FortificationController implements ActionListener, ItemListener {

        private Fortification fortification;
        private MapRiskModel mapRiskModel = null;

        /**
         * Constructor of Fortification controller class
         *
         * @param mapRiskModel
         */
        public FortificationController(MapRiskModel mapRiskModel) {
            this.mapRiskModel = mapRiskModel;
            fortification = new Fortification(this.mapRiskModel);
            fortification.setActionListener(this);
            fortification.setItemListener(this);
            fortification.setVisible(true);
            this.mapRiskModel.addObserver(this.fortification);
        }

        /**
         * @param actionEvent Performs action whenever there is a change in fortification view class
         *
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(this.fortification.moveButton)) {

                // BFS
                MapValidation val = new MapValidation();
                if (val.checkIfValidMove(mapRiskModel,
                        (CountryModel) this.fortification.fromCountryListComboBox.getSelectedItem(),
                        (CountryModel) this.fortification.toCountryListComboBox.getSelectedItem())) {
                    this.mapRiskModel.setMovingArmies(
                            (Integer) this.fortification.numOfTroopsComboBox.getSelectedItem(),
                            (CountryModel) this.fortification.fromCountryListComboBox.getSelectedItem(),
                            (CountryModel) this.fortification.toCountryListComboBox.getSelectedItem());
                }

                int index = this.mapRiskModel.getIndexOfPlayer();
                index++;
                if (this.mapRiskModel.getPlayerModelList().size() > index) {
                    this.mapRiskModel.setIndexOfPlayer(index);
                    this.mapRiskModel.getPlayerModelList().get(index).callObservers();
                    new PlayerGameController(this.mapRiskModel, this.mapRiskModel.getPlayerModelList());
                    this.fortification.dispose();
                } else {
                    JOptionPane.showOptionDialog(null, "Bravo! Game is over! No one won!", "Valid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                    this.fortification.dispose();
                }

            } else if (actionEvent.getSource().equals(this.fortification.fromCountryListComboBox)) {
                this.mapRiskModel
                        .setSelectedComboBoxIndex(this.fortification.fromCountryListComboBox.getSelectedIndex());
            }
        }

        /**
         * Listener for item state and updates
         *
         * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
         */
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getSource().equals(this.fortification.fromCountryListComboBox)) {
                this.mapRiskModel
                        .setSelectedComboBoxIndex(this.fortification.fromCountryListComboBox.getSelectedIndex());
            }

        }
}
