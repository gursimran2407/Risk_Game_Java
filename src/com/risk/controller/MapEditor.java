package com.risk.controller;

import java.util.Scanner;

import com.risk.model.MapRiskModel;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MapEditor extends Application
{

    public MapEditor()
    {
        //  MapRiskModel = new MapRiskModel();
    }

    public void addContinent(String continentName)
    {
        System.out.println("Please enter the names of the continents that you want to enter");
        Scanner sc = new Scanner(System.in);

//        this.MapRiskModel.getContinents().add(sc.nextLine());
//        this.MapRiskModel.setContinents(this.MapRiskModel.getContinents());

    }




    @Override
    public void start(Stage primaryStage) throws IOException
    {

    }
}
