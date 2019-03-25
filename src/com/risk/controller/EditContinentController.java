package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import com.risk.model.ContinentsModel;
import com.risk.model.GameMapModel;
import com.risk.utilities.ReadFile;
import com.risk.view.EditContinentView;
/**
 * In EditContinentController, the data flow into model object and updates the
 * view whenever data changes.
 * @author gursimransingh
 */
public class EditContinentController implements ActionListener {

    /** The edit continent view. */
    private EditContinentView editContinentView;

    /** The map model. */
    private GameMapModel mapModel;

    /** The new continent list. */
    private List<ContinentsModel> newContinentList;

    /**
     * Constructor initializes values and sets the screen too visible.
     */
    public EditContinentController() {

        /* The file. */
        File file = this.selectFile();
        /* The temp read. */
        ReadFile tempRead = new ReadFile();
        tempRead.setFile(file);
        /* The continent list. */
        List<ContinentsModel> continentList = tempRead.getMapContinentDetails();
        this.mapModel = new GameMapModel();
        this.mapModel.setCountries(tempRead.getMapCountryDetails());
        mapModel.setContinents(continentList);
        mapModel.callObservers();
        this.newContinentList = new ArrayList<>();
        continentList = this.mapModel.getContinents();
        this.editContinentView = new EditContinentView(continentList);
        this.editContinentView.setActionListener(this);
        this.editContinentView.setVisible(true);
        this.mapModel.addObserver(this.editContinentView);
    }

    /**
     * Browsing a file.
     *
     * @return File
     */
    private File selectFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        File file;
        int returnValue = jfc.showOpenDialog(this.editContinentView);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            file = new File(selectedFile.getAbsolutePath());
            return file;
        } else
            return null;

    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.editContinentView.addButton)) {
            if (this.editContinentView.controlValue.getText() != null
                    && !this.editContinentView.controlValue.getText().isEmpty()) {
                if (0 < Integer.parseInt(this.editContinentView.controlValue.getText())
                        && Integer.parseInt(this.editContinentView.controlValue.getText()) < 10) {

                    /* The new continent model. */
                    ContinentsModel newContinentModel = (ContinentsModel) this.editContinentView.continentListCombobox
                            .getSelectedItem();

                    this.mapModel.removeContinent(newContinentModel);

                    Objects.requireNonNull(newContinentModel)
                            .setValueControl(Integer.parseInt(this.editContinentView.controlValue.getText()));
                    this.newContinentList.add(newContinentModel);

                    System.out.println(this.newContinentList);

                } else {
                    JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                }
            } else {
                JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            }
        } else if (actionEvent.getSource().equals(this.editContinentView.saveButton)) {
            if (this.editContinentView.controlValue.getText() != null
                    && !this.editContinentView.controlValue.getText().isEmpty()) {
                if (0 < Integer.parseInt(this.editContinentView.controlValue.getText())
                        && Integer.parseInt(this.editContinentView.controlValue.getText()) < 10) {
                    if (!this.newContinentList.isEmpty()) {
                        this.mapModel.setContinents(newContinentList);
                        this.mapModel = this.mapModel.updateCountries(this.mapModel);
                        new ConnectCountryController(this.mapModel);
                        this.editContinentView.dispose();
                        // open connectCountries Controller and pass the map model
                    } else {
                        JOptionPane.showOptionDialog(null, "Please add atleast one continent first.", "Invalid",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                null);
                    }
                } else {
                    JOptionPane.showOptionDialog(null, "Please enter a control value between 0 and 10", "Invalid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                }
            } else {
                JOptionPane.showOptionDialog(null, "Please enter at least one control value", "Invalid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
            }
        }
    }
}
