package com.risk.controller;

import com.risk.model.*;
import com.risk.view.BrandNewGameView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * similar to all other controllers in the project, the StartBrandNewGameController also takes care of the movement of
 * data into the model corresponding to the view and the controller and also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Karan
 */
public class StartBrandNewGameController implements ActionListener {

    private BrandNewGameView brandNewGameView;
    private ArrayList<PlayerModel> playersList = new ArrayList<>();
    private MapRiskModel mapRiskModel = new MapRiskModel();
    private int noOfPlayers;
    private String players = "";
    private GamePlayModel gamePlayModel = new GamePlayModel();

    /**
     * Constructor initializes values and sets the screen too visible
     */
    public StartBrandNewGameController() {
        brandNewGameView = new BrandNewGameView();
        brandNewGameView.setActionListener(this);
        brandNewGameView.setVisible(true);

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(brandNewGameView.browse)) {
            int value = brandNewGameView.chooseMap.showOpenDialog(brandNewGameView);
            if(value == JFileChooser.APPROVE_OPTION){
                try {
                    File mapFile = brandNewGameView.chooseMap.getSelectedFile();
                    mapRiskModel = new MapRiskModel(mapFile);
                    JOptionPane.showMessageDialog(brandNewGameView, "File Loaded Successfully! Click Next to Play!","Map Loaded",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(actionEvent.getSource().equals(brandNewGameView.nextButton)) {
            noOfPlayers = (int) brandNewGameView.numOfPlayers.getSelectedItem();

            if ( mapRiskModel.getCountryModelList().size() > noOfPlayers) {
                System.out.println("no of players" + noOfPlayers);
                for (int i = 0; i < noOfPlayers; i++) {
                    if (i == 0) {
                        players = brandNewGameView.player1.getText();
                    } else if (i == 1) {
                        players = brandNewGameView.player2.getText();
                    } else if (i == 2) {
                        players = brandNewGameView.player3.getText();
                    } else if (i == 3) {
                        players = brandNewGameView.player4.getText();
                    } else if (i == 4) {
                        players = brandNewGameView.player5.getText();
                    }
                    System.out.println("players " + players);
                    if (players == null || "".equals(players.trim())) {
                        players = "Player " + (i + 1);
                    }
                    PlayerModel pm = new PlayerModel(players, 0, 0, Color.WHITE, new ArrayList<CountryModel>(),
                            new ArrayList<CardModel>());
                    playersList.add(pm);
                }
                gamePlayModel.setMapRiskModel(mapRiskModel);
                gamePlayModel.setPlayers(playersList);
                try {
                    gamePlayModel.setDeck(gamePlayModel.getCardFromJSON());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new StartupController(gamePlayModel);
                this.brandNewGameView.dispose();
            } else {
                JOptionPane.showMessageDialog(brandNewGameView,
                        "Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
                        "Map Loaded", JOptionPane.INFORMATION_MESSAGE);
            }

        }else if(actionEvent.getSource().equals(brandNewGameView.cancelButton)) {
            new MainGame();
            brandNewGameView.dispose();
        }

    }

}
