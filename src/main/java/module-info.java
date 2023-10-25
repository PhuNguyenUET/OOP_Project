module com.example.demo{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    /* requires cmu.time.awb;
    requires freetts;
    requires jsapi; */


    exports com.example.demo.frontend.LoginComponent;
    opens com.example.demo.frontend.LoginComponent to javafx.fxml;

    exports com.example.demo.frontend.DictionaryFrontEnd;
    opens com.example.demo.frontend.DictionaryFrontEnd to javafx.fxml, com.fasterxml.jackson.databind;


    exports com.example.demo.backend.DictionaryBackend;
    opens com.example.demo.backend.DictionaryBackend to com.fasterxml.jackson.databind;

    exports com.example.demo.frontend.Translator;
    opens com.example.demo.frontend.Translator to javafx.fxml;
}