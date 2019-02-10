package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;

import java.io.*;
import java.util.*;

/**
 * This class will read the map
 */

public class ReadMapFile {
    private static  File FILE;

    /**
     * This method reads the map file and returns continent name and
     * control value.
     *
     * @return the array list of continents
     */
    public ArrayList<ContinentModel> getMapContinentDetails() {

        ArrayList<ContinentModel> continentsList = new ArrayList<>();
        File file = getMapFile();
        BufferedReader bfr;
        try {
            bfr = new BufferedReader(new FileReader(file));
            while (bfr.readLine() != null) {
                String reader = bfr.readLine();
                if (reader.contains("[Continents]")) {
                    String reader2 = bfr.readLine();
                    reader2.trim();
                    while (!"".equals(reader2)) {
                        int positionEqual = reader2.indexOf('=');
                        String reader3 = reader2.substring(0, positionEqual);
                        System.out.println("Continents: " + reader3);

                        String reader4 = reader2.substring(positionEqual + 1);
                        int result = Integer.parseInt(reader4);
                        System.out.println("Value: " + reader4);

                        ContinentModel tempMyContinents = new ContinentModel(reader3, result);
                        continentsList.add(tempMyContinents);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return continentsList;
    }

    public File getMapFile() {
        return FILE;
    }

    public void setMapFile(File file) {
        FILE = file;
    }
}
