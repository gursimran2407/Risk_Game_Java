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
     * This method shows play game window in the UI
     */
    private void showPlayGameWindow() {
        new StartBrandNewGameController(environment);
        view.hideView();
    }

    /**
     * This method shows edit game window in the UI
     */
    private void showEditGameWindow() {
        new MapEditController(environment);
        view.hideView();
    }

    /**
     * This method shows create map window in the UI
     */
    private void showCreateMapWindow() {
        new MapCreateContinentController(environment);
        view.hideView();
    }

    /**
     * This method exit the game completely from the window and console.
     */
    private void exitGame() {
        view.hideView();
        environment.exit();
    }
}