package com.example.comsbetweencontrollers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    MenuButton lengthMenu;

    @FXML
    CheckBox upperCaseCheck;

    @FXML
    CheckBox numbersCheck;

    @FXML
    CheckBox symbolsCheck;

    @FXML
    TextField viewPassBar;

    @FXML TextField searchField;
    @FXML GridPane searchGrid;








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
            gridPane.getChildren().clear();
            for(int i = 0; i < ActiveList.size(); i++){

                //ColumnConstraints colCon = (ColumnConstraints) gridPane.getColumnConstraints();
                //RowConstraints con = (RowConstraints) gridPane.getRowConstraints();










                RowConstraints con = new RowConstraints();
                ColumnConstraints colCon1 = new ColumnConstraints();





                // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
                con.setMinHeight(52);
                con.setMaxHeight(52);
                gridPane.getRowConstraints().add(con);
                Pane popPane = makePane(ActiveList.get(i));

                //Add name of item

                GridPane.setHalignment(popPane, HPos.CENTER);
                gridPane.add(popPane,0,i);
                popPane.prefWidth(popPane.USE_COMPUTED_SIZE);//sends item to first row

                ButtonBar buttonBar = new ButtonBar();
                //buttonBar.setStyle("-fx-background-color: white;");
                // Create the buttons to go into the ButtonBar
                Button thisPassword = makeButton(ActiveList.get(i));
                Button delete = new Button("\uD83D\uDDD1");

                delete.setMaxSize(11, 36);
                delete.setId("gridButton");
                delete.getStylesheets().add(getClass().getResource("Scene2.css").toExternalForm());

                int finalI1 = i;
                EventHandler <ActionEvent> deleteFunction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deletePass(ActiveList.get(finalI1));

                    }
                };
                delete.setOnAction(deleteFunction);
                buttonBar.getButtons().addAll(delete,thisPassword);
                int finalI = i;


                // when button is pressed

                thisPassword.setAlignment(Pos.CENTER);
                //buttonBar.getButtons().addAll(thisPassword);

                GridPane.setHalignment(buttonBar, HPos.CENTER);


                gridPane.add(buttonBar, 1,i);







            }
        }

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
        //System.out.println("Size:" + include.length);

        /*for (boolean item : include){
            System.out.print(item);
        }*/

        //System.out.println("From addRow\nsymbolsField: " + symbolsField.getText());
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
        populate();


        /*
        int placement = ActiveList.size()-1;






        RowConstraints con = new RowConstraints();

        // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
        con.setMinHeight(52);
        con.setMaxHeight(52);
        gridPane.getRowConstraints().add(con);

        ColumnConstraints firstCol = new ColumnConstraints();
        //firstCol.setHgrow(Priority.ALWAYS);
        firstCol.setPercentWidth(70);

        ColumnConstraints secondCol = new ColumnConstraints();
        //secondCol.setPercentWidth(30);
        secondCol.setHgrow(Priority.ALWAYS);


        gridPane.getColumnConstraints().addAll(firstCol,secondCol );

        Pane pane = makePane(generatePass);



        //gridPane.add(new Label("First"), 0,n);








            GridPane.setHalignment(pane, HPos.LEFT);
            gridPane.add(pane,0,placement);








        // Create the buttons to go into the ButtonBar
        Button newPassword = makeButton(generatePass);
        //ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES);




        // Add buttons to the ButtonBar
        newPassword.setAlignment(Pos.CENTER);


        GridPane.setHalignment(newPassword, HPos.CENTER);


        gridPane.add(newPassword, 1,placement);
        n++;
        //generatePass = null;

         */
        } else{
            System.out.println("Error in addPass");
        }




    }

    public void savePasses(){
        try {
            AESEncryptDecrypt.sendToFile(ActiveList, fileKey, IV);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File sent sucessfuly");




    }
    //function to set Length menu and button effects to change value of sizeField
    public void setSizeMenu(){
        for (int i = 8; i<= 32; i = i+2){
            String iText = String.valueOf(i);
            MenuItem newItem = new MenuItem(iText);
            // Setting Event to add to the menuitem;
            EventHandler<ActionEvent> changeSizeFieldValue = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    sizeField.setText(iText);
                }
            };
            newItem.setOnAction(changeSizeFieldValue);

            lengthMenu.getItems().add(newItem);

        }

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

    private Pane makePane( Password generatePass){
        Pane activePane = new Pane();
        activePane.setMaxHeight(50);
        activePane.setMinHeight(50);
        activePane.setCursor(Cursor.HAND);
        activePane.getStylesheets().add(getClass().getResource("Scene2.css").toExternalForm());
        Label addLabel = new Label(generatePass.getName());


        activePane.getChildren().add(addLabel);
        activePane.getStyleClass().add("pane");

        EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e)
            {
                activePane.setCursor(Cursor.CLOSED_HAND);
                copyToClipBoard(generatePass.getEncryptedPassword());
                activePane.setCursor(Cursor.HAND);









                FXMLLoader loader = new FXMLLoader(getClass().getResource("copyPopUp.fxml"));

                Parent root;
                try {
                    root = loader.load();
                    copyPopUpController copyController = loader.getController();
                    copyController.getPopUpColor();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }





                Scene scene = new Scene(root);


                Stage stage = new Stage();


                stage.setScene(scene);


                stage.setTitle("My Window");



                stage.show();

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        System.out.println("Stage is closing");
                    }
                });
            };

        };




        activePane.setOnMouseClicked( onClick );

        //addLabel.layoutXProperty().bind(activePane.widthProperty().divide(10));


        //addLabel.setStyle("-fx-label-padding: 100em 0 100em 0;");
        //addLabel.prefWidth(Parent.USE_COMPUTED_SIZE);

        addLabel.layoutYProperty().bind(activePane.heightProperty().subtract(addLabel.heightProperty()).divide(2));
        addLabel.setTextOverrun(OverrunStyle.CLIP);
        addLabel.setEllipsisString("...");
        addLabel.setWrapText(true);
        //activePane.setStyle("-fx-background-color: green");

        return activePane;
    }

    private void copyToClipBoard(String string){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(string);
        clipboard.setContent(content);


    }

    private Button makeButton(Password password){
        Button editButton = new Button("≡");

        EventHandler <ActionEvent> setButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("buttonPopUp.fxml"));

                Parent root;
                try {
                    root = loader.load();
                    buttonPopUpController buttoncontroller = loader.getController();
                    buttoncontroller.setUp(password);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }




                Scene scene = new Scene(root);


                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("My Window");
                stage.show();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        //System.out.println("Stage is closing");
                        populate();
                    }
                });


            }
        };

        editButton.setOnAction(setButton);
        return editButton;



    }

    public void deletePass(Password myPass){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Password");
        alert.setHeaderText("Are you sure you wish to delete this password.\n (Deleted Passwords CANNOT be recovered");
        alert.setContentText("DO YOU WISH TO CONTINUE?");

        if (alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) pane.getScene().getWindow();

            ActiveList.remove(myPass);
            populate();


        }



    }

    @FXML
    private void minimize(ActionEvent action){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setIconified(true);

    }
    @FXML
    private void maximize(ActionEvent action){
        Stage stage = (Stage) pane.getScene().getWindow();

        if(stage.isMaximized()){
            stage.setMaximized(false);
        }else{
            stage.setMaximized(true);
        }

    }

    @FXML
    private void search(ActionEvent searchButton){
        String searchWord = searchField.getText();
        int i=0;
        searchGrid.getChildren().clear();

        for (Password item : ActiveList){

            if(item.getName().contains(searchWord)){

                Pane newPane = makePane(item);

                i++;
                RowConstraints con = new RowConstraints();
                // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
                con.setMinHeight(52);
                con.setMaxHeight(52);
                searchGrid.getRowConstraints().add(con);
                searchGrid.add(newPane,0,i);



            }
        }
        if(i==0){

            Pane activePane = new Pane();
            activePane.setMaxHeight(50);
            activePane.setMinHeight(50);
            activePane.setCursor(Cursor.HAND);
            activePane.getStylesheets().add(getClass().getResource("Scene2.css").toExternalForm());
            Label addLabel = new Label("No Results Found");
            activePane.getChildren().add(addLabel);
            activePane.getStyleClass().add("pane");
            RowConstraints con = new RowConstraints();
            // Here we set the pref height of the row, but you could also use .setPercentHeight(double) if you don't know much space you will need for each label.
            con.setMinHeight(52);
            con.setMaxHeight(52);
            gridPane.getRowConstraints().add(con);
            searchGrid.add(activePane,0,i);
        }
    }




}
