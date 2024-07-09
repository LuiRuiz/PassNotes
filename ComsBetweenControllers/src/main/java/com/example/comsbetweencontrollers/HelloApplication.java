package com.example.comsbetweencontrollers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloApplication extends Application {



    private int tracker = 0;

    private boolean loopCondition = true;
    @Override
    public void start(Stage stage) throws IOException {
        String myFXML = "login.fxml";
        final String necessaryFile = "SerializableTest.txt";
        //Our TryCatch to create the files.
        try {
            File file = new File(necessaryFile);
            if (file.createNewFile()) {
                //set intial login page for FXMLoader resource
                myFXML = "SetUpLogin.fxml";


            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        Parent root = FXMLLoader.load(getClass().getResource(myFXML));

        Scene main = new Scene(root);

        stage.setScene(main);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    public void saveOnCLose(Stage stage){


    }
}