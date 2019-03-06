package com.risk.model;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

/**
 * This test case is to test invalid map
 *
 * @author Namita Faujdar
 */
public class MapUnlinkingValidation {

    private static boolean setUpIsDone = false;
    MapRiskModel mapRiskModel;
    MapValidation mapValidation;
    MapRead mapRead;
    File file;

    /**
     * set up variables
     */
    @Before
    public void setUp()
    {
        if (setUpIsDone)
        {
            return;
        }
        /** for setup these parameters are needed */
        mapRead = new MapRead();
        file = new File(Constants.filePath.toUri());
        System.out.println(file.toString() + "FILEREAD");
        mapRead.setReadFile(file);
        mapValidation = new MapValidation();
        mapRiskModel = new MapRiskModel(file);
        setUpIsDone = true;

    }

    /**Test for un-linking continent validation */
    @Test
    public void testMapUnlinkingValidation()
    {
        assertFalse(mapValidation.unlinkedContinentValidation(mapRiskModel));
    }
}
