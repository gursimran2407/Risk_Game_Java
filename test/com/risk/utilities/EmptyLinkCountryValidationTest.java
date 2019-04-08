package com.risk.utilities;

import com.risk.model.GameMapModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

public class EmptyLinkCountryValidationTest {
    private static final boolean False = false;
    GameMapModel gameMapModel;
    Validation val;
    ReadFile readFile;
    File file;

    private static boolean setUpIsDone = false;

    /**
     * Set up variables
     */
    @Before
    public void setUp()
    {
        if (setUpIsDone) {
            return;
        }
        // do the setup
        readFile = new ReadFile();
        file = new File(Constant.filePath.toUri());
        readFile.setFile(file);
        val = new Validation();
        gameMapModel = new GameMapModel(file);
        setUpIsDone = true;
    }
    /**
     * Test empty link country validation
     */
    @Test
    public void testUnlinkedContinentValidation()
    {
        assertFalse(val.emptyContinentValidation(gameMapModel));
    }

}
