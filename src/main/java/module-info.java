module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires cmu.time.awb;
    requires freetts;
    requires jsapi;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}