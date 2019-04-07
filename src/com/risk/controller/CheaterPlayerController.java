package com.risk.controller;

import com.risk.helperInterfaces.Strategy;
import com.risk.model.GamePlayModel;
import com.risk.utilities.Validation;

/**
 * CheaterPlayerController allows the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Shriyans
 * @version 1.0
 */
public class CheaterPlayerController implements Strategy {

    /** The game play model */
    private GamePlayModel gamePlayModel;

    /** The validation */
    private Validation val = new Validation();

    /** The index 1 and index 2 */
    private int index1, index2;

    /**
     *  Constructor initializes values and sets screen visibility
     * @param gamePlayModel
     */
    public CheaterPlayerController(GamePlayModel gamePlayModel) {
        this.gamePlayModel = gamePlayModel;
    }

    @Override
    public void fortification() {
        this.gamePlayModel.getConsole().append("Cheater - fortification");
        index1 = getRandomBetweenRange(1, this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());
        index1 = index1 - 1;

        index2 = indexRandomvalues();
        index2 = index2 - 1;

        int armies = (this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1).getArmies() - 1)
                * 2;
        if (val.checkIfValidMove(this.gamePlayModel.getGameMap(),
                this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1),
                this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index2))) {
            this.gamePlayModel.getGameMap().setMovingArmies(armies,
                    this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1),
                    this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index2));
            this.gamePlayModel.getConsole().append("From Country "
                    + this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index1).getCountryName()
                    + "armies " + armies + " has been moved to "
                    + this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(index2).getCountryName());
        }

    }

    @Override
    public void attack() {

    }

    @Override
    public void reinforcement() {

    }

    /**
     * This method generated random number within two values
     * @param min
     * @param max
     * @return
     */

    public int getRandomBetweenRange(double min, double max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }
    public int indexRandomvalues() {
        boolean flag = false;
        index2 = getRandomBetweenRange(1, this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());

        if (index1 == index2) {
            flag = true;
        }
        while (flag == true) {
            index2 = getRandomBetweenRange(1,
                    this.gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size());
            if (index1 != index2) {
                flag = false;
            }
        }
        return index2;
}
