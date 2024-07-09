module com.example.fxml_css_test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxml_css_test to javafx.fxml;
    exports com.example.fxml_css_test;
}