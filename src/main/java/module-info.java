module com.example.demo{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    /* requires cmu.time.awb;
    requires freetts;
    requires jsapi; */


    exports com.example.demo.frontend.LoginComponent;
    opens com.example.demo.frontend.LoginComponent to javafx.fxml;

    exports com.example.demo.frontend.DictionaryFrontEnd;
    opens com.example.demo.frontend.DictionaryFrontEnd to javafx.fxml;
}