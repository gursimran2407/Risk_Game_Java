package com.risk.utilities;

import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WinnerCheckTest {
    GameMapModel gameMapModel = new GameMapModel() ;
    GamePlayModel gamePlayModel = new GamePlayModel();
    Validation val;
    ReadFile readFile;
    File file;
    ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();

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

        countryList.add(gameMapModel.getCountries().get(0));
        countryList.add(gameMapModel.getCountries().get(1));

        countryList.get(0).setArmies(2);

        PlayerModel pm = new PlayerModel("X", "Human", 0, Color.WHITE, 0, countryList, null);
        ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();

        pmList.add(pm);

        gamePlayModel.setPlayers(pmList);
        setUpIsDone = true;
    }

    @Test
    public void teststartup()
    {
        //StartUpController statup = new StartUpController(gamePlayModel);
        assertEquals("draw",val.determineWinner(gamePlayModel));
    }
}
