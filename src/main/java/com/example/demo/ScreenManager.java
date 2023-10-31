package com.example.demo;

import com.example.demo.frontend.LearnerFrontEnd.ListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ScreenManager {
    private static ScreenManager instance;
    private Stage stage;
    private StackPane root;

    private String folderName;

    private int folderId;

    private int listId;

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    private String listName;

    public String getListName() {
        return listName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    private Scene scene;

    public Stage getStage() {
        return stage;
    }

    public StackPane getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

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
        scene = new Scene(root, 1520, 780);
        stage.setScene(scene);
//        stage.setMinWidth(1536);
//        stage.setMinHeight(800);
//        stage.setMaximized(true);
        stage.setResizable(false);
    }

    public void switchToLogin() {
        try {
            FXMLLoader login = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LoginFrontEnd/login.fxml"));
            StackPane screen = login.load();
            root.getChildren().clear();
            root.getChildren().add(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToLearner() {
        try {
            FXMLLoader learner = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/Learner.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("navBar.fxml"));
            StackPane screen = learner.load();
            StackPane navBarPane = navBar.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().remove(1);
                root.getChildren().add(screen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(navBarPane);
                root.getChildren().add(screen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToDict() {
        try {
            FXMLLoader dict = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/DictionaryFrontEnd/dictionary_home.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("com/example/demo/frontend/NavbarFrontEnd/navBar.fxml"));
            StackPane dictScreen = dict.load();
            StackPane navBarPane = navBar.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().remove(1);
                root.getChildren().add(dictScreen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(navBarPane);
                root.getChildren().add(dictScreen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToFolder() {
        try {
            FXMLLoader folder = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/folder.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/NavbarFrontEnd/navBar.fxml"));
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


    public void switchToList(String folderName) {
        try {
            FXMLLoader list = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/listOfFolder.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/NavbarFrontEnd/navBar.fxml"));
            this.folderName = folderName;
            StackPane screen = list.load();
            StackPane navBarPane = navBar.load();
            root.getChildren().remove(1);
            root.getChildren().add(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToList(int folderId) {
        try {
            FXMLLoader list = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/listOfFolder.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/NavbarFrontEnd/navBar.fxml"));
            this.folderId = folderId;
            StackPane screen = list.load();
            StackPane navBarPane = navBar.load();
            root.getChildren().remove(1);
            root.getChildren().add(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWord(int listId) {
        try {
            FXMLLoader listWord = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/wordOfList.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/NavbarFrontEnd/navBar.fxml"));
            this.listId = listId;
            StackPane screen = listWord.load();
            StackPane navBarPane = navBar.load();
            root.getChildren().remove(1);
            root.getChildren().add(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void switchToWordTmp() {
//        try {
//            FXMLLoader navBar = new FXMLLoader(getClass().getResource("navBar.fxml"));
//            FXMLLoader listWord = new FXMLLoader(getClass().getResource("wordOfList.fxml"));
//            this.listName = "LIST 1";
//            StackPane navBarPane = navBar.load();
//            StackPane screen = listWord.load();
//            root.getChildren().add(navBarPane);
//            root.getChildren().add(screen);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
