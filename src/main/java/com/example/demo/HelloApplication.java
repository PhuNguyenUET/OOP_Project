package com.example.demo;

import com.example.demo.backend.LearnerBackend.FolderReposity;
import com.example.demo.backend.LearnerBackend.ListReposity;
import com.example.demo.frontend.LearnerFrontEnd.Folder;
import com.example.demo.frontend.LearnerFrontEnd.FolderManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ScreenManager.getInstance().setStage(primaryStage);
        primaryStage.setTitle("My JavaFX App");
        ScreenManager.getInstance().switchToLearner();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
//        List<Folder> folders = FolderManager.getIntance().updateAndGetListFolder();
//        for (Folder folder : folders)
//        {
//            FolderReposity.getInstance().addRecentFolder(folder.getId());
//        }
    }


}