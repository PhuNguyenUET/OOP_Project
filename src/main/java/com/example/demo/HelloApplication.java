package com.example.demo;

import com.example.demo.backend.Connect;
import com.example.demo.backend.TextToSpeech;
import com.example.demo.backend.SQLCommand;
import com.example.demo.backend.userDatabaseConnect;
import com.example.demo.backend.userDatabaseSQL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        primaryStage.setTitle("JavaFX FXML Example");
        primaryStage.setScene(new Scene(root, 1536, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        Connection connection= userDatabaseConnect.connect();
        TextToSpeech.processTextToSpeech("Fuck you all");
//        userDatabaseSQL.createtable(connection);
//        userDatabaseSQL.insertIntoDict(connection,"name1","pass1",1);
//        userDatabaseSQL.showAll(connection);
        launch(args);
    }
}