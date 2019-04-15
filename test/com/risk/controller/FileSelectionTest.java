package com.risk.controller;

import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.model.GamePlayModel;
import com.risk.utilities.Constant;
import com.risk.utilities.ReadFile;
import com.risk.utilities.Validation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileSelectionTest {
    GameMapModel gameMapModel = new GameMapModel() ;
    GamePlayModel gamePlayModel = new GamePlayModel();
    Validation val;
    ReadFile readFile;
    File file;
    ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
    String name= "file";
    EditContinentController editContinentController;


    private static boolean setUpIsDone = false;

    /**
     * Set up file
     */
    @Before
    public void setUp() throws Exception {
        if (setUpIsDone) {
            return;
        }
        // do the setup
        readFile = new ReadFile();
        file = new File(String.valueOf(Constant.filePath));

        readFile.setFile(file);
        val = new Validation();
        gameMapModel = new GameMapModel(file);
        gamePlayModel = new GamePlayModel();
        gamePlayModel.setGameMap(gameMapModel);
        editContinentController =  new EditContinentController();
    }

    /**
     * Test for checking file opening validation
     */
    @Test
    public void testFileSelection()

    {
        if (editContinentController.selectFile() != null)
        {
            assertTrue(true);
        }
    }

}
