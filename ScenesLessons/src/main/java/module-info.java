module com.example.sceneslessons {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sceneslessons to javafx.fxml;
    exports com.example.sceneslessons;
}