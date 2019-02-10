package com.risk.controller;
/**
 * @author gursimransingh
 * Refererences: https://stackoverflow.com/questions/18736986/autocompletion-of-author-in-intellij
 * https://stackoverflow.com/questions/16176701/switch-between-panes-in-javafx
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class MainGameController {
    @FXML
    private Button mapEditorLoadbutton;
    @FXML
    private Button startGameButton;
    @FXML
    private Button exitButton;
    @FXML
    private GridPane gridpane;
    @FXML
    private Button mapEditorbutton;

    @FXML
    public void onClickMainButton(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == mapEditorbutton) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/mapEditor.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            System.out.println(actionEvent.getSource());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            System.out.println("NEW window");
            gridpane.getScene().getWindow().hide();

            // gridpane.getChildren().setAll(FXMLLoader.load(getClass().getResource("mapEditor.fxml")));
        } else if (actionEvent.getSource() == startGameButton) {
//           CODE HERE
        } else if (actionEvent.getSource() == mapEditorLoadbutton) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Map", "*.map")
            );
            File file = fileChooser.showOpenDialog(gridpane.getScene().getWindow());
            if (file != null) {
                System.out.println("The file is opened to edit. Path is:" + file.getAbsoluteFile());
            } else System.out.println("File not opened");

        } else Platform.exit();



    }


}
