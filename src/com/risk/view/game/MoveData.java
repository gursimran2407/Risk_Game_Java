package com.risk.view.game;

import com.risk.model.CountryModel;

public class MoveData {

    private final int numOfTroops;
    private final CountryModel from;
    private final CountryModel to;

    public MoveData(int numOfTroops, CountryModel from, CountryModel to) {
        this.numOfTroops = numOfTroops;
        this.from = from;
        this.to = to;
    }

    public int getNumOfTroops() {
        return numOfTroops;
    }

    public CountryModel getFrom() {
        return from;
    }

    public CountryModel getTo() {
        return to;
    }
}
