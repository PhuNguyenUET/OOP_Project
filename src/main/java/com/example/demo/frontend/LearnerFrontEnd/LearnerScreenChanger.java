package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LearnerScreenChanger {
    public void switchToFolder() {
        try {
            FXMLLoader folder = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/folder.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            StackPane screen = folder.load();
            StackPane navBarPane = navBar.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, screen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToList(int folderId) {
        try {
            FXMLLoader list = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/listOfFolder.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            ScreenManager.getInstance().setFolderId(folderId);
            StackPane screen = list.load();
            StackPane navBarPane = navBar.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, screen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToWord(int listId) {
        try {
            FXMLLoader listWord = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/LearnerFrontEnd/wordOfList.fxml"));
            FXMLLoader navBar = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/navBarFrontEnd/navBar.fxml"));
            ScreenManager.getInstance().setListId(listId);
            StackPane screen = listWord.load();
            StackPane navBarPane = navBar.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, screen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
