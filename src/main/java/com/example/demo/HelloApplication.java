package com.example.demo;

import com.example.demo.backend.Connect;
import com.example.demo.backend.TextToSpeech;
import com.example.demo.backend.SQLCommand;
import com.example.demo.backend.userDatabaseConnect;
import com.example.demo.backend.userDatabaseSQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.*;
import com.sun.speech.freetts.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Locale;
import java.util.Objects;
import java.util.Stack;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader navBar = new FXMLLoader(getClass().getResource("navBar.fxml"));
//        FXMLLoader libChild = new FXMLLoader(getClass().getResource("listOfFolder.fxml"));
//        FXMLLoader learner = new FXMLLoader(getClass().getResource("Learner.fxml"));
//        StackPane navBarPane= navBar.load();
//        StackPane learnerPane = learner.load();
//        VBox wordOfList= libChild.load();
//        StackPane root = new StackPane();
//        root.getChildren().add(navBarPane);
//        root.getChildren().add(learnerPane);
//        root.getChildren().add(wordOfList);
//        StackPane leanerPane=learner.load();
//        Pane libChildPane=libChild.load();
//        Parent root = FXMLLoader.load(getClass().getResource("wordOfList.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("Learner.fxml"));
/*        StackPane root = new StackPane();
        StackPane.setAlignment(navBarPane, Pos.TOP_LEFT);
        StackPane.setAlignment(libChildPane, Pos.BOTTOM_RIGHT);
        root.getChildren().add(navBarPane);
        root.getChildren().add(libChildPane);
        root.getChildren().remove(libChildPane);
        root.getChildren().add(leanerPane);*/
        ScreenManager.getInstance().setStage(primaryStage);
        primaryStage.setTitle("My JavaFX App");
        ScreenManager.getInstance().switchToLogin();
        primaryStage.show();
    }

    public static void main(String[] args) {
//        Connection connection= userDatabaseConnect.connect();
//        TextToSpeech.processTextToSpeech("Hello");
//        userDatabaseSQL.createtable(connection);
//        userDatabaseSQL.insertIntoDict(connection,"name1","pass1",1);
//        userDatabaseSQL.showAll(connection);
        launch(args);
    }
}