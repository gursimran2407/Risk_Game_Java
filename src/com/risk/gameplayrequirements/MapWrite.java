package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class has checks for validation, writing to map and creating a map file.
 *
 * @author Karandeep
 */
public class MapWrite {
        File file;    //Creating new File

    /**
     * Get the continents
     * @param continentsModel
     * @return content
     */
    public static String getContinet(ContinentModel continentsModel) {
        String content = null;
        if (!"".equals(continentsModel.getContinentName())) {
            content = continentsModel.getContinentName() + "=" + continentsModel.getControlValue();
        }
        return content;
    }

    /**
     * Get the country
     * @param countryModel
     * @return content
     */
    public static String getCountry(CountryModel countryModel) {
        String content = null;
        if (!"".equals(countryModel.getCountryName())) {
            content = countryModel.getCountryName() + "," + countryModel.getXPosition() + ","
                    + countryModel.getYPosition() + "," + countryModel.getContinentName() + ",";
            String country = "";
            for (int i = 0; i < countryModel.getConnectedCountryList().size(); i++) {
                if (i == countryModel.getConnectedCountryList().size()) {
                    country = country + countryModel.getConnectedCountryList().get(i).getCountryName();
                } else {
                    country = country + countryModel.getConnectedCountryList().get(i).getCountryName() + ",";
                }
            }
            content = content + country;
        }
        return content;
    }
    /**
     * Create a new File
     * @param fileName
     * @return file
     */
        public File createNewFile(String fileName) {
            try {
                this.file = new File(System.getProperty("user.dir") + "\\maps\\" + fileName);
                boolean fvar = file.createNewFile();
                if (fvar) {
                    System.out.println("File has been created successfully");
                    return file;
                } else {
                    System.out.println("File already present at the specified location");
                    return null;
                }
            } catch (IOException e) {
                System.out.println("Exception Occurred:");
                e.printStackTrace();
            }
            return this.file;
        }

    /**
     * This method Writes Map to file
     * @param fileName
     * @param mapRiskModel
     * @throws Exception
     */

    public void writeMapToFile(String fileName, MapRiskModel mapRiskModel) throws Exception {
        List<ContinentModel> listOfContinents = mapRiskModel.getContinentModelList();
        List<CountryModel> listOfCountries = mapRiskModel.getCountryModelList();
        this.file = new File(System.getProperty("user.dir") + "//map//" + fileName + ".map");
            System.out.println(this.file);
            try {
                // Create new file
                String content = null;
                File file = this.file;

                // If file doesn't exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                // Write in file
                // Map content
                content = "[Map]";
                bw.write(content);
                bw.newLine();
                content = "author=";
                bw.write(content);
                bw.newLine();
                content = "image=new.bmp";
                bw.write(content);
                bw.newLine();
                content = "\n";
                bw.write(content);
                bw.newLine();

                // Continent content
                content = "[Continents]";
                bw.write(content);
                bw.newLine();

                for (int i = 0; i < listOfContinents.size(); i++) {
                    content = getContinet(listOfContinents.get(i));
                    bw.write(content);
                    bw.newLine();
                }

                content = "\n";
                bw.write(content);
                bw.newLine();

                // Country content
                content = "[Territories]";
                bw.write(content);
                bw.newLine();

                for (int i = 0; i < listOfCountries.size(); i++) {
                    content = getCountry(listOfCountries.get(i));
                    bw.write(content);
                    bw.newLine();
                }

                // Close connection
                bw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }


