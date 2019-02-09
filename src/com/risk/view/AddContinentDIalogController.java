package com.risk.view;

import com.risk.model.ContinentModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Iterator;

/**
 * @author gursimransingh
 */
public class AddContinentDIalogController {
    @FXML
    private TextField continentNameTextField;
    @FXML
    private TextField controlValueTextField;

    public void addNewContinent() {
        String continentName = continentNameTextField.getText();
        int controlValue = Integer.parseInt(controlValueTextField.getText());

        ContinentModel continentModel = new ContinentModel(continentName, controlValue);
        System.out.println(continentModel.toString());

        ContinentData.getInstance().addContinentModel(continentModel);

    }
}

