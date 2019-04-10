package com.risk.utilities;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Field;

import com.risk.model.CardModel;
import com.risk.model.ContinentsModel;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;

public class SaveGame {


    public boolean writeTOJSONFile(GamePlayModel gamePlayModel, String fileName) throws IOException, ParseException {
        File file = new File(System.getProperty("user.dir") + "//mapfiles//" + fileName + ".json");
        try {

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            JSONObject gameMapModelParam = new JSONObject();

            // continent
            JSONArray continentParam = new JSONArray();
            if (gamePlayModel.getGameMap().getContinents() != null) {
                for (int i = 0; i < gamePlayModel.getGameMap().getContinents().size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("continentName", gamePlayModel.getGameMap().getContinents().get(i).getContinentName());
                    jsonObj.put("valueControl", gamePlayModel.getGameMap().getContinents().get(i).getValueControl());

                    // list of country
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

            // Country
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

            // list of players
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

                // list of owned cards
                JSONArray cardList = new JSONArray();
                if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards() != null) {
                    for (int j = 0; j < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); j++) {
                        JSONObject jsonObj1 = new JSONObject();
                        jsonObj1.put("cardId",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(j).getCardId());
                        jsonObj1.put("cardValue",
                                gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(j).getCardValue());
                        cardList.add(jsonObj1);
                    }
                }
                jsonObj.put("ownedCards", cardList);

                gameMapModelParam.put("playerTurn", jsonObj);
            } else {
                gameMapModelParam.put("playerTurn", null);
            }
            gameMapModelParam.put("playerIndex", gamePlayModel.getGameMap().getPlayerIndex());
            gameMapModelParam.put("leftModelIndex", gamePlayModel.getGameMap().getLeftModelIndex());
            gameMapModelParam.put("rightModelIndex", gamePlayModel.getGameMap().getRightModelIndex());
            gameMapModelParam.put("listOfPlayers", playerList);

            // cards
            JSONArray cardParam = new JSONArray();
            if (gamePlayModel.getCards() != null) {
                for (int i = 0; i < gamePlayModel.getCards().size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("cardId", gamePlayModel.getCards().get(i).getCardId());
                    jsonObj.put("cardValue", gamePlayModel.getCards().get(i).getCardValue());
                    cardParam.add(jsonObj);
                }
            }

            // Players
            JSONArray playerParam = new JSONArray();
            if (gamePlayModel.getPlayers() != null) {
                for (int i = 0; i < gamePlayModel.getPlayers().size(); i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("namePlayer", gamePlayModel.getPlayers().get(i).getNamePlayer());
                    jsonObj.put("typePlayer", gamePlayModel.getPlayers().get(i).getTypePlayer());
                    jsonObj.put("myTroop", gamePlayModel.getPlayers().get(i).getmyTroop());
                    if (gamePlayModel.getPlayers().get(i).getColor() != null) {
                        String colorS = Integer.toString(gamePlayModel.getPlayers().get(i).getColor().getRGB());
                        jsonObj.put("color", colorS.toString());
                    } else {
                        jsonObj.put("color", null);
                    }
                    jsonObj.put("remainTroop", gamePlayModel.getPlayers().get(i).getremainTroop());
                    jsonObj.put("showReinforcementCard", gamePlayModel.getPlayers().get(i).getShowReinforcementCard());

                    // list of country
                    JSONArray countryOwned = new JSONArray();
                    if (gamePlayModel.getPlayers().get(i).getOwnedCountries() != null) {
                        for (int j = 0; j < gamePlayModel.getPlayers().get(i).getOwnedCountries().size(); j++) {
                            JSONObject jsonObj1 = new JSONObject();
                            jsonObj1.put("countryName",
                                    gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getCountryName());
                            jsonObj1.put("xPosition",
                                    gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getXPosition());
                            jsonObj1.put("yPosition",
                                    gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getYPosition());
                            jsonObj1.put("continentName",
                                    gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getcontinentName());
                            jsonObj1.put("armies",
                                    gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getArmies());
                            if (gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j)
                                    .getBackgroundColor() != null) {
                                String colorS = Integer.toString(gamePlayModel.getPlayers().get(i).getOwnedCountries()
                                        .get(j).getBackgroundColor().getRGB());
                                jsonObj1.put("backgroundColor", colorS.toString());
                            } else {
                                jsonObj1.put("backgroundColor", null);
                            }
                            if (gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getBorderColor() != null) {
                                String colorS = Integer.toString(gamePlayModel.getPlayers().get(i).getOwnedCountries()
                                        .get(j).getBorderColor().getRGB());
                                jsonObj1.put("borderColor", colorS.toString());
                            } else {
                                jsonObj1.put("borderColor", null);
                            }
                            jsonObj1.put("rulerName",
                                    gamePlayModel.getPlayers().get(i).getOwnedCountries().get(j).getRulerName());
                            countryOwned.add(jsonObj1);
                        }
                    }
                    jsonObj.put("ownedCountries", countryOwned);

                    // list of owned cards
                    JSONArray cardList = new JSONArray();
                    if (gamePlayModel.getPlayers().get(i).getOwnedCards() != null) {
                        for (int j = 0; j < gamePlayModel.getPlayers().get(i).getOwnedCards().size(); j++) {
                            JSONObject jsonObj1 = new JSONObject();
                            jsonObj1.put("cardId",
                                    gamePlayModel.getPlayers().get(i).getOwnedCards().get(j).getCardId());
                            jsonObj1.put("cardValue",
                                    gamePlayModel.getPlayers().get(i).getOwnedCards().get(j).getCardValue());
                            cardList.add(jsonObj1);
                        }
                    }
                    jsonObj.put("ownedCards", cardList);

                    playerParam.add(jsonObj);
                }
            }

