package com.risk.controller.main;

import com.risk.Environment;
import com.risk.controller.map.createcontinent.MapCreateContinentController;
import com.risk.controller.map.edit.MapEditController;
import com.risk.controller.game.StartBrandNewGameController;
import com.risk.view.main.IMainGameView;


/**
 * This is the main starting point of the program containing main function
 *
 * @author gursimransingh
 */

public class MainGameController {

    private final Environment environment;
    private final IMainGameView view;

    /**
     * This Constructor initializes the values and sets the screen to visible.
     */
    public MainGameController(final Environment environment) {
        this.environment = environment;

        view = environment.getViewManager().createMainGameView();
        view.addCreateMapListener(e -> showCreateMapWindow());
        view.addEditMapListener(e -> showEditGameWindow());
        view.addPlayMapListener(e -> showPlayGameWindow());
        view.addExitListener(e -> exitGame());
        view.showView();
    }

    /**
     * show play game window
     */
    private void showPlayGameWindow() {
        new StartBrandNewGameController(environment);
        view.hideView();
    }

    /**
     * show edit game window
     */
    private void showEditGameWindow() {
        new MapEditController(environment);
        view.hideView();
    }

    /**
     * show create map window
     */
    private void showCreateMapWindow() {
        new MapCreateContinentController(environment);
        view.hideView();
    }

    /**
     * exit the game
     */
    private void exitGame() {
        view.hideView();
        environment.exit();
    }
}