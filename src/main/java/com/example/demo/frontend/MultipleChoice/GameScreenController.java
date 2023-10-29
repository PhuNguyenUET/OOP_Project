package com.example.demo.frontend.MultipleChoice;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
    @FXML
    Label questionLabel;
    @FXML
    Label difficultyLabel;
    @FXML
    Label scoreLabel;
    @FXML
    Label quesNumberLabel;
    @FXML
    Button option1;
    @FXML
    Button option2;
    @FXML
    Button option3;
    @FXML
    Button option4;
    @FXML
    Button next;
    @FXML
    ProgressBar timer;

    int currentChoice;
    private Difficulty difficulty;
    private Game game;
    Timeline timeline;

    int waitTime;

    public void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy" -> {
                this.difficulty = Difficulty.Easy;
                waitTime = 30;
            }
            case "Medium" -> {
                this.difficulty = Difficulty.Medium;
                waitTime = 20;
            }
            case "Hard" -> {
                this.difficulty = Difficulty.Hard;
                waitTime = 15;
            }
        }

        game = new Game(this.difficulty);
        showQuestion();
    }

    private void showQuestion() {
        Question q = game.getCurrentQuestion();
        if (q == null) {
            return;
        }
        difficultyLabel.setText("Difficulty: " + q.getDifficulty());
        quesNumberLabel.setText("Question " + game.getQuesIndex() + "/" + game.getTotalQuestion());
        questionLabel.setText(q.getQuestion());
        List<String> options = q.getOptions();
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(timer.progressProperty(), 1)),
                new KeyFrame(Duration.seconds(waitTime), e-> {
                    timeOut();
                }, new KeyValue(timer.progressProperty(), 0))
        );
        timeline.play();
    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    private void checkAnswer(Button button) {
        boolean check = game.checkAnswer(currentChoice);
        scoreLabel.setText("Score: " + game.getScore());
        if (check) {
            button.getStyleClass().add("correctAnswer");
        } else {
            button.getStyleClass().add("wrongAnswer");
        }
        if(game.isOver()) {
            next.setText("Show result");
        }
        next.setVisible(true);
        next.setDisable(false);
    }

    private void timeOut() {
        option1.setDisable(true);
        option2.setDisable(true);
        option3.setDisable(true);
        option4.setDisable(true);
        if(game.isOver()) {
            next.setText("Show result");
        }
        next.setVisible(true);
        next.setDisable(false);
    }

    private void resetDefaultButton (Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("choiceButton");
        button.setOpacity(1);
        button.setDisable(false);
    }

    @FXML
    public void button1Handler() {
        currentChoice = 1;
        option1.setDisable(true);
        option1.setOpacity(1);
        option2.setDisable(true);
        option2.setOpacity(0.5);
        option3.setDisable(true);
        option3.setOpacity(0.5);
        option4.setDisable(true);
        option4.setOpacity(0.5);
        timeline.stop();
        delay(500, () -> checkAnswer(option1));
    }

    @FXML
    public void button2Handler(Event event) {
        currentChoice = 2;
        option1.setDisable(true);
        option1.setOpacity(0.5);
        option2.setDisable(true);
        option2.setOpacity(1);
        option3.setDisable(true);
        option3.setOpacity(0.5);
        option4.setDisable(true);
        option4.setOpacity(0.5);
        timeline.stop();
        delay(500, () -> checkAnswer(option2));
    }

    @FXML
    public void button3Handler(Event event) {
        currentChoice = 3;
        option1.setDisable(true);
        option1.setOpacity(0.5);
        option2.setDisable(true);
        option2.setOpacity(0.5);
        option3.setDisable(true);
        option3.setOpacity(1);
        option4.setDisable(true);
        option4.setOpacity(0.5);
        timeline.stop();
        delay(500, () -> checkAnswer(option3));
    }

    @FXML
    public void button4Handler(Event event) {
        currentChoice = 4;
        option1.setDisable(true);
        option1.setOpacity(0.5);
        option2.setDisable(true);
        option2.setOpacity(0.5);
        option3.setDisable(true);
        option3.setOpacity(0.5);
        option4.setDisable(true);
        option4.setOpacity(1);
        timeline.stop();
        delay(500, () -> checkAnswer(option4));
    }

    @FXML
    public void nextQuestion(Event event) {
        if(game.isOver()) {
            try {
                GameScreenChanger.Instance().switchToResultScreen(event, game.getScore());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resetDefaultButton(option1);
        resetDefaultButton(option2);
        resetDefaultButton(option3);
        resetDefaultButton(option4);
        game.nextQuestion();
        showQuestion();
        next.setDisable(true);
        next.setVisible(false);
    }

    @FXML
    public void onHover1(Event event) {
        option1.getStyleClass().add("onHover");
    }

    @FXML
    public void onHover2(Event event) {
        option2.getStyleClass().add("onHover");
    }

    @FXML
    public void onHover3(Event event) {
        option3.getStyleClass().add("onHover");
    }

    @FXML
    public void onHover4(Event event) {
        option4.getStyleClass().add("onHover");
    }

    @FXML
    public void outOfHover(Event event) {
        option1.getStyleClass().remove("onHover");
        option2.getStyleClass().remove("onHover");
        option3.getStyleClass().remove("onHover");
        option4.getStyleClass().remove("onHover");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        next.setVisible(false);
        next.setDisable(true);
    }
}
