package com.risk.model;
import org.junit.Test;

import com.risk.controller.ReinforcementController;
import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import org.junit.Test;

/**
 * Test for number of armies for reinforcement
 *
 * @author Karandeep
 */
public class NoOfArmyReinforcementTest {

    private static final boolean False = false;
    MapRiskModel mapRiskModel;
    ReinforcementController rC;
    MapRead mapRead;
    File file;
    int reinforceArmies;
    private ArrayList<CountryModel> listOfCountrys = new ArrayList<CountryModel>();

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
        mapRead = new MapRead();
        file = new File(Constants.filePath.toUri());
        System.out.println(file.toString() + "FILEREAD");
        mapRead.setReadFile(file);
        mapRiskModel = new MapRiskModel(file);
        rC = new ReinforcementController(mapRiskModel);
        for (int i = 0; i < this.mapRiskModel.getCountryModelList().size(); i++) {
            if (this.mapRiskModel.getCountryModelList().get(i).getCountryOwner().equals(this.mapRiskModel.getPlayerTurn())) {
                this.listOfCountrys.add(this.mapRiskModel.getCountryModelList().get(i));
            }
        }
        if (listOfCountrys.size() > 3) {
            reinforceArmies = 3 + Math.round(listOfCountrys.size() / 3);
        } else {
            reinforceArmies = 3;
        }
        if (reinforceArmies > 12) {
            reinforceArmies = 12;
        }
        setUpIsDone = true;
    }
    /**
     * Test unlink continent validation
     */
    @Test
    public void testUnlinkedContinentValidation()
    {
        assertEquals(reinforceArmies,rC.calculateArmies());
    }

}