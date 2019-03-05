package com.risk.model;

import com.risk.MapRiskModel;
import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

public class CheckInterlinkedContinentTest {
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
    public void testContinentEmptyValidation() {
        assertFalse(mapValidation.checkInterlinkedContinent(mapRiskModel));

    }
}
