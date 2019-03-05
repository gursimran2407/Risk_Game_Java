package com.risk.controller.game;

import com.risk.Environment;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.game.IReinforcementView;

import java.util.ArrayList;
import java.util.List;

/**
 * In Reinforcement Controller, the data flow into model object and updates the
 * view whenever data changes.
 * @author Karandeep
 *
 */
public class ReinforcementController {

    private final Environment environment;

    private IReinforcementView view;
    private List<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
    private List<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModel = null;
    private int noOfPlayers;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param mapRiskModel
     */
    public ReinforcementController(final Environment environment, MapRiskModel mapRiskModel) {
        this.environment = environment;

        this.mapRiskModel = mapRiskModel;
        this.mapRiskModel.getPlayerTurn().setNumberofArmies(this.calculateArmies());

        view = environment.getViewManager().createReinforcementView(this.mapRiskModel);
        view.addAddListener(this::setSelectedArmiesToCountries);
        view.showView();

        this.mapRiskModel.addObserver(view);
        for (int i = 0; i < noOfPlayers; i++) {
            this.listOfPlayers.get(i).addObserver(this.view);
        }
    }

    private void setSelectedArmiesToCountries(final Object numOfTroopsAsItem, final CountryModel countryName) {
        int selectedArmies = 0;
        if (numOfTroopsAsItem != null) {
            selectedArmies = (int) numOfTroopsAsItem;
            this.mapRiskModel.setSelectedArmiesToCountries(selectedArmies, countryName);
        } else {
            new FortificationController(environment, this.mapRiskModel);
            this.view.hideView();
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
}
