package com.risk.utilities;

import com.risk.model.GameMapModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

/**
 * CheckValidMove
 * @author shriyans
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
	 * Setting up variables
	 */
	@Before
	public void setUp() {
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
	 * Test to check valid move
	 */
	@Test
	public void testUnlinkedContinentVAlidation() {
		assertFalse(val.checkIfValidMove(gameMapModel, gameMapModel.getCountries().get(0),
				gameMapModel.getCountries().get(1)));
	}
}
