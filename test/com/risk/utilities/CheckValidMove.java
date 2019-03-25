package com.risk.utilities;

import app.model.GameMapModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

/**
 * CheckValidMove
 * @author team 35
 *
 */
public class CheckValidMove {

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
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		// do the setup
		readFile = new ReadFile();
		file = new File(Constant.FILE_LOCATION);
		readFile.setFile(file);
		val = new Validation();
		gameMapModel = new GameMapModel(file);
		setUpIsDone = true;
	}

	/**
	 * Test check valid move
	 */
	@Test
	public void testUnlinkedContinentVAlidation() {
		assertFalse(val.checkIfValidMove(gameMapModel, gameMapModel.getCountries().get(0),
				gameMapModel.getCountries().get(1)));
	}
}
