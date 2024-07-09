package com.example.logout;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene main = new Scene(root);

        stage.setScene(main);

        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage);
        });
    }
    public void logout(Stage stage ){
        // alert object created!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Youre about to logout");
        alert.setContentText("DO YOU WISH TO SAVE B4 exiting");

        if (alert.showAndWait().get() == ButtonType.OK){


            System.out.println("Successful Logout");
            stage.close();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}