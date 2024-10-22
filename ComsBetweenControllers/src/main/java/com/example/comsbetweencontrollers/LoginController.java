package com.example.comsbetweencontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LoginController {
    @FXML
    PasswordField loginField;
    @FXML
    Label PasswordErrorString;
    byte[] masterKey;
    byte[] passKey;
    String[] KeyandIVs;
    String fileIV;



    final String firstLoginConst = "firstLogin";


    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean toMainScene;
    private boolean firstRun = false;
    byte[] fileKey;

    public void pressLogin(ActionEvent event)throws IOException{
       // set source to root!!
        String loginType = loginField.getParent().getId();

        //for initial login
        if (loginType.equals(firstLoginConst)){
            String entry = loginField.getText();
            if (loginField.getText() == "" || entry.length() <=1 ){
                PasswordErrorString.setOpacity(1);
            }
            //here we generate the MasterPasskey and IV's
            else{
                initialize(entry);

                 KeyandIVs = AESEncryptDecrypt.readEncryptedKey();
                 passKey = AESEncryptDecrypt.getMasterKey(entry);
                try {
                    fileKey = AESEncryptDecrypt.decyptKey(KeyandIVs[0], KeyandIVs[1].substring(0,16), passKey);
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e) {
                    throw new RuntimeException(e);
                } catch (InvalidAlgorithmParameterException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeyException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchPaddingException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

                toMainScene = true;
                firstRun = true;

            }

        } ///PUT REGULAR LOGIN HERE!!!!!!!!
        else{
            try {
                KeyandIVs = AESEncryptDecrypt.readEncryptedKey();
                 passKey = AESEncryptDecrypt.getMasterKey(loginField.getText());
                 fileKey = AESEncryptDecrypt.decyptKey(KeyandIVs[0], KeyandIVs[1].substring(0,16), passKey);
                toMainScene = true;


            } catch (IllegalBlockSizeException| InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {

            toMainScene = false;
            PasswordErrorString.setOpacity(1);}

        }

        if(toMainScene){
            fileIV = KeyandIVs[1].substring(16);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));

            root = loader.load();

            //adds controller to main controller
            Scene2Controller scene2Controller = loader.getController();
            scene2Controller.setSizeMenu();
            scene2Controller.initializeKey(fileKey,fileIV,firstRun);
            scene2Controller.setArray(firstRun);
            scene2Controller.populate();

            //scene2Controller.initializeKey(masterKey);
            //scene2Controller.Hardlaunch("David",1);
            //scene2Controller.Hardlaunch("Micheal",2);

            ///scene2Controller.displayName(); use this logic to send data


            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();





        }








    }


    private void initialize(String masterPassword)
        {
        AESEncryptDecrypt aesEncryptDecrypt = new AESEncryptDecrypt();
        try {
            aesEncryptDecrypt.keyGenerator(masterPassword);

        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


}