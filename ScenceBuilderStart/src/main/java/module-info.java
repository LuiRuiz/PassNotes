module com.example.scencebuilderstart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scencebuilderstart to javafx.fxml;
    exports com.example.scencebuilderstart;
}