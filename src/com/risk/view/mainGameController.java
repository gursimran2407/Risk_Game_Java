package com.risk.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class mainGameController {

    public void onClickMapEditor(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mapEditor.fxml"));
        Scene scene = new Scene(root);
        System.out.println(actionEvent.getSource());
        stage.setScene(scene);
        stage.show();
    }


}
