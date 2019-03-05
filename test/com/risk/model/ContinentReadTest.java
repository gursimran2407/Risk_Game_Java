package com.risk.model;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ContinentReadTest {
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
    public void readContinentTest()
    {
        Assert.assertEquals(true,mapRead.validateReadContinent(mapRiskModel.getContinentModelList(), mapRead.getMapContinentDetails()));
    }
}
