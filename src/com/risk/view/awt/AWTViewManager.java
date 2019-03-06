package com.risk.view.awt;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;
import com.risk.view.ViewManager;
import com.risk.view.awt.game.AWTFortificationView;
import com.risk.view.awt.game.AWTReinforcementView;
import com.risk.view.awt.game.AWTStartupView;
import com.risk.view.awt.main.AWTMainGameView;
import com.risk.view.awt.map.countryconnect.AWTMapCountryConnectView;
import com.risk.view.awt.map.createcontinent.AWTMapCreateContinentView;
import com.risk.view.awt.map.createcountry.AWTMapCreateCountryView;
import com.risk.view.awt.map.edit.AWTMapEditView;
import com.risk.view.awt.game.AWTBrandNewGameView;
import com.risk.view.game.IFortificationView;
import com.risk.view.game.IReinforcementView;
import com.risk.view.game.IStartupView;
import com.risk.view.main.IMainGameView;
import com.risk.view.map.countryconnect.IMapCountryConnectView;
import com.risk.view.map.createcontinent.IMapCreateContinentView;
import com.risk.view.map.createcountry.IMapCreateCountryView;
import com.risk.view.map.edit.IMapEditView;
import com.risk.view.game.IBrandNewGameView;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

/**
 * Invokes all view methods
 * @author Karan
 */
public class AWTViewManager implements ViewManager {

    /**
     * Choosing a file to edit
     */
    @Override
    public File openFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File openedFile = jfc.getSelectedFile();
            System.out.println(openedFile.getAbsolutePath());

            return new File(openedFile.getAbsolutePath());
        } else {
            return null;
        }
    }

    @Override
    public IMainGameView createMainGameView() {
        return new AWTMainGameView();
    }

    @Override
    public IMapCreateContinentView createMapCreateContinentView() {
        return new AWTMapCreateContinentView();
    }

    @Override
    public IMapCreateCountryView createMapCreateCountryView(List<ContinentModel> continentModelList) {
        return new AWTMapCreateCountryView(continentModelList);
    }

    @Override
    public IMapEditView createMapEditView(List<ContinentModel> continentModelList) {
        return new AWTMapEditView(continentModelList);
    }

    @Override
    public IBrandNewGameView createBrandNewGameView() {
        return new AWTBrandNewGameView();
    }

    @Override
    public IStartupView createStartupView(MapRiskModel mapRiskModel, PlayerModel playerModel) {
        return new AWTStartupView(mapRiskModel, playerModel);
    }

    @Override
    public IReinforcementView createReinforcementView(MapRiskModel mapRiskModel) {
        return new AWTReinforcementView(mapRiskModel);
    }

    @Override
    public IFortificationView createFortificationView(MapRiskModel mapRiskModel) {
        return new AWTFortificationView(mapRiskModel);
    }

    @Override
    public IMapCountryConnectView createMapCountryConnectView(MapRiskModel mapRiskModel) {
        return new AWTMapCountryConnectView(mapRiskModel);
    }
}
