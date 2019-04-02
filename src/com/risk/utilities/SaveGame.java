package com.risk.utilities;

import com.beust.jcommander.ParameterException;
import com.risk.model.GamePlayModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {

    public boolean writeTOJSONFile(GamePlayModel gamePlayModel, String fileName) throws IOException, ParameterException {
        File file = new File(System.getProperty("user.dir") + "//mapfiles//" + fileName + ".json");
        try {

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            JSONObject gameMapModelParam = new JSONObject();
            JSONArray continentParam = new JSONArray();
            if (gamePlayModel.getGameMap().getContinents() != null) {
                for (int i = 0; i < gamePlayModel.getGameMap().getContinents().size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("continentName", gamePlayModel.getGameMap().getContinents().get(i).getContinentName());
                    jsonObj.put("valueControl", gamePlayModel.getGameMap().getContinents().get(i).getValueControl());
                    JSONArray countryList = new JSONArray();
                    for (int j = 0; j < gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
                            .size(); j++) {
                        JSONObject jsonObj1 = new JSONObject();
                        jsonObj1.put("countryName", gamePlayModel.getGameMap().getContinents().get(i)
                                .getCoveredCountries().get(j).getCountryName());
                        jsonObj1.put("xPosition", gamePlayModel.getGameMap().getContinents().get(i)
                                .getCoveredCountries().get(j).getXPosition());
                        jsonObj1.put("yPosition", gamePlayModel.getGameMap().getContinents().get(i)
                                .getCoveredCountries().get(j).getYPosition());
                        jsonObj1.put("continentName", gamePlayModel.getGameMap().getContinents().get(i)
                                .getCoveredCountries().get(j).getcontinentName());
                        jsonObj1.put("armies", gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries()
                                .get(j).getArmies());
                        if (gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries().get(j)
                                .getBackgroundColor() != null) {
                            String colorS = Integer.toString(gamePlayModel.getGameMap().getContinents().get(i)
                                    .getCoveredCountries().get(j).getBackgroundColor().getRGB());
                            jsonObj1.put("backgroundColor", colorS.toString());
                        } else {
                            jsonObj1.put("backgroundColor", null);
                        }
                        if (gamePlayModel.getGameMap().getContinents().get(i).getCoveredCountries().get(j)
                                .getBorderColor() != null) {
                            String colorS = Integer.toString(gamePlayModel.getGameMap().getContinents().get(i)
                                    .getCoveredCountries().get(j).getBorderColor().getRGB());
                            jsonObj1.put("borderColor", colorS.toString());
                        } else {
                            jsonObj1.put("borderColor", null);
                        }
                        jsonObj1.put("rulerName", gamePlayModel.getGameMap().getContinents().get(i)
                                .getCoveredCountries().get(j).getRulerName());
                        countryList.add(jsonObj1);
                    }
                    jsonObj.put("listOfCountries", countryList);
                    continentParam.add(jsonObj);
                }

            }
        }
    }
}