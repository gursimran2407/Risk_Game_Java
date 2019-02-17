package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

/**
 * This class will read the map
 */

public class ReadMapFile {
    private static File FILE;

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

    /**
     * This method reads the map file and returns country name,
     * position, continent that it belongs to, and countries list that it is
     * connected to.
     *
     * @return the array list of countries
     */
    public ArrayList<CountryModel> getMapCountryDetails() {
        ArrayList<CountryModel> listOfCountryModel;
        HashMap<String, CountryModel> listOfCountries = new HashMap<String, CountryModel>();
        File file = getMapFile();
        BufferedReader bfr;
        try {
            bfr = new BufferedReader(new FileReader(file));
            while (bfr.readLine() != null) {
                String fileData = bfr.readLine();

                if (fileData.contains("[Territories]")) {

                    while (bfr.readLine() != null) {
                        String territories = bfr.readLine();
                        territories.trim();

                        if (!"".equals(territories)) {

                            int indexOfCountryName = territories.indexOf(',');

                            String countryName = territories.substring(0, indexOfCountryName).trim();
                            CountryModel cm = listOfCountries.get(countryName);
                            if (cm == null) {
                                cm = new CountryModel();
                                cm.setCountryName(countryName);
                                listOfCountries.put(cm.getCountryName(), cm);
                            }

                            /*int indexOfXPos = 0;
                            try {
                                indexOfXPos = territories.indexOf(',', (indexOfCountryName + 1));
                                String xPosition = territories.substring((indexOfCountryName + 1), indexOfXPos);
                                cm.setXPosition(Integer.parseInt(xPosition.trim()));
                                System.out.println("xposition: " + xPosition);
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null,
                                        "Map is missing the x position for " + cm.getCountryName());

                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null,
                                        "Map is missing the x position for " + cm.getCountryName());

                            }*/

                            /*int indexOfYPos = territories.indexOf(',', (indexOfXPos + 1));
                            try {
                                String yPosition = territories.substring((indexOfXPos + 1), indexOfYPos);
                                cm.setYPosition(Integer.parseInt(yPosition.trim()));
                                System.out.println("yPosition" + yPosition);
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null,
                                        "Map is missing the y position for " + cm.getCountryName());

                            } catch (NumberFormatException n) {
                                JOptionPane.showMessageDialog(null,
                                        "Map is missing the y position for " + cm.getCountryName());

                            }*/

                            int indexOfContinent = territories.indexOf(',', (0 + 1));
                            String continent = territories.substring((0 + 1), indexOfContinent).trim();
                            cm.setContinentName(continent.trim());
                            System.out.println("Continent: " + continent);

                            String neighbouringCountries = territories.substring((indexOfContinent + 1)).trim();
                            List<String> listOfNeighbouringCountries = Arrays
                                    .asList(neighbouringCountries.split("\\s*,\\s*"));
                            CountryModel newNeighbour;

                            ArrayList<CountryModel> linkedCountriesList = new ArrayList<CountryModel>();

                            for (int i = 0; i < listOfNeighbouringCountries.size(); i++) {
                                if (listOfCountries.containsKey(listOfNeighbouringCountries.get(i).trim())) {
                                    newNeighbour = listOfCountries.get(listOfNeighbouringCountries.get(i).trim());
                                } else {
                                    newNeighbour = new CountryModel();
                                    newNeighbour.setCountryName(listOfNeighbouringCountries.get(i).trim());
                                }
                                listOfCountries.put(listOfNeighbouringCountries.get(i).trim(), newNeighbour);
                                linkedCountriesList.add(newNeighbour);
                            }
                            cm.setLinkCountryModel(linkedCountriesList);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            //JOptionPane.showMessageDialog(null, "Map is missing one of the country name");
            //how to get dialog box in javafx
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Map is missing one of the country name");
            alert.showAndWait();
        } catch (NumberFormatException n) {
            //JOptionPane.showMessageDialog(null, "Map is missing one of  the country name");

        }

        Collection<CountryModel> c = (Collection<CountryModel>) listOfCountries.values();
        listOfCountryModel = new ArrayList<CountryModel>(c);
        return listOfCountryModel;
    }

    public File getMapFile() {
        return FILE;
    }

    public void setMapFile(File file) {
        FILE = file;
    }
}
