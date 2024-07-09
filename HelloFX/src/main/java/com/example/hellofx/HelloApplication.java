package com.example.hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

       // Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK); //scene must have root node// layout manager

        // pass scene to stage
        stage.setScene(scene);

        stage.setTitle("MyPRogramDemo");

        Image icon = new Image("Krystine-Batcho.jpg");

        //set image
        stage.getIcons().add(icon);

        //still resizable
        stage.setWidth(420);
        stage.setHeight(420);

        stage.setResizable(false);

        //set intial screen postion
        //stage.setX(50);
        //stage.setY(50);

        //esc to leave
        stage.setFullScreen(true);

        stage.setFullScreenExitHint("You must press esc to escapewk");



        stage.show(); // keep at end of start method
    }
}