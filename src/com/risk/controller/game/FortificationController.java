package com.risk.controller.game;

import com.risk.gameplayrequirements.MapValidation;
import com.risk.model.MapRiskModel;
import com.risk.model.CountryModel;
import com.risk.view.FortificationView;

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

    private FortificationView theFortificationView;
    private MapRiskModel mapRiskModel = null;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param mapRiskModel
     */
    public FortificationController(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
        theFortificationView = new FortificationView(this.mapRiskModel);
        theFortificationView.setActionListener(this);
        theFortificationView.setItemListener(this);
        theFortificationView.setVisible(true);
        this.mapRiskModel.addObserver(this.theFortificationView);
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.theFortificationView.moveButton)) {

            // BFS
            MapValidation val = new MapValidation();
            if (val.checkIfValidMove(mapRiskModel,
                    (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
                    (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem())) {
                this.mapRiskModel.setMovingArmies(
                        (Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem(),
                        (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
                        (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem());
            }

            int index = this.mapRiskModel.getIndexOfPlayer();
            index++;
            if (this.mapRiskModel.getPlayerModelList().size() > index) {
                this.mapRiskModel.setIndexOfPlayer(index);
                this.mapRiskModel.getPlayerModelList().get(index).callObservers();
                new PlayerGameController(this.mapRiskModel, this.mapRiskModel.getPlayerModelList());
                this.theFortificationView.dispose();
            } else {
                JOptionPane.showOptionDialog(null, "Bravo! Game is over! No one won!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                this.theFortificationView.dispose();
            }

        } else if (actionEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
            this.mapRiskModel
                    .setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
        }
    }

    /**
     * Item Listener
     *
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
            this.mapRiskModel
                    .setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
        }
    }
}
