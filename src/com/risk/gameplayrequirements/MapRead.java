package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;

import java.io.*;
import java.util.*;

/**
 * @author Namita Faujdar 
 */
public class MapRead {

    private static File READ_FILE;

    public ArrayList<ContinentModel> getMapContinentDetails() {

        File readFile = getReadFile();
        ArrayList<ContinentModel> continentsList = null;
        BufferedReader bfr;
        try {
            bfr = new BufferedReader(new FileReader(readFile));

            continentsList = new ArrayList<>();

            while (bfr.readLine() != null) {
                String bfr1 = bfr.readLine();
                if (bfr1.contains("[Continents]")) {
                    String bfr2 = bfr.readLine();
                    bfr2.trim();
                    while (!"".equals(bfr2)) {
                        int positionEqual = bfr2.indexOf('=');
                        String bfr3 = bfr2.substring(0, positionEqual);
                        System.out.println("Continents: " + bfr3);

                        String bfr4 = bfr2.substring(positionEqual + 1);
                        int result = Integer.parseInt(bfr4);
                        System.out.println("Value: " + bfr4);

                        ContinentModel tempMyContinents = new ContinentModel(bfr3, result);
                        continentsList.add(tempMyContinents);
                        bfr2 = bfr.readLine();
                        bfr2.trim();
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return continentsList;
    }

    public ArrayList<CountryModel> getMapCountryDetails() {
        File readFile = getReadFile();
        BufferedReader bfr;
        ArrayList<CountryModel> countryModelList;
        HashMap<String, CountryModel> countriesList = new HashMap<>();
        try {
            bfr = new BufferedReader(new FileReader(readFile));

        while (bfr.readLine() != null) {
            String fileData = bfr.readLine();

            if (fileData.contains("[Territories]")) {

                while (bfr.readLine() != null) {
                    String territories = bfr.readLine();
                    territories.trim();

                    if (!"".equals(territories)) {

                        int indexOfCountryName = territories.indexOf(',');
                        String countryName = territories.substring(0, indexOfCountryName).trim();
                        CountryModel cm = countriesList.get(countryName);
                        if (cm == null) {
                            cm = new CountryModel();
                            cm.setCountryName(countryName);
                            countriesList.put(cm.getCountryName(), cm);
                        }

                        int indexOfXPos = territories.indexOf(',', (indexOfCountryName + 1));
                        String xPosition = territories.substring((indexOfCountryName + 1), indexOfXPos);
                        cm.setXPosition(Integer.parseInt(xPosition.trim()));
                        System.out.println("xposition: " + xPosition);

                        int indexOfYPos = territories.indexOf(',', (indexOfXPos + 1));
                        String yPosition = territories.substring((indexOfXPos + 1), indexOfYPos);
                        cm.setYPosition(Integer.parseInt(yPosition.trim()));
                        System.out.println("yPosition" + yPosition);

                        int indexOfContinent = territories.indexOf(',', (indexOfYPos + 1));
                        String continent = territories.substring((indexOfYPos + 1), indexOfContinent).trim();
                        cm.setContinentName(continent.trim());
                        System.out.println("Continent: " + continent);

                        String neighbouringCountries = territories.substring((indexOfContinent + 1)).trim();
                        List<String> listOfNeighbouringCountries = Arrays
                                .asList(neighbouringCountries.split("\\s*,\\s*"));
                        CountryModel newNeighbour;

                        ArrayList<CountryModel> linkedCountriesList = new ArrayList<CountryModel>();

                        for (int i = 0; i < listOfNeighbouringCountries.size(); i++) {
                            if (countriesList.containsKey(listOfNeighbouringCountries.get(i).trim())) {
                                newNeighbour = countriesList.get(listOfNeighbouringCountries.get(i).trim());
                            } else {
                                newNeighbour = new CountryModel();
                                newNeighbour.setCountryName(listOfNeighbouringCountries.get(i).trim());
                            }
                            countriesList.put(listOfNeighbouringCountries.get(i).trim(), newNeighbour);
                            linkedCountriesList.add(newNeighbour);
                        }
                        cm.setLinkCountryModel(linkedCountriesList);
                    }
                }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collection<CountryModel> c = countriesList.values();
        countryModelList = new ArrayList<>(c);
        return countryModelList;
    }

    public static File getReadFile() {
        return READ_FILE;
    }

    public static void setReadFile(File readFile) {
        READ_FILE = readFile;
    }
}
