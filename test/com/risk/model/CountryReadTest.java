package com.risk.model;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class CountryReadTest {

    private static boolean setUpIsDone = false;
    MapRiskModel mapRiskModel;
    MapValidation mapValidation;
    MapRead mapRead;
    File file;

    @Before
    public void setUp()
    {
        if(setUpIsDone)
        {
            return;
        }
        this.mapRead = new MapRead();
        file = new File(Constants.filePath.toUri());
        this.mapRead.setReadFile(file);
        mapValidation = new MapValidation();
        mapRiskModel = new MapRiskModel(file);
        setUpIsDone = true;
    }

    @Test
    public void readCountryFile()
    {
        Assert.assertEquals(true,mapRead.validateReadCountry(mapRiskModel.getCountryModelList(), mapRead.getMapCountryDetails()));
    }
}
