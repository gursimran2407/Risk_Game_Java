package com.risk.controller.game;

import com.risk.Environment;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.game.IReinforcementView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Reinforcement Controller, also takes care of the movement of
 * data into the model corresponding to the view and the controller and
 * also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Karandeep
 */
public class ReinforcementController {

    private final Environment environment;

    private IReinforcementView view;
    private List<CountryModel> listOfCountrys = new ArrayList<CountryModel>();
    private List<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModel = null;
    private int noOfPlayers;

    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
     * @param mapRiskModel
     * @param environment
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
    /**
     * This method triggers the model class with the changes in the armies selected for each player
     * and update return the update to view.
     *
     * @param numOfTroopsAsItem
     * @param countryName
     */
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
     * This method is to calculate the number of armies
     * selected from reinforcement
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