            JSONObject mainobj = new JSONObject();
            mainobj.put("selectedComboBoxIndex", gamePlayModel.getSelectedComboBoxIndex());
            mainobj.put("selectedAttackComboBoxIndex", gamePlayModel.getSelectedAttackComboBoxIndex());
            mainobj.put("selectedDefendComboBoxIndex", gamePlayModel.getSelectedDefendComboBoxIndex());
            mainobj.put("countryOwned", gamePlayModel.getCountryOwned());
            mainobj.put("armyToMoveFlag", gamePlayModel.getArmyToMoveFlag());
            mainobj.put("cardToBeAssigned", gamePlayModel.getCardToBeAssigned());
            mainobj.put("consoleText", gamePlayModel.getConsoleText().toString());
            if (gamePlayModel.getDefeatedCountry() != null) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("countryName", gamePlayModel.getDefeatedCountry().getCountryName());
                jsonObj.put("xPosition", gamePlayModel.getDefeatedCountry().getXPosition());
                jsonObj.put("yPosition", gamePlayModel.getDefeatedCountry().getYPosition());
                jsonObj.put("continentName", gamePlayModel.getDefeatedCountry().getcontinentName());
                jsonObj.put("armies", gamePlayModel.getDefeatedCountry().getArmies());
                if (gamePlayModel.getDefeatedCountry().getBackgroundColor() != null) {
                    String colorS = Integer.toString(gamePlayModel.getDefeatedCountry().getBackgroundColor().getRGB());
                    jsonObj.put("backgroundColor", colorS.toString());
                } else {
                    jsonObj.put("backgroundColor", null);
                }
                if (gamePlayModel.getDefeatedCountry().getBorderColor() != null) {
                    String colorS = Integer.toString(gamePlayModel.getDefeatedCountry().getBorderColor().getRGB());
                    jsonObj.put("borderColor", colorS.toString());
                } else {
                    jsonObj.put("borderColor", null);
                }
                jsonObj.put("rulerName", gamePlayModel.getDefeatedCountry().getRulerName());

