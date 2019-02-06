package com.risk.controller;


import javafx.application.Application;
import java.util.Scanner;
import com.risk.model.ContinentMod;
import com.risk.model.MapRiskMod;
import javafx.stage.Stage;

import java.io.IOException;

/** this is the Continent creation controller which will help update the data in the Model of the map using the Continent Model and
 * will update the view whenever the data is changed.
 * @author Karan
 */
public class MapContinentCont extends Application {


    private MapRiskMod mapRiskMod;
    ContinentMod continentModVarTemp;

    continentModVarTemp = new MapContinentCont(this.)

    public MapContinentCont() {
        this.mapRiskMod = new MapRiskMod();

    }
// this is my testing function to understand if the flow that i have decided is working on not./

    // this function should be replaced by the actual function of the JAVA FX that we are going to use as our UI.

    public void addContinent(String continentName) {
        System.out.println("Please enter the names of the continents that you want to enter");
        Scanner sc = new Scanner(System.in);

        continentModVarTemp = new ContinentMod(this.createContinentView.continentValue.methodname());
        // this is taking the value from the continent view once it is set up.
        this.mapRiskMod.getContinentModList().add(sc.next());
        this.mapRiskMod.setContinentModList(this.mapRiskMod.getContinentModList());

    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {

    }
}