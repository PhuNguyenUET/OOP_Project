module com.example.demo{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires cmu.time.awb;
    requires freetts;
    requires jsapi;
    requires com.fasterxml.jackson.core;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.media;


    exports com.example.demo.frontend.DictionaryFrontEnd;
    opens com.example.demo.frontend.DictionaryFrontEnd to javafx.fxml, com.fasterxml.jackson.databind;


    exports com.example.demo.backend.DictionaryBackend;
    opens com.example.demo.backend.DictionaryBackend to com.fasterxml.jackson.databind;

    exports com.example.demo.backend.LearnerBackend;

    exports com.example.demo.backend.MultipleChoiceBackend;
    opens com.example.demo.backend.MultipleChoiceBackend to com.fasterxml.jackson.databind;

    exports com.example.demo.frontend.MultipleChoice;
    opens com.example.demo.frontend.MultipleChoice to com.fasterxml.jackson.databind, javafx.fxml;

    exports com.example.demo.frontend.Translator;
    opens com.example.demo.frontend.Translator to javafx.fxml;
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

    opens com.example.demo.frontend.FlipGameFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.FlipGameFrontEnd;

    opens com.example.demo.frontend.SettingsFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.SettingsFrontEnd;

    opens com.example.demo.frontend.ProfileFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.ProfileFrontEnd;

    opens com.example.demo.frontend.CanvasFrontEnd to javafx.fxml;
    exports com.example.demo.frontend.CanvasFrontEnd;
}