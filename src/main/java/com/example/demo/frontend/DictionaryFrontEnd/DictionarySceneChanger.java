package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.ScreenManager;
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

public class DictionarySceneChanger {
    private static DictionarySceneChanger _instance = null;

    private DictionarySceneChanger() {
    }

    public static DictionarySceneChanger Instance() {
        if (_instance == null) {
            _instance = new DictionarySceneChanger();
        }
        return _instance;
    }

    private Stage stage;
    private Scene scene;
    private StackPane screen;

    protected void switchToHomeScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dictionary_home.fxml"));
        screen = loader.load();
        StackPane root = ScreenManager.getInstance().getRoot();

        root.getChildren().set(0, screen);
        ScreenManager.getInstance().applyFadeInEffect((StackPane) root.getChildren().get(0));
        ScreenManager.getInstance().getNavbarController().resetPopupWindow();
    }

    protected void switchToWordNotExistScreen(SearchBarController searchBarController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("word_not_exist.fxml"));
        screen = loader.load();

        WordNotExistScreenController wordNotExistScreenController = loader.getController();
        wordNotExistScreenController.setSearchBarController(searchBarController);

        StackPane root = ScreenManager.getInstance().getRoot();
        root.getChildren().set(0, screen);
        ScreenManager.getInstance().applyFadeInEffect((StackPane) root.getChildren().get(0));
        ScreenManager.getInstance().getNavbarController().resetPopupWindow();
    }

    public void switchToWordDisplay(SearchBarController searchBarController, StandardWord word) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("word_display.fxml"));
        screen = loader.load();

        WordDisplayController wordDisplayController = loader.getController();
        wordDisplayController.setWord(word);

        wordDisplayController.setSearchBar(searchBarController);

        wordDisplayController.setContent();

        DictionaryIntegration.Instance().updateRecentSearches(word.getWord(), ScreenManager.getInstance().getUserId());

        StackPane root = ScreenManager.getInstance().getRoot();

        root.getChildren().set(0, screen);
        ScreenManager.getInstance().applyFadeInEffect((StackPane) root.getChildren().get(0));
        ScreenManager.getInstance().getNavbarController().resetPopupWindow();
    }
}
