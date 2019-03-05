package com.risk.controller.game;

import com.risk.Environment;
import com.risk.gameplayrequirements.MapValidation;
import com.risk.model.MapRiskModel;
import com.risk.model.CountryModel;
import com.risk.view.game.IFortificationView;
import com.risk.view.game.MoveData;


/**
 * In FortificationController, the data flow into model object and updates the
 * view whenever data changes.
 *
 * @author Karandeep
 */

public class FortificationController {

    private final Environment environment;

    private IFortificationView view;
    private MapRiskModel mapRiskModel = null;

    /**
     * Constructor initializes values and sets the screen too visible
     *
     * @param mapRiskModel
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

    private void fromChanged(final int selectedComboBoxIndex) {
        this.mapRiskModel.setSelectedComboBoxIndex(selectedComboBoxIndex);
    }
}
