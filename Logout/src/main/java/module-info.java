module com.example.logout {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.logout to javafx.fxml;
    exports com.example.logout;
}