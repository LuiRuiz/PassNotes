package com.example.sceneslessons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.GregorianCalendar;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        // add width and height as args, Color
        Scene scene = new Scene(root,600,600, Color.OLIVE);

        Stage theStage = new Stage();
        //start text//
        Text text = new Text("Heyy!!");
        text.setX(50);
        text.setY(50);

        text.setFont(Font.font("Verdana",50));
        text.setFill(Color.SILVER);
        //end TEXT//
        //Start Line//
        Line line = new Line();

        line.setStartX(200);
        line.setStartY(200);

        line.setEndX(500);
        line.setEndY(200);

        line.setStrokeWidth(5);
        line.setStroke(Color.SILVER);

        line.setOpacity(.75);
        //End Line//

        //Start Rectangle//
        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.MEDIUMPURPLE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BEIGE);

        //START Triangle
        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                300.0,300.0,
                300.0,300.0,
                300.0, 300.0);
        triangle.setFill(Color.BLACK);
        //End Trianfle doesnt show

        //start circle
        Circle circle = new Circle();
        circle.setCenterX(700);
        circle.setCenterY(700);
        circle.setRadius(50);
        circle.setFill(Color.BLACK);

        //Images
        //Image image = new Image("title.png)
        //ImageView imageview = new ImageView(image)
        //setX()
        //setY()

        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);

        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}