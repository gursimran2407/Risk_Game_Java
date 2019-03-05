package com.risk;

import com.risk.view.ViewManager;
import com.risk.view.game.*;
import com.risk.view.main.IMainGameView;
import com.risk.view.map.countryconnect.IMapCountryConnectView;
import com.risk.view.map.createcontinent.IMapCreateContinentView;
import com.risk.view.map.createcountry.IMapCreateCountryView;
import com.risk.view.map.edit.IMapEditView;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestsViewManager implements ViewManager {
    @Override
    public File openFile() {
        return null;
    }

    @Override
    public IMainGameView createMainGameView() {
        return new IMainGameView() {
            @Override
            public void addCreateMapListener(ActionListener listener) {

            }

            @Override
            public void addEditMapListener(ActionListener listener) {

            }

            @Override
            public void addPlayMapListener(ActionListener listener) {

            }

            @Override
            public void addExitListener(ActionListener listener) {

            }
        };
    }

    @Override
    public IMapCreateContinentView createMapCreateContinentView() {
        return new IMapCreateContinentView() {
            @Override
            public void addContinentListener(BiConsumer<String, String> listener) {

            }

            @Override
            public void addShowMapCreateCountryListener(ActionListener listener) {

            }
        };
    }

    @Override
    public IMapCreateCountryView createMapCreateCountryView(List<ContinentModel> continentModelList) {
        return new IMapCreateCountryView() {
            @Override
            public void addCountryListener(BiConsumer<String, ContinentModel> listener) {

            }

            @Override
            public void addShowMapCountryConnectListener(ActionListener listener) {

            }
        };
    }

    @Override
    public IMapEditView createMapEditView(List<ContinentModel> continentModelList) {
        return new IMapEditView() {
            @Override
            public void addContinentListener(BiConsumer<ContinentModel, String> listener) {

            }

            @Override
            public void addSaveListener(Consumer<String> listener) {

            }
        };
    }

    @Override
    public IBrandNewGameView createBrandNewGameView() {
        return new IBrandNewGameView() {
            @Override
            public void addBrowseMapListener(ActionListener listener) {

            }

            @Override
            public void addPlayListener(Consumer<Integer> listener) {

            }

            @Override
            public void addCancelListener(ActionListener listener) {

            }
        };
    }

    @Override
    public IStartupView createStartupView(MapRiskModel mapRiskModel, PlayerModel playerModel) {
        return new IStartupView() {
            @Override
            public void setWelcomeMessage(String message) {

            }

            @Override
            public void addTroopsListener(BiConsumer<Object, CountryModel> listener) {

            }

            @Override
            public void addNextListener(ActionListener listener) {

            }
        };
    }

    @Override
    public IReinforcementView createReinforcementView(MapRiskModel mapRiskModel) {
        return new IReinforcementView() {
            @Override
            public void addAddListener(BiConsumer<Object, CountryModel> listener) {

            }
        };
    }

    @Override
    public IFortificationView createFortificationView(MapRiskModel mapRiskModel) {
        return new IFortificationView() {

            @Override
            public void addMoveListener(Consumer<MoveData> listener) {

            }

            @Override
            public void addFromChangeListener(Consumer<Integer> listener) {

            }
        };
    }

    @Override
    public IMapCountryConnectView createMapCountryConnectView(MapRiskModel mapRiskModel) {
        return new IMapCountryConnectView() {

            @Override
            public void addAddNeighbouringListener(BiConsumer<CountryModel, CountryModel> listener) {

            }

            @Override
            public void addRemoveNeighbouringListener(BiConsumer<CountryModel, CountryModel> listener) {

            }

            @Override
            public void addSaveListener(ActionListener listener) {

            }

            @Override
            public ListSelectionModel getLeftListSelectionModel() {
                return null;
            }

            @Override
            public JList getLeftCountryParentList() {
                return null;
            }

            @Override
            public JList getRightCountryParentList() {
                return null;
            }

            @Override
            public void setListSelectionListener(ListSelectionListener actionListener) {

            }
        };
    }
}
