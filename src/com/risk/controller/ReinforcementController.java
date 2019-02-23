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

    private ReinforcementView reinforcementView;
    private ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel d_mapRiskModel;
    private int noOfPlayers;

    public ReinforcementController(MapRiskModel new_mapRiskModel) {
        d_mapRiskModel = new_mapRiskModel;
        d_mapRiskModel.getPlayerTurn().setmyPlayer(calculateArmies());
        reinforcementView = new ReinforcementView(d_mapRiskModel);
        reinforcementView.setActionListener(this);
        reinforcementView.setVisible(true);

        d_mapRiskModel.addObserver(reinforcementView);
        for (int i = 0; i < noOfPlayers; i++) {
            listOfPlayers.get(i).addObserver(reinforcementView);
        }
    }
/**
 * Calculation of Number of Reinforcement Armies
 *
 * @return Integer
 */

        public int calculateArmies () {
            int reinforcementArmies = 0;

            for (int i = 0; i < d_mapRiskModel.getCountryModelList().size(); i++) {
                if (d_mapRiskModel.getCountryModelList().get(i).getCountryOwner().equals(d_mapRiskModel.getPlayerTurn())) {
                    countryList.add(d_mapRiskModel.getCountryModelList().get(i));
                }
            }
            if (countryList.size() > 3) {
                Double d = Math.floor(countryList.size() / 3);
                reinforcementArmies = 3 + d.intValue();
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
        if (actionEvent.getSource().equals(this.reinforcementView.buttonAdd)) {
            int selectedArmies = 0;
            if (reinforcementView.noOfArmiesComboBox.getSelectedItem() != null) {
                selectedArmies = (int) reinforcementView.noOfArmiesComboBox.getSelectedItem();
                CountryModel countryName = (CountryModel) reinforcementView.countryListComboBox.getSelectedItem();
                d_mapRiskModel.setSelectedArmiesToCountries(selectedArmies, countryName);
            } else {

                //new FortificationController(d_mapRiskModel);
                this.reinforcementView.dispose();
            }
        }
    }

}
