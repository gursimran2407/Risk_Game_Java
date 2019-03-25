package com.risk.utilities;

import com.risk.gameplayrequirements.Constants;
import com.risk.gameplayrequirements.MapRead;
import com.risk.gameplayrequirements.MapValidation;
import com.risk.gameplayrequirements.MessageHelper;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * @author gursimransingh
 */
public class EndGameTest {

    private static boolean setup = false;
    MapRiskModel mapRiskModel;
    GamePlayModel gamePlayModel;
    MapValidation mapValidation;
    MapRead mapRead;
    File file;
    ArrayList<CountryModel> countryModels = new ArrayList<>();

    @Before
    public void setUp() {
        if (setup) {
            return;
        }
        mapRead = new MapRead();
        file = new File(Constants.filePath.toUri());
        mapRead.setReadFile(file);
        mapValidation = new MapValidation();
        mapRiskModel = new MapRiskModel(file);
        gamePlayModel = new GamePlayModel();
        gamePlayModel.setMapRiskModel(mapRiskModel);

        countryModels.add(mapRiskModel.getCountryModelList().get(0));
        countryModels.add(mapRiskModel.getCountryModelList().get(1));

        countryModels.get(0).setNumberofArmies(2);

        PlayerModel pm = new PlayerModel("X", 0, 0, Color.WHITE, countryModels, null);
        ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();

        pmList.add(pm);

        gamePlayModel.setPlayers(pmList);
        setup = true;

    }

    @Test
    public void test() {

        boolean endOfGameFlag = this.mapValidation.endOfGame(gamePlayModel);
        if (endOfGameFlag) {
            MessageHelper msg = new MessageHelper("It is an end of game");
        } else {
            MessageHelper msg = new MessageHelper("It is an not an end of game");
        }
    }

}
