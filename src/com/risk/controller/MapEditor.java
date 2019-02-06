package com.risk.controller;

import java.util.Scanner;

import com.risk.model.MapRiskMod;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MapEditor extends Application
{

    public MapEditor()
    {
        this.MapRisk = new MapRiskMod();
    }

    public void addContinent(String continentName)
    {
        System.out.println("Please enter the names of the continents that you want to enter");
        Scanner sc = new Scanner(System.in);

        this.MapRisk.getContinents().add(sc.nextLine());
        this.MapRisk.setContinents(this.MapRisk.getContinents());

    }




    @Override
    public void start(Stage primaryStage) throws IOException
    {

    }
}
