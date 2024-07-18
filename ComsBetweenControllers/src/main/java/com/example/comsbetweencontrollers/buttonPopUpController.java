package com.example.comsbetweencontrollers;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;


public class buttonPopUpController {

    String [][] colors = {{"#ffc8dd", "#bf99bf"},
                    {"#bde0fe", "#86a4bf"},
                        {"#fcecb6","#bfb38a"},
                    {"#d5f0eb","#c1d9d4"},
                    {"#fa9191","#c77373"}}; //pink, blue, yellow,green, red
    String [] color;

    @FXML
    Pane secondPane;

    @FXML
    AnchorPane mainPane;

    Random random = new Random();

    @FXML
    Label nameLabel; @FXML
    Label passLabel; @FXML
    Label changeLabel;

    @FXML TextField nameField;

    Password activePassword;
    Clipboard clipboard = Clipboard.getSystemClipboard();

    public void setUp(Password password ){

        activePassword = password;
         nameLabel.setText(activePassword.name);
         String blank = "";
         for(int i = 0; i < activePassword.passLength; i++){
             blank+="*";
         }
         passLabel.setText(blank);

         color = colors[random.nextInt(colors.length)];

         secondPane.setStyle("-fx-background-color: "+color[1]+";");
         mainPane.setStyle("-fx-background-color: "+color[0]+";");
    }
    public void copyPass (){
        ClipboardContent content = new ClipboardContent();
        content.putString(activePassword.getEncryptedPassword());
        clipboard.setContent(content);
    }
    public void editName(){

       if (!nameField.isVisible()){
           nameField.setVisible(true);
           nameField.setDisable(false);
           nameField.setText("");

       }else{
           nameField.setVisible(false);
           nameField.setDisable(true);
       }

    }

    public void changeName(){
        activePassword.setName(nameField.getText());
        nameLabel.setText(activePassword.name);
        nameField.setDisable(true);
        nameField.setVisible(false);
    }


}
