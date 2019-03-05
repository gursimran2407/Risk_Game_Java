package com.risk.view.game;

import com.risk.model.CountryModel;

/**
 * In this file, the movement between countries will be monitored and
 * processed accordingly
 */

public class MoveData {

    private final int numOfTroops;
    private final CountryModel from;
    private final CountryModel to;

    /**
     * Constructor for movement of troops between country model from-to
     * @param numOfTroops
     * @param from
     * @param to
     */
    public MoveData(int numOfTroops, CountryModel from, CountryModel to) {
        this.numOfTroops = numOfTroops;
        this.from = from;
        this.to = to;
    }

    /**
     * @return total number of troops
     */
    public int getNumOfTroops() {
        return numOfTroops;
    }

    /**
     * @return source movement
     */
    public CountryModel getFrom() {
        return from;
    }

    /**
     * @return destination movement
     */
    public CountryModel getTo() {
        return to;
    }
}
