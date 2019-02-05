package com.risk.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryName;
    private Player countryOwner;
    private List<Country> adjacentCountriesList = new ArrayList<Country>();
    private CountryCoordinates countryCoordinates;

}
