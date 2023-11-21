package com.example.demo.frontend.CanvasFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.frontend.navBarFrontEnd.NavbarController;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class CanvasController {
    @FXML
    private Canvas canvas;

    private final double WIDTH = 1520;
    private final double HEIGHT = 780;

    private GraphicsContext gc;
    private Snowflake[] snowflakes;

    public void initialize() {
        canvas.getStyleClass().add("canvasTest");
        gc = canvas.getGraphicsContext2D();
        canvas.setMouseTransparent(true);
        snowflakes = new Snowflake[100];
        for (int i = 0; i < 100; i++) {
            snowflakes[i] = new Snowflake();
        }

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                    updateSnowflakes();
                    drawSnowflakes();
//                    System.out.println("tuyết rơi");
            }
        }.start();
    }

    public void updateSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.update();
        }
    }

    public void drawSnowflakes() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.WHITE);
        for (Snowflake snowflake : snowflakes) {
            gc.fillOval(snowflake.getX(), snowflake.getY(), snowflake.getSize(), snowflake.getSize());
        }
    }


    public class Snowflake {
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
