package com.example.demo.frontend.navBarFrontEnd;

import com.example.demo.ScreenManager;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NavbarController {
    @FXML
    private Button Dictionary;

    @FXML

    private Button Learner;

    @FXML

    private Button Translate;

    @FXML

    private Button Game;

    @FXML

    private TranslateTransition transition;

    @FXML

    private Rectangle movingRec;

    public Button getDictionary() {
        return Dictionary;
    }

    public Button getLearner() {
        return Learner;
    }

    public Button getTranslate() {
        return Translate;
    }

    public Button getGame() {
        return Game;
    }

    private List<Button> list = new ArrayList<>();

    public List<Button> getList() {
        return list;
    }

    double DictionaryPos = 58;

    double LeanerPos = DictionaryPos + 170;

    double TranslatePos = LeanerPos + 170;

    double GamePos = TranslatePos + 170;
    public void initialize() {

        Dictionary.setOnAction(e -> {
            /*transition.setToX(DictionaryPos);
            transition.play();*/
//            movingAnimation(transition, DictionaryPos);
            handleActive(Dictionary);
            ScreenManager.getInstance().switchToDict();
        });

        Learner.setOnAction(e -> {
//            movingAnimation(transition, LeanerPos);
            handleActive(Learner);
            ScreenManager.getInstance().switchToLearner();
        });

        Translate.setOnAction(e -> {
//            movingAnimation(transition, TranslatePos);
            handleActive(Translate);
            ScreenManager.getInstance().switchToTranslate();
        });

        Game.setOnAction(e -> {
//            movingAnimation(transition, GamePos);
            handleActive(Game);
            ScreenManager.getInstance().switchToGame();
        });
    }

    public void movingAnimation(TranslateTransition transition, double pos) {
        transition = new TranslateTransition(Duration.seconds(0.5), movingRec);
        transition.setToX(pos);
        transition.play();
    }

    public void handleActive(Button button) {
        if (button.getText().equals(Dictionary.getText())) {
            if (!Dictionary.getStyleClass().contains("active")) {
                Dictionary.getStyleClass().add("active");
            }
            movingAnimation(transition, DictionaryPos);
        } else {
            if (Dictionary.getStyleClass().contains("active")) {
                Dictionary.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Learner.getText())) {
            if (!Learner.getStyleClass().contains("active")) {
                Learner.getStyleClass().add("active");
            }
            movingAnimation(transition, LeanerPos);

        } else {
            if (Learner.getStyleClass().contains("active")) {
                Learner.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Translate.getText())) {
            if (!Translate.getStyleClass().contains("active")) {
                Translate.getStyleClass().add("active");
            }
            movingAnimation(transition, TranslatePos);

        } else {
            if (Translate.getStyleClass().contains("active")) {
                Translate.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Game.getText())) {
            if (!Game.getStyleClass().contains("active")) {
                Game.getStyleClass().add("active");
            }
            movingAnimation(transition, GamePos);

        } else {
            if (Game.getStyleClass().contains("active")) {
                Game.getStyleClass().remove("active");
            }
        }
    }

}
