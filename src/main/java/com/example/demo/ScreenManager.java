package com.example.demo;

import com.example.demo.frontend.DictionaryFrontEnd.SearchBarController;
import com.example.demo.frontend.DictionaryFrontEnd.StandardWord;
import com.example.demo.frontend.DictionaryFrontEnd.DictionarySceneChanger;
import com.example.demo.frontend.SettingsFrontEnd.SettingsTabController;
import com.example.demo.frontend.navBarFrontEnd.NavbarController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ScreenManager {
    private static ScreenManager instance;
    private Stage stage;
    private StackPane root;

    private NavbarController navbarController;

    public void setNavbarController(FXMLLoader loader) {
        this.navbarController = loader.getController();
    }

    public NavbarController getNavbarController()
    {
        return this.navbarController;
    }
    private int folderId;

    private int listId;

    private int userId;

    private long loginTime = 0;

    private LocalDate loginDate;

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        this.loginDate = loginDate;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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


    private String listName;

    public String getListName() {
        return listName;
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
    private ScreenManager() {
        root = new StackPane();
        root.setAlignment(Pos.TOP_CENTER);
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
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane navBarPane = navBar.load();
            StackPane screen = learner.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().set(0, screen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(screen);
                root.getChildren().add(navBarPane);
                setNavbarController(navBar);
            }
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToDict() {
        try {
            FXMLLoader dict = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/DictionaryFrontEnd/dictionary_home.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane navBarPane = navBar.load();
            StackPane dictScreen = dict.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().set(0, dictScreen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(dictScreen);
                root.getChildren().add(navBarPane);
                setNavbarController(navBar);
            }
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToFolder() {
        try {
            FXMLLoader folder = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/folder.fxml"));
            StackPane screen = folder.load();
            root.getChildren().set(0, screen);
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToList(int folderId) {
        try {
            FXMLLoader list = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/listOfFolder.fxml"));
            this.folderId = folderId;
            StackPane screen = list.load();
            root.getChildren().set(0, screen);
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWord(int listId) {
        try {
            FXMLLoader listWord = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/wordOfList.fxml"));
            this.listId = listId;
            StackPane screen = listWord.load();
            root.getChildren().set(0, screen);
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchToGame() {
        try {
            FXMLLoader game = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/FlipGameFrontEnd/SelectGame.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane navBarPane = navBar.load();
            StackPane gameScreen = game.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().set(0, gameScreen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(gameScreen);
                root.getChildren().add(navBarPane);
                setNavbarController(navBar);
            }
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToTranslate() {
        try {
            FXMLLoader translate = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/Translator/translator.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane navBarPane = navBar.load();
            StackPane translateScreen = translate.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().set(0, translateScreen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(translateScreen);
                root.getChildren().add(navBarPane);
                setNavbarController(navBar);
            }
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToProfile() {
        try {
            FXMLLoader dict = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/ProfileFrontEnd/profile.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane navBarPane = navBar.load();
            StackPane dictScreen = dict.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().set(0, dictScreen);
            } else {
                root.getChildren().clear();
                root.getChildren().add(dictScreen);
                root.getChildren().add(navBarPane);
                setNavbarController(navBar);
            }
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWordDisplay(StandardWord word) {
        try {
            switchToDict();
            DictionarySceneChanger.Instance().switchToWordDisplay(new SearchBarController(), word);
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSettings() {
        try {
            FXMLLoader settings = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/SettingsFrontEnd/SettingsFrontEnd.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane navBarPane = navBar.load();
            StackPane settingsScreen = settings.load();
            if (root.getChildren().size() >= 2) {
                root.getChildren().set(0, settingsScreen);
                SettingsTabController stc = settings.getController();
                stc.setNavbarController(navbarController);
            } else {
                root.getChildren().clear();
                root.getChildren().add(settingsScreen);
                root.getChildren().add(navBarPane);
                setNavbarController(navBar);
                SettingsTabController stc = settings.getController();
                stc.setNavbarController(navbarController);
            }
            navbarController.resetPopupWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
