module com.test.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.test.demo to javafx.fxml;
    exports com.test.demo;
}