package com.example.demo;

import com.example.demo.backend.ProfileBackend.ProfileRepo;
import com.example.demo.frontend.Common.MusicManager;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    ProfileRepo profileRepo = new ProfileRepo();

//    private static final int WIDTH = 1520;
//    private static final int HEIGHT = 780;
//    private static final int NUM_SNOWFLAKES = 100;
//
//    private Canvas canvas;
//    private GraphicsContext gc;
//    private Snowflake[] snowflakes;

    @Override
    public void start(Stage primaryStage) throws IOException {
        ScreenManager.getInstance().setStage(primaryStage);
        primaryStage.setTitle("My JavaFX App");
        MusicManager.getInstance().play2("/com/example/demo/music/song.mp3");
        ScreenManager.getInstance().switchToLogin();
        primaryStage.setOnCloseRequest(e -> {
            long timeUsage = System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime();
            double timeHour = timeUsage * 1.0 / (60 * 1000 * 1.0);
            System.out.println("Thời gian phiên vừa rồi là: " + timeHour + " minutes");
            if (ScreenManager.getInstance().getLoginDate() != null) {
                if (profileRepo.checkDateIsExist(ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId())) {
                    double lastTime = profileRepo.getRecentTimeUsageOfDate(ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
                    double newTime = lastTime + timeHour;
                    profileRepo.updateTimeForDate(newTime, ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
                } else {
                    profileRepo.insertToSession(timeHour, ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
                }
            }
        });
//        System.out.println((System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime())/60000);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
