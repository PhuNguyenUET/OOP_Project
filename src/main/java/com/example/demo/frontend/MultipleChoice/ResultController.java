package com.example.demo.frontend.MultipleChoice;

import com.example.demo.ScreenManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ResultController {
    @FXML
    Label scoreTag;
    int score;

    public void setScore(int score) {
        this.score = score;
        scoreTag.setText("Your score is: " + score);
    }

    @FXML
    public void replay(Event event) {
        try {
            GameScreenChanger.Instance().switchToDifficultyScreen(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeToGameHome(MouseEvent event) {
        ScreenManager.getInstance().switchToGame();
    }
}
