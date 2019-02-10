package com.risk.controller;

import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import java.util.ArrayList;

/** This file consist of methods for connecting map with neighbouring country during map editing phase
 There needs to be a view for controlling this map connection

 *
 */
public class MapCountryController {

    private MapRiskModel mapRiskModel;

    private ArrayList<CountryModel> countryModelArrayList;

    private ArrayList<CountryModel> countryModelArrayLinkedList;

    private CountryModel countryModel;

    private String newFile = null;

    public MapCountryController(MapRiskModel mapRiskModel) {
        this.mapRiskModel = mapRiskModel;
        this.countryModelArrayList = this.mapRiskModel.getCountryModelList();
        this.countryModelArrayLinkedList = new ArrayList<>();

    }

    public ArrayList<CountryModel> getCountryModelArrayList()
    {
        return countryModelArrayList;
    }

    /* This controller will connect through view , map editor view which is in production.
    * Processing on logic for country linking*/

}
