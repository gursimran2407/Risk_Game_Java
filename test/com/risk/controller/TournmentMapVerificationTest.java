package com.risk.controller;

import com.risk.model.GameMapModel;
import com.risk.utilities.Constant;
import com.risk.utilities.ReadFile;
import com.risk.utilities.Validation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author gursimransingh
 */
public class TournmentMapVerificationTest {
    private static final boolean False = false;
    GameMapModel gameMapModel;
    Validation val;
    ReadFile readFile;
    File file;
    TournmentDetailController tournmentDetailController;

    private static boolean setUpIsDone = false;

    /**
     * Set up variables
     */
    @Before
    public void setUp() {
        if (setUpIsDone) {
            return;
        }
        this.readFile = new ReadFile();
        file = new File(Constant.filePath.toUri());
        this.readFile.setFile(file);
        val = new Validation();
        gameMapModel = new GameMapModel(file);
        setUpIsDone = true;

        tournmentDetailController = new TournmentDetailController();
    }

    /**
     * Test read File get continent
     */
    @Test
    public void testReadFileGetContinent() {
        Assert.assertEquals(true, tournmentDetailController.mapVerification(file,0));
    }

}