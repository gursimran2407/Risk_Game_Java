package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.ReinforcementView;

public class ReinforcementController implements ActionListener {

    private ReinforcementView reinforcementView;
    private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModel = null;
    private int noOfPlayers;

    public ReinforcementController(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
        this.mapRiskModel.getPlayerTurn().setmyPlayer(this.calculateArmies());
        reinforcementView = new ReinforcementView(this.mapRiskModel);
        reinforcementView.setActionListener(this);
        reinforcementView.setVisible(true);

        this.mapRiskModel.addObserver(reinforcementView);
        for (int i = 0; i < noOfPlayers; i++) {
            this.listOfPlayers.get(i).addObserver(this.reinforcementView);
        }
    }
/**
 * Calculation of Number of Reinforcement Armies
 *
 * @return Integer
 */

        public int calculateArmies () {
            int reinforcementArmies = 0;

            for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
                if (this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().equals(this.mapRiskModel.getPlayerTurn())) {
                    this.listOfCountrys.add(this.mapRiskModel.getCountryModelList().get(i));
                }
            }
            if (listOfCountrys.size() > 3) {
                reinforcementArmies = 3 + Math.round(listOfCountrys.size() / 3);
            } else {
                reinforcementArmies = 3;
            }
            if (reinforcementArmies > 12) {
                reinforcementArmies = 12;
            }
            return reinforcementArmies;
        }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.reinforcementView.addButton)) {
            int selectedArmies = 0;
            if (reinforcementView.numOfTroopsComboBox.getSelectedItem() != null) {
                selectedArmies = (int) reinforcementView.numOfTroopsComboBox.getSelectedItem();
                CountryModel countryName = (CountryModel) reinforcementView.countryListComboBox.getSelectedItem();
                this.mapRiskModel.setSelectedArmiesToCountries(selectedArmies, countryName);
            } else {

                new FortificationController(this.gameMapModel);
                this.reinforcementView.dispose();
            }
        }
    }

}
