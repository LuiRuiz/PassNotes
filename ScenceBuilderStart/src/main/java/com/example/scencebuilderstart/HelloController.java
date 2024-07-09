package com.example.scencebuilderstart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class HelloController {
    @FXML
    private Circle myCircle;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    private int one = 0;
    private  int two = 0;
    private  int three = 0;
    private  int four = 0;
    private String[] words = {"One","Two","Three"};
    @FXML
   public void up(ActionEvent e){

        button1.setText(words[one]);
        one++;


   }
   public void down(ActionEvent e){
       button4.setText(words[two]);
       two++;
   }
    public void right(ActionEvent e){
        button3.setText(words[three]);
        three++;
    }
    public void left(ActionEvent e){
        button2.setText(words[four]);
        four++;
    }
}