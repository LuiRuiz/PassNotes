package com.example.logout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLOutput;

public class HelloController {

    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane scenePane;

    Stage stage;

    public void logout(ActionEvent event){
        // alert object created!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Youre about to logout");
        alert.setContentText("DO YOU WISH TO SAVE B4 exiting");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();

            System.out.println("Successful Logout");
            stage.close();
        }


    }

}