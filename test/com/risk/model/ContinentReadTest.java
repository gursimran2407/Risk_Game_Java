package com.risk.model;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * This test case is to read continent
 *
 * @author Namita Faujdar
 */
public class ContinentReadTest {
    private static boolean setUpIsDone = false;
    MapRiskModel mapRiskModel;
    MapValidation mapValidation;
    MapRead mapRead;
    File file;

    /**
     * Set up variables
     */
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

    /**
     * Test to read continents from file
     */
    @Test
    public void readContinentTest()
    {
        Assert.assertEquals(true,mapRead.validateReadContinent(mapRiskModel.getContinentModelList(), mapRead.getMapContinentDetails()));
    }
}