                mainobj.put("defeatedCountry", jsonObj);
            } else {
                mainobj.put("defeatedCountry", null);
            }

            mainobj.put("deck", cardParam);
            mainobj.put("players", playerParam);
            mainobj.put("gameMapModel", gameMapModelParam);
            mainobj.put("gamePhase", gamePlayModel.getGamePhase());

            StringWriter out = new StringWriter();
            mainobj.writeJSONString(out);
            String jsonText = out.toString();
            bw.write(jsonText);

            bw.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public GamePlayModel readFROMJSONFile(File file) throws IOException, ParseException {
        GamePlayModel gamePlayModel = new GamePlayModel();
        JSONParser parser = new JSONParser();
        Long lvalue;
        int value;
        Boolean flag;
        String content;
        Color color;

        try {
            // Read JSON file
            Object inputModel = parser.parse(new FileReader(file));
            JSONObject playModel = (JSONObject) inputModel;

            JSONObject getMapModel = (JSONObject) playModel.get("gameMapModel");

            JSONArray playerList = (JSONArray) getMapModel.get("listOfPlayers");
            for (Object o : playerList) {
                PlayerModel playerModel = new PlayerModel();
                JSONObject player = (JSONObject) o;

                if (player.get("namePlayer") != null) {
                    content = (String) player.get("namePlayer");
                    playerModel.setNamePlayer(content);
                } else {
                    playerModel.setNamePlayer("");
                }

                if (player.get("typePlayer") != null) {
                    content = (String) player.get("typePlayer");
                    playerModel.setTypePlayer(content);
                } else {
                    playerModel.setTypePlayer("");
                }

                lvalue = (Long) player.get("myTroop");
                value = lvalue.intValue();
                playerModel.setmyTroop(value);

                content = (String) player.get("color");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    playerModel.setColor(color);
                }

                lvalue = (Long) player.get("remainTroop");
                value = lvalue.intValue();
                playerModel.setremainTroop(value);

                flag = (Boolean) player.get("showReinforcementCard");
                playerModel.setShowReinforcementCard(flag);

                // list of country
                JSONArray OwnedCountry = (JSONArray) player.get("ownedCountries");
                for (Object j : OwnedCountry) {
                    CountryModel countryModel = new CountryModel();
                    JSONObject country = (JSONObject) j;

                    if (country.get("countryName") != null) {
                        content = (String) country.get("countryName");
                        countryModel.setCountryName(content);
                    } else {
                        countryModel.setCountryName("");
                    }

                    lvalue = (Long) country.get("xPosition");
                    value = lvalue.intValue();
                    countryModel.setXPosition(value);

                    lvalue = (Long) country.get("yPosition");
                    value = lvalue.intValue();
                    countryModel.setYPosition(value);

                    if (country.get("continentName") != null) {
                        content = (String) country.get("continentName");
                        countryModel.setContinentName(content);
                    } else {
                        countryModel.setContinentName("");
                    }

                    lvalue = (Long) country.get("armies");
                    value = lvalue.intValue();
                    countryModel.setArmies(value);

                    content = (String) country.get("backgroundColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countryModel.setBackgroundColor(color);
                    }

                    content = (String) country.get("borderColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countryModel.setBorderColor(color);
                    }

                    if (country.get("rulerName") != null) {
                        content = (String) country.get("rulerName");
                        countryModel.setRulerName(content);
                    } else {
                        countryModel.setRulerName("");
                    }
                    playerModel.getOwnedCountries().add(countryModel);
                }

                // list of owned cards
                JSONArray OwnedCards = (JSONArray) player.get("ownedCards");
                for (Object j : OwnedCards) {
                    CardModel cardModel = new CardModel();
                    JSONObject card = (JSONObject) j;
                    lvalue = (Long) card.get("cardId");
                    value = lvalue.intValue();
                    cardModel.setCardId(value);

                    lvalue = (Long) card.get("cardValue");
                    value = lvalue.intValue();
                    cardModel.setCardValue(value);
                    playerModel.getOwnedCards().add(cardModel);
                }
                gamePlayModel.getGameMap().getListOfPlayers().add(playerModel);
            }

            JSONObject playerTurn = (JSONObject) getMapModel.get("playerTurn");
            PlayerModel playerModel1 = new PlayerModel();

            if (!playerTurn.get("namePlayer").equals(null)) {
                content = (String) playerTurn.get("namePlayer");
                playerModel1.setNamePlayer(content);
            } else {
                playerModel1.setNamePlayer("");
            }

            if (playerTurn.get("typePlayer") != null) {
                content = (String) playerTurn.get("typePlayer");
                playerModel1.setTypePlayer(content);
            } else {
                playerModel1.setTypePlayer("");
            }

            lvalue = (Long) playerTurn.get("myTroop");
            value = lvalue.intValue();
            playerModel1.setmyTroop(value);

            content = (String) playerTurn.get("color");
            if (content != null ) {
                color = new Color(Integer.parseInt(content));
                playerModel1.setColor(color);
            }

            lvalue = (Long) playerTurn.get("remainTroop");
            value = lvalue.intValue();
            playerModel1.setremainTroop(value);

            flag = (Boolean) playerTurn.get("showReinforcementCard");
            playerModel1.setShowReinforcementCard(flag);

            // list of country
            JSONArray OwnedCountry = (JSONArray) playerTurn.get("ownedCountries");
            for (Object j : OwnedCountry) {
                CountryModel countryModel = new CountryModel();
                JSONObject country = (JSONObject) j;

                if (country.get("countryName") != null) {
                    content = (String) country.get("countryName");
                    countryModel.setCountryName(content);
                } else {
                    countryModel.setCountryName("");
                }

                lvalue = (Long) country.get("xPosition");
                value = lvalue.intValue();
                countryModel.setXPosition(value);

                lvalue = (Long) country.get("yPosition");
                value = lvalue.intValue();
                countryModel.setYPosition(value);

                if (country.get("continentName") != null) {
                    content = (String) country.get("continentName");
                    countryModel.setContinentName(content);
                } else {
                    countryModel.setContinentName("");
                }

                lvalue = (Long) country.get("armies");
                value = lvalue.intValue();
                countryModel.setArmies(value);

                content = (String) country.get("backgroundColor");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    countryModel.setBackgroundColor(color);
                }

                content = (String) country.get("borderColor");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    countryModel.setBorderColor(color);
                }

                if (country.get("rulerName") != null) {
                    content = (String) country.get("rulerName");
                    countryModel.setRulerName(content);
                } else {
                    countryModel.setRulerName("");
                }
                playerModel1.getOwnedCountries().add(countryModel);
            }

            // list of owned cards
            JSONArray OwnedCards = (JSONArray) playerTurn.get("ownedCards");
            for (Object j : OwnedCards) {
                CardModel cardModel = new CardModel();
                JSONObject card = (JSONObject) j;
                lvalue = (Long) card.get("cardId");
                value = lvalue.intValue();
                cardModel.setCardId(value);

                lvalue = (Long) card.get("cardValue");
                value = lvalue.intValue();
                cardModel.setCardValue(value);
                playerModel1.getOwnedCards().add(cardModel);
            }
            gamePlayModel.getGameMap().setPlayerTurn(playerModel1);

            lvalue = (Long) getMapModel.get("playerIndex");
            value = lvalue.intValue();
            gamePlayModel.getGameMap().setPlayerIndex(value);

            lvalue = (Long) getMapModel.get("leftModelIndex");
            value = lvalue.intValue();
            gamePlayModel.getGameMap().setLeftModelIndex(value);

            lvalue = (Long) getMapModel.get("rightModelIndex");
            value = lvalue.intValue();
            gamePlayModel.getGameMap().setRightModelIndex(value);

            JSONArray continentJSON = (JSONArray) getMapModel.get("continentList");
            int index1 = 0;
            for (Object o : continentJSON) {
                ContinentsModel continentModel = new ContinentsModel();
                JSONObject continent = (JSONObject) o;

                if (continent.get("continentName") != null) {
                    content = (String) continent.get("continentName");
                    continentModel.setContinentName(content);
                } else {
                    continentModel.setContinentName("");
                }

                lvalue = (Long) continent.get("valueControl");
                value = lvalue.intValue();
                continentModel.setValueControl(value);

                // list of country
                JSONArray linkedCountry = (JSONArray) continent.get("listOfCountries");
                for (Object j : linkedCountry) {
                    CountryModel countryModel1 = new CountryModel();
                    JSONObject country = (JSONObject) j;

                    if (country.get("countryName") != null) {
                        content = (String) country.get("countryName");
                        countryModel1.setCountryName(content);
                    } else {
                        countryModel1.setCountryName("");
                    }

                    lvalue = (Long) country.get("xPosition");
                    value = lvalue.intValue();
                    countryModel1.setXPosition(value);

                    lvalue = (Long) country.get("yPosition");
                    value = lvalue.intValue();
                    countryModel1.setYPosition(value);

                    if (country.get("continentName") != null) {
                        content = (String) country.get("continentName");
                        countryModel1.setContinentName(content);
                    } else {
                        countryModel1.setContinentName("");
                    }

                    lvalue = (Long) country.get("armies");
                    value = lvalue.intValue();
                    countryModel1.setArmies(value);

                    content = (String) country.get("backgroundColor");
                    if (content != null) {
                        color = new Color(Integer.parseInt(content));
                        countryModel1.setBackgroundColor(color);
                    }

                    content = (String) country.get("borderColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countryModel1.setBorderColor(color);
                    }

                    if (country.get("rulerName") != null) {
                        content = (String) country.get("rulerName");
                        countryModel1.setRulerName(content);
                    } else {
                        countryModel1.setRulerName("");
                    }
                    continentModel.getCoveredCountries().add(countryModel1);
                }
                gamePlayModel.getGameMap().getContinents().add(continentModel);
            }

            JSONArray countryJSON = (JSONArray) getMapModel.get("countryList");
            for (Object o : countryJSON) {
                CountryModel countryModel = new CountryModel();
                JSONObject country = (JSONObject) o;

                if (country.get("countryName") != null) {
                    content = (String) country.get("countryName");
                    countryModel.setCountryName(content);
                } else {
                    countryModel.setCountryName("");
                }

                lvalue = (Long) country.get("xPosition");
                value = lvalue.intValue();
                countryModel.setXPosition(value);

                lvalue = (Long) country.get("yPosition");
                value = lvalue.intValue();
                countryModel.setYPosition(value);

                if (country.get("continentName") != null) {
                    content = (String) country.get("continentName");
                    countryModel.setContinentName(content);
                } else {
                    countryModel.setContinentName("");
                }

                lvalue = (Long) country.get("armies");
                value = lvalue.intValue();
                countryModel.setArmies(value);

                content = (String) country.get("backgroundColor");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    countryModel.setBackgroundColor(color);
                }

                content = (String) country.get("borderColor");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    countryModel.setBorderColor(color);
                }

                if (country.get("rulerName") != null) {
                    content = (String) country.get("rulerName");
                    countryModel.setRulerName(content);
                } else {
                    countryModel.setRulerName("");
                }
                JSONArray links = (JSONArray) country.get("linkedCountries");
                for (Object j : links) {
                    CountryModel countries = new CountryModel();
                    JSONObject countryListed = (JSONObject) j;

                    if (countryListed.get("countryName") != null) {
                        content = (String) countryListed.get("countryName");
                        countries.setCountryName(content);
                    } else {
                        countries.setCountryName("");
                    }

                    lvalue = (Long) countryListed.get("xPosition");
                    value = lvalue.intValue();
                    countries.setXPosition(value);

                    lvalue = (Long) countryListed.get("yPosition");
                    value = lvalue.intValue();
                    countries.setYPosition(value);

                    if (countryListed.get("continentName") != null) {
                        content = (String) countryListed.get("continentName");
                        countries.setContinentName(content);
                    } else {
                        countries.setContinentName("");
                    }

                    lvalue = (Long) countryListed.get("armies");
                    value = lvalue.intValue();
                    countries.setArmies(value);

                    content = (String) countryListed.get("backgroundColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countries.setBackgroundColor(color);
                    }

                    content = (String) countryListed.get("borderColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countries.setBorderColor(color);
                    }

                    if (countryListed.get("rulerName") != null) {
                        content = (String) countryListed.get("rulerName");
                        countries.setRulerName(content);
                    } else {
                        countries.setRulerName("");
                    }
                    countryModel.getLinkedCountries().add(countries);
                }
                gamePlayModel.getGameMap().getCountries().add(countryModel);
            }

            JSONArray playersJSON = (JSONArray) playModel.get("players");
            for (Object o : playersJSON) {
                PlayerModel playerModel2 = new PlayerModel();
                JSONObject player = (JSONObject) o;

                if (player.get("namePlayer") != null) {
                    content = (String) player.get("namePlayer");
                    playerModel2.setNamePlayer(content);
                } else {
                    playerModel2.setNamePlayer("");
                }

                if (player.get("typePlayer") != null) {
                    content = (String) player.get("typePlayer");
                    playerModel2.setTypePlayer(content);
                } else {
                    playerModel2.setTypePlayer("");
                }

                lvalue = (Long) player.get("myTroop");
                value = lvalue.intValue();
                playerModel2.setmyTroop(value);

                content = (String) player.get("color");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    playerModel2.setColor(color);
                }

                lvalue = (Long) player.get("remainTroop");
                value = lvalue.intValue();
                playerModel2.setremainTroop(value);

                flag = (Boolean) player.get("showReinforcementCard");
                playerModel2.setShowReinforcementCard(flag);

                // list of country
                JSONArray OwnedCountries = (JSONArray) player.get("ownedCountries");
                for (Object j : OwnedCountries) {
                    CountryModel countryModel2 = new CountryModel();
                    JSONObject country = (JSONObject) j;

                    if (country.get("countryName") != null) {
                        content = (String) country.get("countryName");
                        countryModel2.setCountryName(content);
                    } else {
                        countryModel2.setCountryName("");
                    }

                    lvalue = (Long) country.get("xPosition");
                    value = lvalue.intValue();
                    countryModel2.setXPosition(value);

                    lvalue = (Long) country.get("yPosition");
                    value = lvalue.intValue();
                    countryModel2.setYPosition(value);

                    if (country.get("continentName") != null) {
                        content = (String) country.get("continentName");
                        countryModel2.setContinentName(content);
                    } else {
                        countryModel2.setContinentName("");
                    }

                    lvalue = (Long) country.get("armies");
                    value = lvalue.intValue();
                    countryModel2.setArmies(value);

                    content = (String) country.get("backgroundColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countryModel2.setBackgroundColor(color);
                    }

                    content = (String) country.get("borderColor");
                    if (content != null ) {
                        color = new Color(Integer.parseInt(content));
                        countryModel2.setBorderColor(color);
                    }

                    if (country.get("rulerName") != null) {
                        content = (String) country.get("rulerName");
                        countryModel2.setRulerName(content);
                    } else {
                        countryModel2.setRulerName("");
                    }
                    playerModel2.getOwnedCountries().add(countryModel2);
                }

                // list of owned cards
                JSONArray OwnedCard = (JSONArray) player.get("ownedCards");
                for (Object j : OwnedCard) {
                    CardModel cardModel = new CardModel();
                    JSONObject card = (JSONObject) j;
                    lvalue = (Long) card.get("cardId");
                    value = lvalue.intValue();
                    cardModel.setCardId(value);

                    lvalue = (Long) card.get("cardValue");
                    value = lvalue.intValue();
                    cardModel.setCardValue(value);
                    playerModel2.getOwnedCards().add(cardModel);
                }
                ;
                gamePlayModel.getPlayers().add(playerModel2);
            }

            JSONArray deckJSON = (JSONArray) playModel.get("deck");
            for (Object o : deckJSON) {
                CardModel cardModel = new CardModel();
                JSONObject card = (JSONObject) o;
                lvalue = (Long) card.get("cardId");
                value = lvalue.intValue();
                cardModel.setCardId(value);

                lvalue = (Long) card.get("cardValue");
                value = lvalue.intValue();
                cardModel.setCardValue(value);
                gamePlayModel.getCards().add(cardModel);
            }

            lvalue = (Long) playModel.get("selectedComboBoxIndex");
            value = lvalue.intValue();
            gamePlayModel.setSelectedComboBoxIndexRead(value);

            lvalue = (Long) playModel.get("selectedAttackComboBoxIndex");
            value = lvalue.intValue();
            gamePlayModel.setSelectedAttackComboBoxIndexRead(value);

            lvalue = (Long) playModel.get("selectedDefendComboBoxIndex");
            value = lvalue.intValue();
            gamePlayModel.setSelectedDefendComboBoxIndexRead(value);

            flag = (Boolean) playModel.get("countryOwned");
            gamePlayModel.setCountryOwned(flag);

            flag = (Boolean) playModel.get("armyToMoveFlag");
            gamePlayModel.setArmyToMoveTextRead(flag);

            flag = (Boolean) playModel.get("cardToBeAssigned");
            gamePlayModel.setCardToBeAssigned(flag);

            StringBuilder sb = new StringBuilder();
            if (playModel.get("consoleText") != null) {
                content = (String) playModel.get("consoleText");
                sb.append(content);
                gamePlayModel.setConsoleText(sb);
            } else {
                gamePlayModel.setConsoleText(sb);
            }

            if (playModel.get("gamePhase") != null) {
                content = (String) playModel.get("gamePhase");
                gamePlayModel.setGamePhase(content);
            } else {
                gamePlayModel.setGamePhase(null);
            }

            if (playModel.get("defeatedCountry") != null) {
                JSONObject jsonObj = (JSONObject) playModel.get("defeatedCountry");

                if (jsonObj.get("countryName") != null) {
                    content = (String) jsonObj.get("countryName");
                    gamePlayModel.getDefeatedCountry().setCountryName(content);
                } else {
                    gamePlayModel.getDefeatedCountry().setCountryName("");
                }

                lvalue = (Long) jsonObj.get("xPosition");
                value = lvalue.intValue();
                gamePlayModel.getDefeatedCountry().setXPosition(value);

                lvalue = (Long) jsonObj.get("yPosition");
                value = lvalue.intValue();
                gamePlayModel.getDefeatedCountry().setYPosition(value);

                if (jsonObj.get("continentName") != null) {
                    content = (String) jsonObj.get("continentName");
                    gamePlayModel.getDefeatedCountry().setContinentName(content);
                } else {
                    gamePlayModel.getDefeatedCountry().setContinentName("");
                }

                lvalue = (Long) jsonObj.get("armies");
                value = lvalue.intValue();
                gamePlayModel.getDefeatedCountry().setArmies(value);

                content = (String) jsonObj.get("backgroundColor");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    gamePlayModel.getDefeatedCountry().setBackgroundColor(color);
                }

                content = (String) jsonObj.get("borderColor");
                if (content != null ) {
                    color = new Color(Integer.parseInt(content));
                    gamePlayModel.getDefeatedCountry().setBorderColor(color);
                }

                if (jsonObj.get("rulerName") != null) {
                    content = (String) jsonObj.get("rulerName");
                    gamePlayModel.getDefeatedCountry().setRulerName(content);
                } else {
                    gamePlayModel.getDefeatedCountry().setRulerName("");
                }

            } else {
                gamePlayModel.setDefeatedCountry(null);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gamePlayModel;

    }

    public static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            // get color by hex or octal value
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            // if we can't decode lets try to get it by name
            try {
                // try to get a color by name using reflection
                final Field f = Color.class.getField(value);

                return (Color) f.get(null);
            } catch (Exception ce) {
                // if we can't get any color return black
                return Color.black;
            }
        }
    }
}
