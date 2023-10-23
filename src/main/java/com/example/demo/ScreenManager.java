package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenManager {
    private static ScreenManager instance;
    private Stage stage;
    private StackPane root;

//    FXMLLoader login;
//
//    FXMLLoader learner;
//
//    FXMLLoader navBar;
//
//    FXMLLoader folderUI;
//
//    public FXMLLoader getLogin() {
//        return login;
//    }
//
//    public FXMLLoader getLearner() {
//        return learner;
//    }
//
//    public FXMLLoader getNavBar() {
//        return navBar;
//    }
//
//    public FXMLLoader getFolderUI() {
//        return folderUI;
//    }
    private ScreenManager() {
//        learner = new FXMLLoader(getClass().getResource("Learner.fxml"));
//        login = new FXMLLoader(getClass().getResource("login.fxml"));
        root = new StackPane();
//        navBar = new FXMLLoader(getClass().getResource("navBar.fxml"));
//        folderUI = new FXMLLoader(getClass().getResource("folder.fxml"));
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(root, 1536, 800);
        stage.setScene(scene);
        stage.setMinWidth(1536);
        stage.setMinHeight(800);
        stage.setMaximized(true);
    }

    public void switchToLogin() {
        try {
            FXMLLoader login = new FXMLLoader(getClass().getResource("login.fxml"));
            StackPane screen = login.load();
            root.getChildren().clear();
            root.getChildren().add(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToLearner() {
        try {
            FXMLLoader learner = new FXMLLoader(getClass().getResource("Learner.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("navBar.fxml"));
            StackPane screen = learner.load();
            StackPane navBarPane = navBar.load();
            if(root.getChildren().contains(navBarPane))
            {
                root.getChildren().remove(1);
                root.getChildren().add(screen);
            }
            else {
                root.getChildren().clear();
                root.getChildren().add(navBarPane);
                root.getChildren().add(screen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToFolder() {
        try {
            FXMLLoader folder = new FXMLLoader(getClass().getResource("folder.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("navBar.fxml"));
            StackPane screen = folder.load();
            StackPane navBarPane = navBar.load();
//            root.getChildren().clear();
//            root.getChildren().add(navBarPane);
            root.getChildren().remove(1);
            root.getChildren().add(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
