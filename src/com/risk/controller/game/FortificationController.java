package com.risk.controller.game;

import com.risk.Environment;
import com.risk.gameplayrequirements.MapValidation;
import com.risk.model.MapRiskModel;
import com.risk.model.CountryModel;
import com.risk.view.game.IFortificationView;
import com.risk.view.game.MoveData;


/**
 * In FortificationController, also takes care of the movement of
 * data into the model corresponding to the view and the controller and
 * also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Karandeep
 * @version 1.0.0
 */

public class FortificationController {

    private final Environment environment;

    private IFortificationView view;
    private MapRiskModel mapRiskModel = null;

    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
     * @param mapRiskModel
     * @param environment
     */
    public FortificationController(final Environment environment, MapRiskModel mapRiskModel) {
        this.environment = environment;

        this.mapRiskModel = mapRiskModel;

        view = environment.getViewManager().createFortificationView(this.mapRiskModel);
        view.addMoveListener(this::move);
        view.addFromChangeListener(this::fromChanged);
        view.showView();

        this.mapRiskModel.addObserver(this.view);
    }
    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
     * @param moveData
     */
    private void move(final MoveData moveData) {
        // BFS
        MapValidation val = new MapValidation();
        if (val.checkIfValidMove(mapRiskModel, moveData.getFrom(), moveData.getTo())) {
            this.mapRiskModel.setMovingArmies(moveData.getNumOfTroops(), moveData.getFrom(), moveData.getTo());
        }

        int index = this.mapRiskModel.getIndexOfPlayer();
        index++;
        if (this.mapRiskModel.getPlayerModelList().size() > index) {
            this.mapRiskModel.setIndexOfPlayer(index);
            this.mapRiskModel.getPlayerModelList().get(index).callObservers();
            new PlayerGameController(environment, this.mapRiskModel, this.mapRiskModel.getPlayerModelList());
            this.view.hideView();
        } else {
            view.showMessage("Valid", "Bravo! Game is over! No one won!");
            this.view.hideView();
        }
    }
    /**
     * This method is to update the view class by triggering the UI.
     * @param selectedComboBoxIndex
     */
    private void fromChanged(final int selectedComboBoxIndex) {
        this.mapRiskModel.setSelectedComboBoxIndex(selectedComboBoxIndex);
    }
}
