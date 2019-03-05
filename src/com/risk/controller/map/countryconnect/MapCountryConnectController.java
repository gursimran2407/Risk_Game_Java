package com.risk.controller.map.countryconnect;

import com.risk.Environment;
import com.risk.controller.main.MainGameController;
import com.risk.gameplayrequirements.MapValidation;
import com.risk.gameplayrequirements.MapWrite;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.map.countryconnect.IMapCountryConnectView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In MapCountryConnectController, also takes care of the movement of
 * data into the model corresponding to the view and the controller and
 * also takes care of updating the view whenever a
 * change is detected.
 *
 * @author Shyrans
 * @version 1.0.0
 */
public class MapCountryConnectController implements ListSelectionListener  {

    private final Environment environment;

    private MapRiskModel mapRiskModel;
    private IMapCountryConnectView view;
    private CountryModel countryModel;
    private String filename = null;
    private MapWrite mapWrite;

    private List<CountryModel> countryModelList;
    private List<CountryModel> countryLinks;

    /**
     * This constructor initializes values to be used in for view object and
     * the parameters to be passed on to the view classes.
     *
     * @param mapRiskModel
     */
    public MapCountryConnectController(final Environment environment, MapRiskModel mapRiskModel) {
        this.environment = environment;

        this.mapRiskModel = mapRiskModel;
        this.countryModelList = mapRiskModel.getCountryModelList();
        this.countryLinks = new ArrayList<>();

        this.view = environment.getViewManager().createMapCountryConnectView(mapRiskModel);
        this.view.addAddNeighbouringListener(this::addNeighbouringCountry);
        this.view.addRemoveNeighbouringListener(this::removeNeighbouringCountry);
        this.view.addSaveListener(e -> save());
        this.view.setListSelectionListener(this);
        this.view.showView();

        this.mapRiskModel.addObserver(this.view);
    }
    /**
     * This method is to the add the neighbouring countries to the existing player
     * selected countries.
     *
     * @param left
     * @param right
     */
    private void addNeighbouringCountry(final CountryModel left, final CountryModel right) {
        if (left.equals(right)) {
            view.showMessage("Invalid", "Cannot create a self link");
        } else {
            // If adding connection between two countries then setneighbouring countries to all the countries
            mapRiskModel.setNeighbouringCountry(left, right);

        }
    }
    /**
     * This method is to remove the neighbouring countries from the existing selected countries
     * of the players.
     *
     * @param left
     * @param right
     */
    private void removeNeighbouringCountry(final CountryModel left, final CountryModel right) {
        mapRiskModel.removeNeighbouringCountry(left, right);
    }

    private void save() {
        MapValidation mapValidation = new MapValidation();
        boolean flag1 = mapValidation.emptyLinkCountryValidation(mapRiskModel);

        boolean flag3 = mapValidation.emptyContinentValidation(mapRiskModel);
        boolean flag2 = mapValidation.checkInterlinkedContinent(mapRiskModel);
        System.out.println(flag1 + " " + flag2 + " " + flag3);
        if (!(mapValidation.emptyLinkCountryValidation(mapRiskModel))) {
            if ((!mapValidation.checkInterlinkedContinent(mapRiskModel))) {
                if (!(mapValidation.emptyContinentValidation(mapRiskModel))) {
                    if (!(mapValidation.unlinkedContinentValidation(mapRiskModel))) {

                        System.out.println(" All the map validations are correct");
                        filename = JOptionPane.showInputDialog("File Name");
                        try {
                            System.out.println(filename);
                            mapWrite = new MapWrite();
                            mapWrite.writeMapToFile(filename, mapRiskModel);
                            JOptionPane.showMessageDialog(null, "Map has been created select it before you play");
                            new MainGameController(environment);
                            this.view.hideView();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("All continents are not linked");
                        view.showMessage("Invalid", "All continents are not linked");
                    }

                } else {
                    System.out.println("Empty link country validation failed");
                    view.showMessage("Invalid", "Empty continent validation failed");
                }
            } else {
                System.out.println("ECheck interlinked Continent validation failed");
                view.showMessage("Invalid", "Check interlinedContinent validation failed");

            }
        } else {
            System.out.println("Empty continent validation failed");
            view.showMessage("Invalid", "Empty link country validation failed");
        }
    }

    /**
     * This method is to check the value changed in the index values and
     * update the UI with the changes.
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();
        System.out.println("Event for indexes " + firstIndex + " - " + lastIndex + "; isAdjusting is " + isAdjusting
                + "; selected indexes:");

        if (listSelectionModel.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
            // Looking which index is being selected
            int minRightIndex = listSelectionModel.getMinSelectionIndex();
            int maxRightIndex = listSelectionModel.getMaxSelectionIndex();
            int finalRightModelIndex = 0;
            for (int i = minRightIndex; i <= maxRightIndex; i++) {
                if (this.view.getLeftListSelectionModel().isSelectedIndex(i)) {
                    finalRightModelIndex = i;
                }
            }
            System.out.println(finalRightModelIndex);
        }

        if (e.getSource().equals(this.view.getLeftCountryParentList())) {
            mapRiskModel.setCountryColor(
                    (CountryModel) this.view.getLeftCountryParentList().getSelectedValue(), Color.GREEN);
        } else if (e.getSource().equals(this.view.getRightCountryParentList())) {
            mapRiskModel.setCountryColor(
                    (CountryModel) this.view.getRightCountryParentList().getSelectedValue(), Color.YELLOW);
        }
    }
}
