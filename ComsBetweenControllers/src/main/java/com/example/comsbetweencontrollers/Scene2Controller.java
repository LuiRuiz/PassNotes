package com.example.comsbetweencontrollers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Scene2Controller {
    byte[] masterKey;
    byte[] fileKey;
    String IV;
    boolean firstRun;
    private ArrayList<Password> ActiveList;
    NewPassword generatePass;


    @FXML
    private AnchorPane pane;
    @FXML
    ButtonBar mainBar;
    @FXML
    GridPane gridPane;
    @FXML
    TextField newPassNameField;
    @FXML
    TextField symbolsField;
    @FXML
    TextField sizeField;

    @FXML
    CheckBox upperCaseCheck;

    @FXML
    CheckBox numbersCheck;

    @FXML
    CheckBox symbolsCheck;

    @FXML
    TextField viewPassBar;








    Stage stage;
    int n = 1;



    public void initializeKey(byte[] key,String KeyAndIVsArg, boolean runType){
        //This will be needed regardless of runtype

        IV = KeyAndIVsArg;
        fileKey = key;
        firstRun = runType;

    }
    public  void setArray(boolean runType){
        //
        if (!runType){
            try {
                ActiveList = AESEncryptDecrypt.readFromFile(fileKey, IV);

            } catch (ClassNotFoundException | IOException e) {
                ActiveList = new ArrayList<Password>();

            }



        }else{
            ActiveList = new ArrayList<Password>();
        }

    }
    public void populate(){
        if(ActiveList.size() >=1){
            for(int i = 0; i < ActiveList.size(); i++){
                RowConstraints con = new RowConstraints();
                //-ColumnConstraints colCon = new ColumnConstraints();

                // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
                con.setMinHeight(52);
                con.setMaxHeight(52);
                gridPane.getRowConstraints().add(con);

                //Add name of item
                Label addLabel = new Label(ActiveList.get(i).getName());
                GridPane.setHalignment(addLabel, HPos.CENTER);
                gridPane.add(addLabel,0,i); //sends item to first row

                ButtonBar buttonBar = new ButtonBar();
                // Create the buttons to go into the ButtonBar
                Button thisPassword = new Button("Get Pass");
                //ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);
                int finalI = i;
                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {

                        System.out.println(ActiveList.get(finalI).getEncryptedPassword());
                    }
                };

                // when button is pressed
                thisPassword.setOnAction(event);
                thisPassword.setAlignment(Pos.CENTER);
                buttonBar.getButtons().addAll(thisPassword);

                GridPane.setHalignment(buttonBar, HPos.CENTER);


                gridPane.add(buttonBar, 1,i);







            }
        }

    }
    public void Hardlaunch(String name, int placement){
        RowConstraints con = new RowConstraints();
        ColumnConstraints colCon = new ColumnConstraints();

        // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
        con.setMinHeight(52);
        con.setMaxHeight(52);
        gridPane.getRowConstraints().add(con);

        //gridPane.add(new Label("First"), 0,n);
        Label addLabel = new Label(name);

        //Sets item to Center Position
        //then adds item
        GridPane.setHalignment(addLabel, HPos.CENTER);
        gridPane.add(addLabel,0,placement);
        // Create the ButtonBar instance
        ButtonBar buttonBar = new ButtonBar();
        // Create the buttons to go into the ButtonBar
        Button yesButton = new Button("Yes");
        //ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println(name);
            }
        };

        // when button is pressed
        yesButton.setOnAction(event);
        yesButton.setAlignment(Pos.CENTER);
        buttonBar.getButtons().addAll(yesButton);

        GridPane.setHalignment(buttonBar, HPos.CENTER);


        gridPane.add(buttonBar, 1,placement);


    }
    public void displayPass(){
        viewPassBar.setText(generatePass.getEncryptedPassword());

    }
    public int generate(ActionEvent press){

        String name = newPassNameField.getText();
        int size = 8;
        try{
             size = Integer.valueOf(sizeField.getText());
        } catch (NumberFormatException e){
            return 0;
        }

        boolean[] include = {upperCaseCheck.isSelected(),numbersCheck.isSelected(),symbolsCheck.isSelected()};
        System.out.println("Size:" + include.length);
        for (boolean item : include){
            System.out.print(item);
        }

        System.out.println("From addRow\nsymbolsField: " + symbolsField.getText());
        if(symbolsField.getText().equals("")){
             generatePass = new NewPassword(size, newPassNameField.getText(), include);
        }else{
             generatePass = new NewPassword(size, newPassNameField.getText(), include, symbolsField.getText());

        }
        displayPass();
        /*Create new password using textField*/


//        ActiveList.add(generatePass);         we dont want to add it here
//
//        int placement = ActiveList.size()-1;
//
//
//
//
//
//
//        RowConstraints con = new RowConstraints();
//
//        // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
//        con.setMinHeight(52);
//        con.setMaxHeight(52);
//        gridPane.getRowConstraints().add(con);
//
//        //gridPane.add(new Label("First"), 0,n);
//        Label addLabel = new Label(generatePass.getName());
//
//        //Sets item to Center Position
//        //then adds item
//        GridPane.setHalignment(addLabel, HPos.CENTER);
//        gridPane.add(addLabel,0,placement);
//        // Create the ButtonBar instance
//        ButtonBar buttonBar = new ButtonBar();
//
//        // Create the buttons to go into the ButtonBar
//        Button newPassword = new Button("Get Pass");
//        //ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                System.out.println(generatePass.getEncryptedPassword());
//            }
//        };
//
//        // when button is pressed
//        newPassword.setOnAction(event);
//
//
//
//
//        // Add buttons to the ButtonBar
//        newPassword.setAlignment(Pos.CENTER);
//        buttonBar.getButtons().addAll(newPassword);
//
//        GridPane.setHalignment(buttonBar, HPos.CENTER);
//
//
//        gridPane.add(buttonBar, 1,placement);
//        n++;
        return 0;
    }
    public void addPass(){
        if (generatePass.getEncryptedPassword() != null){
        ActiveList.add(generatePass);

        int placement = ActiveList.size()-1;






        RowConstraints con = new RowConstraints();

        // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
        con.setMinHeight(52);
        con.setMaxHeight(52);
        gridPane.getRowConstraints().add(con);

        //gridPane.add(new Label("First"), 0,n);
        Label addLabel = new Label(generatePass.getName());

        //Sets item to Center Position
        //then adds item
        GridPane.setHalignment(addLabel, HPos.CENTER);
        gridPane.add(addLabel,0,placement);
        // Create the ButtonBar instance
        ButtonBar buttonBar = new ButtonBar();


        // Create the buttons to go into the ButtonBar
        Button newPassword = new Button("Get Pass");
        //ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println(generatePass.getEncryptedPassword());
            }
        };

        // when button is pressed
        newPassword.setOnAction(event);




        // Add buttons to the ButtonBar
        newPassword.setAlignment(Pos.CENTER);
        buttonBar.getButtons().addAll(newPassword);

        GridPane.setHalignment(buttonBar, HPos.CENTER);


        gridPane.add(buttonBar, 1,placement);
        n++;
        //generatePass = null;
        } else{
            System.out.println("Error in addPass");
        }


    }
    public void printYes(){
        System.out.println("Yes");
    }
    public void printNo(){
        System.out.println("No");
    }
    public void savePasses(){
        try {
            AESEncryptDecrypt.sendToFile(ActiveList, fileKey, IV);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File sent sucessfuly");




    }
    public void SaveOnExit(ActionEvent e){

        // alert object created!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Youre about to logout");
        alert.setContentText("DO YOU WISH TO SAVE B4 exiting");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) pane.getScene().getWindow();
            savePasses();
            System.out.println("Successful Logout");
            stage.close();
        }



    }



}
