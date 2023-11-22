package com.example.demo.frontend.MultipleChoice;

import com.example.demo.ScreenManager;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
    private StackPane screen;

    protected void switchToDifficultyScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("select-difficulty-screen.fxml"));
        screen = loader.load();
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = stage.getScene();

        StackPane root = (StackPane) ScreenManager.getInstance().getRoot();
        root.getChildren().set(0, screen);
        ScreenManager.getInstance().applyFadeInEffect((StackPane) root.getChildren().get(0));
        ScreenManager.getInstance().getNavbarController().resetPopupWindow();
//        stage.show();
    }

    protected void switchToGameScreen(Event event, String difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("multiple-choice-game-screen.fxml"));
        screen = loader.load();

        GameScreenController game = loader.getController();
        game.setDifficulty(difficulty);

        StackPane root = (StackPane) ScreenManager.getInstance().getRoot();
        root.getChildren().set(0, screen);
        ScreenManager.getInstance().applyFadeInEffect((StackPane) root.getChildren().get(0));
        ScreenManager.getInstance().getNavbarController().resetPopupWindow();
    }

    protected void switchToResultScreen(Event event, int score) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("result-screen.fxml"));
        screen = loader.load();

        ResultController resultController = loader.getController();
        resultController.setScore(score);

        StackPane root = (StackPane) ScreenManager.getInstance().getRoot();
        root.getChildren().set(0, screen);
        ScreenManager.getInstance().applyFadeInEffect((StackPane) root.getChildren().get(0));
        ScreenManager.getInstance().getNavbarController().resetPopupWindow();
    }
}
