package com.risk.controller;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.StartupView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gursimransingh
 */
public class StartupController {
    private StartupView startupView;
    private List<CountryModel> countryModelArrayList = new ArrayList<>();
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<>();
    private MapRiskModel mapRiskModel;
    private int numberOfPlayers;
    private int[] noOfCountryForRuler = new int[5];
    private String[] colorForRuler = new String[5];
    private int[] totalArmiesPlayer = new int[5];
    private int[] remainArmies = new int[5];
    private int loopValue = 0;
    private boolean armiesNull = false;
    private int initial = 0;


    public StartupController(ArrayList<PlayerModel> listOfPlayers, MapRiskModel mapRiskModelobj) {
        this.listOfPlayers = listOfPlayers;
        this.mapRiskModel = mapRiskModelobj;

        countryModelArrayList = this.mapRiskModel.getCountryModelList();
        numberOfPlayers = listOfPlayers.size();

        allocateArmies();
        checkForOverallArmies();
        initial = 1;

        if (armiesNull == false) {
            while (listOfPlayers.get(loopValue).getremainTroop() == 0) {
                loopValue++;
                if (loopValue > listOfPlayers.size()) {
                    loopValue = 0;
                    break;
                }
            }
            this.theStartUpView = new StartUpView(this.gameMapModel, this.listOfPlayers.get(loopValue));
            this.theStartUpView.setActionListener(this);
            for (int i = 0; i < noOfPlayers; i++) {
                this.listOfPlayers.get(i).addObserver(this.theStartUpView);
            }
            this.gameMapModel.addObserver(theStartUpView);
            this.theStartUpView.setVisible(true);
        }
        this.gameMapModel.setListOfPlayers(listOfPlayers);

    }
}
