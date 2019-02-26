package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.ReinforcementView;

/**
 * In Reinforcement Controller, the data flow into model object and updates the
 * view whenever data changes.
 * @author Karandeep
 *
 */
public class ReinforcementController implements ActionListener {

    private ReinforcementView theReinforcementView;
    private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModel = null;
    private int noOfPlayers;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param mapRiskModel
     */
    public ReinforcementController(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
        this.mapRiskModel.getPlayerTurn().setNumberofArmies(this.calculateArmies());
        theReinforcementView = new ReinforcementView(this.mapRiskModel);
        theReinforcementView.setActionListener(this);
        theReinforcementView.setVisible(true);

        this.mapRiskModel.addObserver(theReinforcementView);
        for (int i = 0; i < noOfPlayers; i++) {
            this.listOfPlayers.get(i).addObserver(this.theReinforcementView);
        }
    }

    /**
     * Calculation of Number of Reinforcement Armies
     *
     * @return Integer
     */
    public int calculateArmies() {
        int reinforceArmies = 0;

        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().equals(this.mapRiskModel.getPlayerTurn())) {
                this.listOfCountrys.add(this.mapRiskModel.getCountryModelList().get(i));
            }
        }
        if (listOfCountrys.size() > 3) {
            reinforceArmies = 3 + Math.round(listOfCountrys.size() / 3);
        } else {
            reinforceArmies = 3;
        }
        if (reinforceArmies > 12) {
            reinforceArmies = 12;
        }
        return reinforceArmies;
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.theReinforcementView.addButton)) {
            int selectedArmies = 0;
            if (theReinforcementView.numOfTroopsComboBox.getSelectedItem() != null) {
                selectedArmies = (int) theReinforcementView.numOfTroopsComboBox.getSelectedItem();
                CountryModel countryName = (CountryModel) theReinforcementView.countryListComboBox.getSelectedItem();
                this.mapRiskModel.setSelectedArmiesToCountries(selectedArmies, countryName);
            } else {

                new FortificationController(this.mapRiskModel);
                this.theReinforcementView.dispose();
            }
        }
    }
}
