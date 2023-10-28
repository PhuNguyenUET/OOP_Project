package com.example.demo.frontend.MultipleChoice;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScreenChanger {
    private static GameScreenChanger _instance = null;

    private GameScreenChanger() {
    }

    protected static GameScreenChanger Instance() {
        if (_instance == null) {
            _instance = new GameScreenChanger();
        }
        return _instance;
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    protected void switchToDifficultyScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("select-difficulty-screen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected void switchToHomeScreen(Event event, String difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("multiple-choice-game-screen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected void switchToResultScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("result-screen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
