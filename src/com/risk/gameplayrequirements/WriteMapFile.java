package com.risk.gameplayrequirements;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * This class writes to Map file
 * @author Namita Faujdar
 */

public class WriteMapFile {

    private File writeFile;
    
    /**
     *  This method writes map to a file
     * @param fileName name of the file
     * @param gameMapModel model game map
     *
     */
 public void mapToFile(String fileName, MapRiskModel gameMapModel){
     
     List<ContinentModel> continentsList = gameMapModel.getContinentModelModList();
     List<CountryModel> countriesList = gameMapModel.getCountryModelList();
     //writeFile = new File(System.getProperty("user.dir") + "//mapfiles//" + fileName + ".map");
     System.out.println(writeFile);
     try {
         // Create new file
         String content;
         File file = writeFile;

         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bufferedWriter = new BufferedWriter(fw);

         // Write in file
         // Map content
         content = "[Map]";
         bufferedWriter.write(content);
         bufferedWriter.newLine();
         content = "author=";
         bufferedWriter.write(content);
         bufferedWriter.newLine();
         content = "image=new.bmp";
         bufferedWriter.write(content);
         bufferedWriter.newLine();
         content = "\n";
         bufferedWriter.write(content);
         bufferedWriter.newLine();

         // Continent content
         content = "[Continents]";
         bufferedWriter.write(content);
         bufferedWriter.newLine();

         for (int i = 0; i < continentsList.size(); i++) {
             content = getContinentName(continentsList.get(i));
             bufferedWriter.write(content);
             bufferedWriter.newLine();
         }

         content = "\n";
         bufferedWriter.write(content);
         bufferedWriter.newLine();

         // Country content
         content = "[Territories]";
         bufferedWriter.write(content);
         bufferedWriter.newLine();

         for (int i = 0; i < countriesList.size(); i++) {
             content = getCountryName(countriesList.get(i));
             bufferedWriter.write(content);
             bufferedWriter.newLine();
         }

         // Close connection
         bufferedWriter.close();
     } catch (Exception e) {
         e.printStackTrace();
     }

    }

    /**
     * Get the continent name with its control value
     * @param continentModel model continent
     * @return continent name
     */
    private static String getContinentName(ContinentModel continentModel) {
        String content = null;
        if (!"".equals(continentModel.getContinentName())) {
            content = continentModel.getContinentName() + "=" + continentModel.getControlValue();
        }
        return content;
    }

    /**
     * Get the country name
     * @param countryModel model country
     * @return country name
     */
    private static String getCountryName(CountryModel countryModel) {
        //Commented out this code as we don't know whether will use x or y coordinates in javafx or not.
        // returning null as the method is of type string

        /*String content = null;
        if (!"".equals(countryModel.getCountryName())) {
            content = countryModel.getCountryName() + "," + countryModel.getXPosition() + ","
                    + countryModel.getYPosition() + "," + countryModel.getContinentName() + ",";
            String country = "";
            for (int i = 0; i < countryModel.getLinkedCountries().size(); i++) {
                if (i == countryModel.getLinkedCountries().size()) {
                    country = country + countryModel.getLinkedCountries().get(i).getCountryName();
                } else {
                    country = country + countryModel.getLinkedCountries().get(i).getCountryName() + ",";
                }
            }
            content = content + country;
        }
        return content;*/
        return "";
    }
}
