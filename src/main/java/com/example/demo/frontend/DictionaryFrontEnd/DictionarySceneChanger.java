package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

class DictionarySceneChanger {
    private static DictionarySceneChanger _instance = null;

    private DictionarySceneChanger() {
    }

    protected static DictionarySceneChanger Instance() {
        if (_instance == null) {
            _instance = new DictionarySceneChanger();
        }
        return _instance;
    }
    private Stage stage;
    private Scene scene;
    private StackPane screen;
    protected void switchToHomeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dictionary_home.fxml"));
        screen = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = stage.getScene();
        StackPane stackPane = (StackPane) scene.getRoot();
        stackPane.getChildren().clear();
        stackPane.getChildren().add(screen);
        stage.show();
    }

    protected void switchToWordNotExistScreen(Event event, SearchBarController searchBarController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("word_not_exist.fxml"));
        screen = loader.load();

        WordNotExistScreenController wordNotExistScreenController = loader.getController();
        wordNotExistScreenController.setSearchBarController(searchBarController);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = stage.getScene();
        StackPane stackPane = (StackPane) scene.getRoot();
        stackPane.getChildren().clear();
        stackPane.getChildren().add(screen);
        stage.show();
    }

    protected void switchToWordDisplay(Event event, SearchBarController searchBarController, StandardWord word) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("word_display.fxml"));
        screen = loader.load();

        WordDisplayController wordDisplayController = loader.getController();
        wordDisplayController.setWord(word);

        wordDisplayController.setSearchBar(searchBarController);

        wordDisplayController.setContent();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = stage.getScene();
        StackPane stackPane = (StackPane) scene.getRoot();
        stackPane.getChildren().clear();
        stackPane.getChildren().add(screen);
        stage.show();
    }
}
