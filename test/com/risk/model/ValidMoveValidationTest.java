package com.risk.model;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class ValidMoveValidationTest {

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
        mapRead = new MapRead();
        file = new File(Constants.filePath.toUri());
        mapRead.setReadFile(file);
        mapValidation = new MapValidation();
        mapRiskModel = new MapRiskModel(file);
        setUpIsDone = true;

    }

    @Test
    public void testContinentLinkingValidation()
    {
        assertTrue(mapValidation.checkIfValidMove(mapRiskModel, mapRiskModel.getCountryModelList().get(0),
                mapRiskModel.getCountryModelList().get(1)));
    }

}