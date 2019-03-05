package com.risk.view;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.game.IFortificationView;
import com.risk.view.game.IReinforcementView;
import com.risk.view.game.IStartupView;
import com.risk.view.map.countryconnect.IMapCountryConnectView;
import com.risk.view.map.createcontinent.IMapCreateContinentView;
import com.risk.view.main.IMainGameView;
import com.risk.view.map.createcountry.IMapCreateCountryView;
import com.risk.view.map.edit.IMapEditView;
import com.risk.view.game.IBrandNewGameView;

import java.io.File;
import java.util.List;

/**
 * this interface has all view interfaces
 *
 * @author Namita Faujdar
 */

public interface ViewManager {

    File openFile();

    IMainGameView createMainGameView();

    IMapCreateContinentView createMapCreateContinentView();

    IMapCreateCountryView createMapCreateCountryView(final List<ContinentModel> continentModelList);

    IMapEditView createMapEditView(final List<ContinentModel> continentModelList);

    IBrandNewGameView createBrandNewGameView();

    IStartupView createStartupView(MapRiskModel mapRiskModel, PlayerModel playerModel);

    IReinforcementView createReinforcementView(MapRiskModel mapRiskModel);

    IFortificationView createFortificationView(MapRiskModel mapRiskModel);

    IMapCountryConnectView createMapCountryConnectView(MapRiskModel mapRiskModel);
}
