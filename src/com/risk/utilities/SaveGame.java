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
            JSONArray countryParam = new JSONArray();
            if (gamePlayModel.getGameMap().getCountries() != null) {
                for (int i = 0; i < gamePlayModel.getGameMap().getCountries().size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("countryName", gamePlayModel.getGameMap().getCountries().get(i).getCountryName());
                    jsonObj.put("xPosition", gamePlayModel.getGameMap().getCountries().get(i).getXPosition());
                    jsonObj.put("yPosition", gamePlayModel.getGameMap().getCountries().get(i).getYPosition());
                    jsonObj.put("continentName", gamePlayModel.getGameMap().getCountries().get(i).getcontinentName());
                    jsonObj.put("armies", gamePlayModel.getGameMap().getCountries().get(i).getArmies());
                    if (gamePlayModel.getGameMap().getCountries().get(i).getBackgroundColor() != null) {
                        String colorS = Integer.toString(
                                gamePlayModel.getGameMap().getCountries().get(i).getBackgroundColor().getRGB());
                        jsonObj.put("backgroundColor", colorS.toString());
                    } else {
                        jsonObj.put("backgroundColor", null);
                    }
                    if (gamePlayModel.getGameMap().getCountries().get(i).getBorderColor() != null) {
                        String colorS = Integer
                                .toString(gamePlayModel.getGameMap().getCountries().get(i).getBorderColor().getRGB());
                        jsonObj.put("borderColor", colorS.toString());
                    } else {
                        jsonObj.put("borderColor", null);
                    }
                    jsonObj.put("rulerName", gamePlayModel.getGameMap().getCountries().get(i).getRulerName());
                    JSONArray countryLinked = new JSONArray();
                    for (int j = 0; j < gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().size(); j++) {
                        JSONObject jsonObj1 = new JSONObject();
                        jsonObj1.put("countryName", gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getCountryName());
                        jsonObj1.put("xPosition", gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getXPosition());
                        jsonObj1.put("yPosition", gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getYPosition());
                        jsonObj1.put("continentName", gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getcontinentName());
                        jsonObj1.put("armies", gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getArmies());
                        if (gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getBackgroundColor() != null) {
                            String colorS = Integer.toString(
                                    gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getBackgroundColor().getRGB());
                            jsonObj1.put("backgroundColor", colorS.toString());
                        } else {
                            jsonObj1.put("backgroundColor", null);
                        }
                        if (gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getBorderColor() != null) {
                            String colorS = Integer
                                    .toString(gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getBorderColor().getRGB());
                            jsonObj1.put("borderColor", colorS.toString());
                        } else {
                            jsonObj1.put("borderColor", null);
                        }
                        jsonObj1.put("rulerName", gamePlayModel.getGameMap().getCountries().get(i).getLinkedCountries().get(j).getRulerName());
                        countryLinked.add(jsonObj1);
                    }
                    jsonObj.put("linkedCountries", countryLinked);
                    countryParam.add(jsonObj);
                }
            }
        JSONArray playerList = new JSONArray();
        if (gamePlayModel.getGameMap().getListOfPlayers() != null) {

            for (int i = 0; i < gamePlayModel.getGameMap().getListOfPlayers().size(); i++) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("namePlayer", gamePlayModel.getGameMap().getListOfPlayers().get(i).getNamePlayer());
                jsonObj.put("typePlayer", gamePlayModel.getGameMap().getListOfPlayers().get(i).getTypePlayer());
                jsonObj.put("myTroop", gamePlayModel.getGameMap().getListOfPlayers().get(i).getmyTroop());
                if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getColor() != null) {
                    String colorS = Integer
                            .toString(gamePlayModel.getGameMap().getListOfPlayers().get(i).getColor().getRGB());
                    jsonObj.put("color", colorS.toString());
                } else {
                    jsonObj.put("color", null);
                }
                jsonObj.put("remainTroop", gamePlayModel.getGameMap().getListOfPlayers().get(i).getremainTroop());
                jsonObj.put("showReinforcementCard",
                        gamePlayModel.getGameMap().getListOfPlayers().get(i).getShowReinforcementCard());

                // list of country
                JSONArray countryOwn = new JSONArray();
                if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries() != null) {
                    for (int j = 0; j < gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries()
                            .size(); j++) {
                        JSONObject jsonObj1 = new JSONObject();
                        jsonObj1.put("countryName", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCountries().get(j).getCountryName());
                        jsonObj1.put("xPosition", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCountries().get(j).getXPosition());
                        jsonObj1.put("yPosition", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCountries().get(j).getYPosition());
                        jsonObj1.put("continentName", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCountries().get(j).getcontinentName());
                        jsonObj1.put("armies", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCountries().get(j).getArmies());
                        if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries().get(j)
                                .getBackgroundColor() != null) {
                            String colorS = Integer.toString(gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                    .getOwnedCountries().get(j).getBackgroundColor().getRGB());
                            jsonObj1.put("backgroundColor", colorS.toString());
                        } else {
                            jsonObj1.put("backgroundColor", null);
                        }
                        if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCountries().get(j)
                                .getBorderColor() != null) {
                            String colorS = Integer.toString(gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                    .getOwnedCountries().get(j).getBorderColor().getRGB());
                            jsonObj1.put("borderColor", colorS.toString());
                        } else {
                            jsonObj1.put("borderColor", null);
                        }
                        jsonObj1.put("rulerName", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCountries().get(j).getRulerName());
                        countryOwn.add(jsonObj1);
                    }
                }
                jsonObj.put("ownedCountries", countryOwn);

                // list of owned cards
                JSONArray cardowned = new JSONArray();
                if (gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards() != null) {
                    for (int j = 0; j < gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards()
                            .size(); j++) {
                        JSONObject jsonObj1 = new JSONObject();
                        jsonObj1.put("cardId", gamePlayModel.getGameMap().getListOfPlayers().get(i).getOwnedCards()
                                .get(j).getCardId());
                        jsonObj1.put("cardValue", gamePlayModel.getGameMap().getListOfPlayers().get(i)
                                .getOwnedCards().get(j).getCardValue());
                        cardowned.add(jsonObj1);
                    }
                }
                jsonObj.put("ownedCards", cardowned);

                playerList.add(jsonObj);
            }
        }
            gameMapModelParam.put("continentList", continentParam);
            gameMapModelParam.put("countryList", countryParam);
            gameMapModelParam.put("playerTurn", gamePlayModel.getGameMap().getPlayerTurn());
            if (gamePlayModel.getGameMap().getPlayerTurn() != null) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("namePlayer", gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
                jsonObj.put("typePlayer", gamePlayModel.getGameMap().getPlayerTurn().getTypePlayer());
                jsonObj.put("myTroop", gamePlayModel.getGameMap().getPlayerTurn().getmyTroop());
                if (gamePlayModel.getGameMap().getPlayerTurn().getColor() != null) {
                    String colorS = Integer.toString(gamePlayModel.getGameMap().getPlayerTurn().getColor().getRGB());
                    jsonObj.put("color", colorS.toString());
                } else {
                    jsonObj.put("color", null);
                }
                jsonObj.put("remainTroop", gamePlayModel.getGameMap().getPlayerTurn().getremainTroop());
                jsonObj.put("showReinforcementCard",
                        gamePlayModel.getGameMap().getPlayerTurn().getShowReinforcementCard());

                // list of country
                JSONArray countryOwn = new JSONArray();
                if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries() != null) {
                    for (int j = 0; j < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().size(); j++) {
                        JSONObject jsonObj1 = new JSONObject();
                        jsonObj1.put("countryName",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getCountryName());
                        jsonObj1.put("xPosition",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getXPosition());
                        jsonObj1.put("yPosition",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getYPosition());
                        jsonObj1.put("continentName", gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries()
                                .get(j).getcontinentName());
                        jsonObj1.put("armies",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getArmies());
                        if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j)
                                .getBackgroundColor() != null) {
                            String colorS = Integer.toString(gamePlayModel.getGameMap().getPlayerTurn()
                                    .getOwnedCountries().get(j).getBackgroundColor().getRGB());
                            jsonObj1.put("backgroundColor", colorS.toString());
                        } else {
                            jsonObj1.put("backgroundColor", null);
                        }
                        if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j)
                                .getBorderColor() != null) {
                            String colorS = Integer.toString(gamePlayModel.getGameMap().getPlayerTurn()
                                    .getOwnedCountries().get(j).getBorderColor().getRGB());
                            jsonObj1.put("borderColor", colorS.toString());
                        } else {
                            jsonObj1.put("borderColor", null);
                        }
                        jsonObj1.put("rulerName",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCountries().get(j).getRulerName());
                        countryOwn.add(jsonObj1);
                    }
                }
                jsonObj.put("ownedCountries", countryOwn);


            }

    private JSONArray newJSONArray() {
    }
}