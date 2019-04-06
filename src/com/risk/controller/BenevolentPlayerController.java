package com.risk.controller;

import com.risk.helperInterfaces.Strategy;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.utilities.Validation;

import java.util.ArrayList;

public class BenevolentPlayerController implements Strategy {

    private GamePlayModel gamePlayModel;
    private Validation val = new Validation();

    public BenevolentPlayerController(GamePlayModel gamePlayModel) {
        this.gamePlayModel = gamePlayModel;
    }

    @Override
    public void reinforcement() {
        this.gamePlayModel.getConsole().append("Benevolent - reinforcement");
        this.gamePlayModel.getConsole()
                .append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
        this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
                + this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));
        int value = this.gamePlayModel.numberOfCountries()
                + this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn());
        this.gamePlayModel.getConsole().append("Number of armies assigned to the player: " + value);

        // select Weakest Country
        ArrayList<CountryModel> listofcountry = this.gamePlayModel.selectWeakestCountry(
                (ArrayList<CountryModel>) gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries());

        if (listofcountry.size() > 3) {
            int selectedArmies = Math.round(this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop() / 3);
            int index;
            for (int i = 0; i < 3; i++) {
                index = getRandomBetweenRange(1, listofcountry.size());
                this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, listofcountry.get(index - 1));
                this.gamePlayModel.getConsole().append("Number of armies assigned to the"
                        + listofcountry.get(index - 1).getCountryName() + " is " + selectedArmies);

            }

        } else {
            int selectedArmies = Math
                    .round(this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop() / listofcountry.size());
            for (int i = 0; i < listofcountry.size(); i++) {
                this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, listofcountry.get(i));
                this.gamePlayModel.getConsole().append("Number of armies assigned to the"
                        + listofcountry.get(i).getCountryName() + " is " + selectedArmies);
            }
        }
    }

    @Override
    public void attack() {

    }

    @Override
    public void fortification() {

    }

    public int getRandomBetweenRange(double min, double max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

}
