package com.risk.controller;

import com.risk.gameplayrequirements.MapRead;
import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.MapEditView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gursimransingh
 */
public class MapEditController implements ActionListener {

    private MapEditView mapEditView;
    private MapRiskModel mapRiskModel;
    private ArrayList<ContinentModel> continentModelList;

    private MapRead mapRead = new MapRead();
    private File file;
    private ContinentModel continentModel;
    private List<ContinentModel> updateContinentModelList;


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


    @Override
    public void actionPerformed(ActionEvent e) {
        //if add button is clicked
        if (e.getSource().equals(mapEditView.addButton)) {

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
