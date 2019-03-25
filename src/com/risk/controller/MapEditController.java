package com.risk.controller;

import com.risk.gameplayrequirements.MapRead;
import com.risk.view.MapEditView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * In MapEditController, the data flow into model object and updates the
 * view whenever data changes.
 * @author gursimransingh
 */
public class MapEditController implements ActionListener {

    private MapEditView mapEditView;
    private MapRiskModel mapRiskModel;
    private ArrayList<ContinentModel> continentModelList;

    private MapRead mapRead = new MapRead();
    private File file;
    private ContinentModel continentModel;
    private ArrayList<ContinentModel> updateContinentModelList;
    private ContinentModel updatedContinentModel;

    /**
     * Constructor initializes values and sets the screen to visible
     */

    public MapEditController() {
        file = openFileToEdit();
        mapRead.setReadFile(file);
        continentModelList = mapRead.getMapContinentDetails();
        mapRiskModel = new MapRiskModel();
        mapRiskModel.setCountryModelList(mapRead.getMapCountryDetails());
        mapRiskModel.setContinentModelModList(continentModelList);
        mapRiskModel.callObservers();
        //updating the continent model list
        updateContinentModelList = new ArrayList<>();
        continentModelList = mapRiskModel.getContinentModelList();
        //calling view constructor
        mapEditView = new MapEditView(continentModelList);
        mapEditView.setActionListener(this);
        mapEditView.setVisible(true);
        mapRiskModel.addObserver(mapEditView);
    }
    /**
     * @param e Performs action whenever there is a change in MapEditView class
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        //if add button is clicked
        if (e.getSource().equals(mapEditView.addButton)) {
            if (mapEditView.controlValue.getText() != null && !mapEditView.controlValue.getText().isEmpty()) {
                if (0 < Integer.parseInt(mapEditView.controlValue.getText()) && Integer.parseInt(mapEditView.controlValue.getText()) < 10) {

                    updatedContinentModel = (ContinentModel) mapEditView.continentListCombobox.getSelectedItem();
                    //removing the selected continent from continent model
                    mapRiskModel.removeContinent(updatedContinentModel);

                    updatedContinentModel.setControlValue(Integer.parseInt(mapEditView.controlValue.getText()));
                    updateContinentModelList.add(updatedContinentModel);

                    System.out.println(updateContinentModelList);

                } else {
                    JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                    return;
                }
            } else {
                JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                return;
            }
        }

//if save button is pressed
        else if (e.getSource().equals(mapEditView.saveButton)) {
            if (mapEditView.controlValue.getText() != null && !mapEditView.controlValue.getText().isEmpty()) {
                if (0 < Integer.parseInt(mapEditView.controlValue.getText()) && Integer.parseInt(mapEditView.controlValue.getText()) < 10) {
                    if (!updateContinentModelList.isEmpty()) {
                        mapRiskModel.setContinentModelModList(updateContinentModelList);
                        mapRiskModel = mapRiskModel.updateCountries(mapRiskModel);
                        new MapCountryConnectController(mapRiskModel);
                        mapEditView.dispose();
                        // open connectCountries Controller and pass the map model
                    } else {
                        JOptionPane.showOptionDialog(null, "Please add atleast one continent first.", "Invalid",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{},
                                null);
                        return;
                    }
                } else {
                    JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                    return;
                }
            } else {
                JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                return;
            }
        }

    }

    /**
     * Choosing a file to edit
     */
    public File openFileToEdit() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        File file;
        int returnValue = jfc.showOpenDialog(mapEditView);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File openedFile = jfc.getSelectedFile();
            System.out.println(openedFile.getAbsolutePath());
            file = new File(openedFile.getAbsolutePath());
            return file;
        } else
            return null;

    }
}
