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

    private BrandNewGameView theView;
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();
    private MapRiskModel mapRiskModel = new MapRiskModel();
    private int noOfPlayers;

    /**
     * Constructor initializes values and sets the screen too visible
     */
    public StartBrandNewGameController() {
        this.theView = new BrandNewGameView();
        this.theView.setActionListener(this);
        this.theView.setVisible(true);

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(theView.browseMapButton)) {
            int value = theView.chooseMap.showOpenDialog(theView);
            if(value == JFileChooser.APPROVE_OPTION){
                try {
                    File mapFile = theView.chooseMap.getSelectedFile();
                    mapRiskModel = new MapRiskModel(mapFile);
                    JOptionPane.showMessageDialog(theView, "File Loaded Successfully! Click Next to Play!","Map Loaded",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(actionEvent.getSource().equals(theView.nextButton)) {
            noOfPlayers = (int) theView.numOfPlayers.getSelectedItem();
            playerValidation();

        }else if(actionEvent.getSource().equals(theView.cancelButton)) {
            new MainGame();
            this.theView.dispose();
        }

    }

    /**
     *  Check for the player validation
     */
    public void playerValidation() {
        if ( mapRiskModel.getCountryModelList().size() > noOfPlayers) {
            System.out.println("no of players");
            String PlayerName = "";
            for (int i=0; i<noOfPlayers; i++) {
                PlayerName = "Player"+ (i+1);
                PlayerModel pm = new PlayerModel(PlayerName, 0, 0,"");
                listOfPlayers.add(pm);
            }
            new StartupController(listOfPlayers, mapRiskModel);
            this.theView.dispose();
        } else {
            JOptionPane.showMessageDialog(theView,
                    "Number of cuntry in the Map is less than Number of Players. Select map or player Again!",
                    "Map Loaded", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}
