package com.example.comsbetweencontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.util.Random;

public class copyPopUpController {
    String [][] colors = {{"#ffc8dd", "#bf99bf"},
            {"#bde0fe", "#86a4bf"},
            {"#fcecb6","#bfb38a"},
            {"#d5f0eb","#c1d9d4"},
            {"#fa9191","#c77373"}}; //pink, blue, yellow,green, red
    String [] color;



    @FXML
    AnchorPane mainPane;
    @FXML
    Pane secondPane;

    Random random = new Random();

    public void getPopUpColor(){
        color = colors[random.nextInt(colors.length)];

        secondPane.setStyle("-fx-background-color: "+color[1]+";");
        mainPane.setStyle("-fx-background-color: "+color[0]+";");
    }


}
