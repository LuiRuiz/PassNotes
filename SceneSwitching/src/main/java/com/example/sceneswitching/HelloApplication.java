package com.example.sceneswitching;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       try{
           Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
           Scene scene1 = new Scene(root);
           stage.setScene(scene1);
           stage.show();
       } catch(Exception e){
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}