package com.example.demo;

import com.example.demo.backend.ProfileBackend.ProfileRepo;
import com.example.demo.frontend.navBarFrontEnd.NavbarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    ProfileRepo profileRepo = new ProfileRepo();
    @Override
    public void start(Stage primaryStage) throws IOException {
        ScreenManager.getInstance().setStage(primaryStage);
        primaryStage.setTitle("My JavaFX App");
        ScreenManager.getInstance().switchToLogin();
        primaryStage.setOnCloseRequest(e->{
            long timeUsage = System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime();
            double timeHour = timeUsage * 1.0 / (60*1000*1.0);
            System.out.println("Thời gian phiên vừa rồi là: " + timeHour + " minutes");
            profileRepo.insertToSession(timeHour,ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
        });
//        System.out.println((System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime())/60000);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
