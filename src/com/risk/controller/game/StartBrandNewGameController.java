package com.risk.controller.game;

import com.risk.Environment;
import com.risk.controller.main.MainGameController;
import com.risk.model.PlayerModel;
import com.risk.model.MapRiskModel;
import com.risk.view.game.IBrandNewGameView;

import java.util.ArrayList;
import java.util.List;

/**
 * similar to all other controllers in the project, the StartBrandNewGameController also takes care of the movement of
 * data into the model corresponding to the view and the controller and also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Karan
 */
public class StartBrandNewGameController {

    private final Environment environment;

    private IBrandNewGameView view;
    private MapRiskModel mapRiskModel = new MapRiskModel();

    /**
     * Constructor initializes values and sets the screen too visible
     */
    public StartBrandNewGameController(final Environment environment) {
        this.environment = environment;

        this.view = environment.getViewManager().createBrandNewGameView();
        this.view.addBrowseMapListener(e -> openMap());
        this.view.addPlayListener(this::playerValidation);
        this.view.addCancelListener(e -> cancel());
        this.view.showView();
    }

    private void openMap() {
        try {
            mapRiskModel = new MapRiskModel(environment.getViewManager().openFile());
            view.showMessage("Map Loaded", "File Loaded Successfully! Click Next to Play!");
        } catch (Exception e) {
            e.printStackTrace();
            view.showMessage(e.getMessage());
        }
    }

    /**
     *  Check for the player validation
     */
    private void playerValidation(int noOfPlayers) {
        if (mapRiskModel.getCountryModelList().size() > noOfPlayers) {
            List<PlayerModel> listOfPlayers = new ArrayList<PlayerModel>();

            System.out.println("no of players");
            String PlayerName = "";
            for (int i=0; i<noOfPlayers; i++) {
                PlayerName = "Player"+ (i + 1);
                PlayerModel pm = new PlayerModel(PlayerName, 0, 0,"");
                listOfPlayers.add(pm);
            }

            new StartupController(environment, listOfPlayers, mapRiskModel);
            this.view.hideView();
        } else {
            view.showMessage("Map Loaded", "Number of country in the Map is less than Number of Players. Select map or player Again!");
        }
    }

    private void cancel() {
        new MainGameController(environment);
        this.view.hideView();
    }
}
