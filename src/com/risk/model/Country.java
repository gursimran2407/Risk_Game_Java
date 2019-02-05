package com.risk.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    String countryName;
    Player countryOwner;
    List<Country> adjacentCountriesList = new ArrayList<Country>();

}
