package com.risk.controller;

import com.risk.view.BrandNewGameView;
import com.risk.model.PlayerModel;
import com.risk.model.MapRiskModel;

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

    private BrandNewGameView brandNewGameView;
    private ArrayList<PlayerModel> playersList = new ArrayList<>();
    private MapRiskModel mapRiskModel = new MapRiskModel();
    private int noOfPlayers;

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
                System.out.println("no of players");
                String PlayerName ;
                for (int i=0; i<noOfPlayers; i++) {
                    PlayerName = "Player"+ (i+1);
                    PlayerModel pm = new PlayerModel(PlayerName, 0, 0,"");
                    playersList.add(pm);
                }
                new StartupController(playersList, mapRiskModel);
                brandNewGameView.dispose();
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
