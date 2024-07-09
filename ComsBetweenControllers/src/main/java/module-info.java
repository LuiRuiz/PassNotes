module com.example.comsbetweencontrollers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.comsbetweencontrollers to javafx.fxml;
    exports com.example.comsbetweencontrollers;
}