package com.example.demo.frontend.MultipleChoice;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SelectDifficultyController {
    @FXML
    Button easyButton;
    @FXML
    Button mediumButton;
    @FXML
    Button hardButton;

    @FXML
    public void easyDifficulty(Event event) {
        try {
            GameScreenChanger.Instance().switchToGameScreen(event, easyButton.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void meidumDifficulty(Event event) {
        try {
            GameScreenChanger.Instance().switchToGameScreen(event, mediumButton.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hardDifficulty(Event event) {
        try {
            GameScreenChanger.Instance().switchToGameScreen(event, hardButton.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void returnToGameSelect(Event event) {

    }
}
