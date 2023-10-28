module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires cmu.time.awb;
    requires freetts;
    requires jsapi;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

    opens com.example.demo.frontend.LoginFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.LoginFrontEnd;

    opens com.example.demo.frontend.navBarFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.navBarFrontEnd;

    opens com.example.demo.frontend.libChildFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.libChildFrontEnd;

    opens com.example.demo.frontend.LearnerFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.LearnerFrontEnd ;

//    opens com.example.demo.fxml to javafx.fxml;
//    exports com.example.demo.fxml;
}