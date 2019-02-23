package com.risk.controller;

import com.risk.view.BrandNewGameView;
import com.risk.model.PlayerModel;
import com.risk.model.MapRiskModel

import javax.swing.*;
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
    private BrandNewGameView brandNewGameViewobj;
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModelobj = new MapRiskModel();
    private int totalNumberOfPlayersInGame;


    /**
     * This method is used to validate the new player entered that is going to play the game.
     */
    public void validateThePlayer() {
        if (mapRiskModelobj.getCountryModelList().size() > totalNumberOfPlayersInGame) {
            System.out.println("no of players");
            String nameOfPlayer = "";
            for (int i = 0; i < totalNumberOfPlayersInGame; i++) {
                nameOfPlayer = "Player" + (i + 1);
                PlayerModel playerModelobj = new PlayerModel(nameOfPlayer, 0, 0, "");
                listOfPlayers.add(playerModelobj);
            }
            new StartUpController(listOfPlayers, mapRiskModelobj); // we are yet to create the StartUpController. It is still in conception.
            this.brandNewGameViewobj.dispose();
        } else {
            JOptionPane.showMessageDialog(brandNewGameViewobj,
                    "Mismatch between the number of maps and the number of Players. Please select matching values or number of maps at least equal to or more than the number of players.",
                    "The selected Map has been Loaded", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(BrandNewGameView.browseMapButton)) {
            int value = BrandNewGameView.chooseMap.showOpenDialog(brandNewGameViewobj);
            if (value == JFileChooser.APPROVE_OPTION) {
                try {
                    File mapFile = brandNewGameViewobj.chooseMap.getSelectedFile();
                    mapRiskModelobj = new MapRiskModel(mapFile);
                    JOptionPane.showMessageDialog(brandNewGameViewobj, "File Loaded Successfully! Click Next to Play!", "Map Loaded", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (actionEvent.getSource().equals(brandNewGameViewobj.nextButton)) {
            totalNumberOfPlayersInGame = (int) brandNewGameViewobj.numOfPlayers.getSelectedItem(); // number of players
            // here are coming from the BrandMewGameView
            validateThePlayer(); // this is to be created yet.

        } else if (actionEvent.getSource().equals(BrandNewGameView.cancelButton)) {
            new MainGame();
            this.totalNumberOfPlayersInGame.dispose();
        }

    }
}
