package com.risk.model;

import app.utilities.Constant;
import app.utilities.ReadFile;
import app.utilities.Validation;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class RemoveCardTest {

	GameMapModel gameMapModel;
	GamePlayModel gamePlayModel;
	Validation val;
	ReadFile readFile;
	File file;
	ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
	ArrayList<CountryModel> cardList = new ArrayList<CountryModel>();
	
	PlayerModel pm = new PlayerModel("X", 0, Color.WHITE, 0, countryList, cardList);
	CardModel card ;

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
		file = new File(Constant.FILE_LOCATION);
		readFile.setFile(file);
		gameMapModel = new GameMapModel(file);
		gamePlayModel = new GamePlayModel();
		gamePlayModel.setGameMap(gameMapModel);

		countryList.add(gameMapModel.getCountries().get(0));
		countryList.add(gameMapModel.getCountries().get(1));

		countryList.get(0).setArmies(2);
		gamePlayModel.getCardFromJSON();
		card = gamePlayModel.getCards().get(0);
		ArrayList<PlayerModel> pmList = new ArrayList<PlayerModel>();
		pm.addCard(card);
		pmList.add(pm);
		
		

		gamePlayModel.setPlayers(pmList);
		setUpIsDone = true;
	}

	/**
	 * Test single strike
	 */
	@Test
	public void test() {
		assertTrue( pm.removeCard(card));
	}
}
