package com.risk.view;

import com.risk.model.CountryModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author gursimransingh
 */
public class AddCountryDialogController {
    @FXML
    private TextField countryNameTextField;

    private CountryModel countryModel;

    public void addNewCountry(String continentName) {
        String countryName = countryNameTextField.getText();
        countryModel = new CountryModel();
        this.countryModel.setCountryName(countryName);
        this.countryModel.setContinentName(continentName);

        System.out.println(countryModel.toString());
    }
}
