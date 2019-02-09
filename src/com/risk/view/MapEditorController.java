package com.risk.view;


import com.risk.model.ContinentModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class MapEditorController {
    @FXML
    private Button addCountryButton;
    @FXML
    private ListView countryListView;
    @FXML
    private ListView continentListView;
    @FXML
    private BorderPane mapEditorBorderPane;
    @FXML
    private Button addContinentButton;

    private ContinentModel continentModel;


    public void initialize() {
        continentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    continentModel = (ContinentModel) continentListView.getSelectionModel().getSelectedItem();
                }
            }
        });
        //Updating continentlistview when app is initialized
        continentListView.setItems(ContinentData.getInstance().getContinents());
        continentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        continentListView.getSelectionModel().selectFirst();



    }


    public void handleAddDialog(ActionEvent event) {
        if (event.getSource() == addContinentButton) {
            //Creating a dialog to enter continents
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(mapEditorBorderPane.getScene().getWindow());
            dialog.setTitle("Add Continents!");
            dialog.setHeaderText("Add continents to the game along with their control values");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addContinentDIalog.fxml"));

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println("Can't load addcontinent dialog!");
                e.printStackTrace();
                return;
            }
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("OK pressed");
                AddContinentDIalogController addContinentDIalogController = fxmlLoader.getController();
                addContinentDIalogController.addNewContinent();

            }
        } else if (event.getSource() == addCountryButton) {
            //Creating a dialog to enter continents
            if (continentModel != null) {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.initOwner(mapEditorBorderPane.getScene().getWindow());
                dialog.setTitle("Add Continents!");
                dialog.setHeaderText("Add continents to the game along with their control values");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("addCountryDialog.fxml"));

                try {
                    dialog.getDialogPane().setContent(fxmlLoader.load());
                } catch (IOException e) {
                    System.out.println("Can't load addcontinent dialog!");
                    e.printStackTrace();
                    return;
                }
                dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("OK pressed");
                    AddCountryDialogController countryDialogController = fxmlLoader.getController();
                    countryDialogController.addNewCountry(continentModel.getContinentName());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Continent Selected Selected");
                alert.setHeaderText(null);
                alert.setContentText("PLease select the Continent for which you want to add the Country.");
                alert.showAndWait();
                return;
            }

        }
    }
}
