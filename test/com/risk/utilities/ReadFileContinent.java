package com.risk.utilities;

import app.model.GameMapModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * ReadFileContinent
 * @author team 35
 *
 */
public class ReadFileContinent {

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
		this.readFile = new ReadFile();
		file = new File(Constant.FILE_LOCATION);
		this.readFile.setFile(file);
		val = new Validation();
		gameMapModel = new GameMapModel(file);
		setUpIsDone = true;
	}
	
	/**
	 * Test read File get continent
	 */
	@Test
	public void testReadFileGetContinent() {
		Assert.assertEquals(true, readFile.validateReadContinent(gameMapModel.getContinents(), readFile.getMapContinentDetails()));
	}

}
