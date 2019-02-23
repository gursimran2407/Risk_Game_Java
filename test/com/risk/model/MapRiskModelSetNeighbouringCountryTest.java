package com.risk.model;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapWrite;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author gursimransingh
 */
public class MapRiskModelSetNeighbouringCountryTest {
    private static boolean setUpIsDone = false;
    List<ContinentModel> continentList;
    List<CountryModel> countryList;
    MapRiskModel mapRiskModel;
    MapRead mapRead;
    File file;

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return;
        }
        // do the setup
        mapRead = new MapRead();
        file = new File(Constants.filePath.toUri());
        System.out.println(file.toString() + "FILEREAD");
        mapRead.setReadFile(file);
        continentList = mapRead.getMapContinentDetails();
        countryList = mapRead.getMapCountryDetails();
        mapRiskModel = new MapRiskModel();
        setUpIsDone = true;
    }

    //Connecting countries of index 0 and 1.
    @Test
    public void setNeighbouringCountry() {
        assertEquals(true, mapRiskModel.setNeighbouringCountry(countryList.get(0), countryList.get(1)));
    }
}