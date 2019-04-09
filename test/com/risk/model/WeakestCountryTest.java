package com.risk.model;

import com.risk.utilities.Constant;
import com.risk.utilities.ReadFile;
import com.risk.utilities.Validation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class WeakestCountryTest {
    GameMapModel gameMapModel;
    GamePlayModel gamePlayModel;
    Validation val;
    ReadFile readFile;
    File file;
    ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
    ArrayList<CountryModel> cardList = new ArrayList<CountryModel>();



    private static boolean setUpIsDone = false;

    /**
     * Set up file
     */
    @Before
    public void setUp() throws Exception {
        if (setUpIsDone) {
            return;
        }

        readFile = new ReadFile();
        countryList.add(new CountryModel("India",0,0,"Asia",null,2,"Bill"));
        countryList.add(new CountryModel("China",0,0,"Asia",null,5,"Bill"));
        countryList.add(new CountryModel("China",0,0,"Asia",null,5,"Bill"));
        countryList.add(new CountryModel("China",0,0,"Asia",null,5,"Bill"));
        countryList.add(new CountryModel("China",0,0,"Asia",null,5,"Bill"));
        countryList.add(new CountryModel("China",0,0,"Asia",null,5,"Bill"));
        countryList.add(new CountryModel("China",0,0,"Asia",null,5,"Bill"));
        file = new File(Constant.filePath.toUri());
        readFile.setFile(file);
        gameMapModel = new GameMapModel(file);
        gamePlayModel = new GamePlayModel();
        gamePlayModel.setGameMap(gameMapModel);
        setUpIsDone = true;
    }

    /**
     * Test weakest country
     */
    @Test
    public void testWeakestCountry()
    {
        cardList= gamePlayModel.selectWeakestCountry(countryList);
        Assert.assertEquals(cardList.get(0).getCountryName(),"India");

    }

}