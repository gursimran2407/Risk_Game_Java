package com.risk.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class mapEditorController extends Application {
    @FXML
    TextField continentNameTextField;

    public void onAddContinentButtonClicked() {
        System.out.println("Continent" + continentNameTextField.getText());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("MapeditorStarted");
    }

    @Override
    public void init() throws Exception {
        super.stop();
    }
}
