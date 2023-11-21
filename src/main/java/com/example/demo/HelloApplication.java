package com.example.demo;

import com.example.demo.backend.ProfileBackend.ProfileRepo;
import com.example.demo.frontend.navBarFrontEnd.NavbarController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    ProfileRepo profileRepo = new ProfileRepo();

    private static final int WIDTH = 1520;
    private static final int HEIGHT = 780;
    private static final int NUM_SNOWFLAKES = 100;

    private Canvas canvas;
    private GraphicsContext gc;
    private Snowflake[] snowflakes;

    @Override
    public void start(Stage primaryStage) throws IOException {
        ScreenManager.getInstance().setStage(primaryStage);
        primaryStage.setTitle("My JavaFX App");
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.setMouseTransparent(true);
        snowflakes = new Snowflake[NUM_SNOWFLAKES];
        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            snowflakes[i] = new Snowflake();
        }
        ScreenManager.getInstance().switchToLogin();
//        ScreenManager.getInstance().getRoot().getChildren().add(canvas);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateSnowflakes();
                drawSnowflakes();
            }
        }.start();
        primaryStage.setOnCloseRequest(e -> {
            long timeUsage = System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime();
            double timeHour = timeUsage * 1.0 / (60 * 1000 * 1.0);
            System.out.println("Thời gian phiên vừa rồi là: " + timeHour + " minutes");
            if (ScreenManager.getInstance().getLoginDate() != null)
                profileRepo.insertToSession(timeHour, ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
        });
//        System.out.println((System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime())/60000);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void updateSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.update();
        }
    }

    private void drawSnowflakes() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.WHITE);
        for (Snowflake snowflake : snowflakes) {
            gc.fillOval(snowflake.getX(), snowflake.getY(), snowflake.getSize(), snowflake.getSize());
        }
    }


    private static class Snowflake {
        private double x;
        private double y;
        private double size;
        private double speed;

        public Snowflake() {
            Random random = new Random();
            this.x = random.nextDouble() * WIDTH;
            this.y = random.nextDouble() * HEIGHT;
            this.size = random.nextDouble() * 10;
            this.speed = 1;
        }

        public void update() {
            y += speed;

            if (y > HEIGHT) {
                y = 0;
                x = Math.random() * WIDTH;
            }
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getSize() {
            return size;
        }
    }

}
