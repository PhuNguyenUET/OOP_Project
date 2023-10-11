package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class DictionarySceneChanger {
    private static DictionarySceneChanger _instance = null;

    private DictionarySceneChanger() {
    }

    ;

    protected static DictionarySceneChanger Instance() {
        if (_instance == null) {
            _instance = new DictionarySceneChanger();
        }
        return _instance;
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    protected void switchToHomeScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dictionary_home.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected void switchToWordDisplay(ActionEvent event, StandardWord word) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("word_display.fxml"));
        root = loader.load();

        WordDisplayController wordDisplayController = loader.getController();
        wordDisplayController.setWord(word);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
