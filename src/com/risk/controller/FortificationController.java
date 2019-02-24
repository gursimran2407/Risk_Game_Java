package com.risk.controller;

import com.risk.gameplayrequirements.MapValidation;
import com.risk.model.MapRiskModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * In FortificationController, the data flow into model object and updates the
 * view whenever data changes.
 * @author Karandeep
 */

    public class FortificationController implements ActionListener, ItemListener {

        private FortificationView fortificationView;
        private MapRiskModel mapRiskModel = null;

        /**
         * Constructor initializes values and sets the screen too visible
         *
         * @param mapRiskModel
         */
        public FortificationController(MapRiskModel mapRiskModel) {
            this.mapRiskModel = mapRiskModel;
            fortificationView = new FortificationView(this.mapRiskModel);
            fortificationView.setActionListener(this);
            fortificationView.setItemListener(this);
            fortificationView.setVisible(true);
            this.mapRiskModel.addObserver(this.fortificationView);
        }

        /**
         * This method performs action, by Listening the action event set in view.
         *
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource().equals(this.fortificationView.moveButton)) {

                // BFS
                MapValidation val = new MapValidation();
                if (val.checkIfValidMove(mapRiskModel,
                        (CountryModel) this.fortificationView.fromCountryListComboBox.getSelectedItem(),
                        (CountryModel) this.fortificationView.toCountryListComboBox.getSelectedItem())) {
                    this.mapRiskModel.setMovingArmies(
                            (Integer) this.fortificationView.numOfTroopsComboBox.getSelectedItem(),
                            (CountryModel) this.fortificationView.fromCountryListComboBox.getSelectedItem(),
                            (CountryModel) this.fortificationView.toCountryListComboBox.getSelectedItem());
                }

                int index = this.mapRiskModel.getPlayerIndex();
                index++;
                if (this.mapRiskModel.getListOfPlayers().size() > index) {
                    this.mapRiskModel.setPlayerIndex(index);
                    this.mapRiskModel.getListOfPlayers().get(index).callObservers();
                    new GamePlayController(this.gameMapModel, this.mapRiskModel.getListOfPlayers());
                    this.fortificationView.dispose();
                } else {
                    JOptionPane.showOptionDialog(null, "Bravo! Game is over! No one won!", "Valid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                    this.fortificationView.dispose();
                }

            } else if (actionEvent.getSource().equals(this.fortificationView.fromCountryListComboBox)) {
                this.mapRiskModel
                        .setSelectedComboBoxIndex(this.fortificationView.fromCountryListComboBox.getSelectedIndex());
            }

        }

        /**
         * Item Listener
         *
         * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
         */
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (itemEvent.getSource().equals(this.fortificationView.fromCountryListComboBox)) {
                this.mapRiskModel
                        .setSelectedComboBoxIndex(this.fortificationView.fromCountryListComboBox.getSelectedIndex());
            }

        }
}
