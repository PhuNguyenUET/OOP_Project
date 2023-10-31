package com.example.demo.frontend.navBarFrontEnd;

import com.example.demo.ScreenManager;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    public void initialize() {
        transition = new TranslateTransition(Duration.seconds(0.5), movingRec);

        double DictionaryPos = 48;

        double LeanerPos = DictionaryPos + 130;

        double TranslatePos = LeanerPos + 126;

        double GamePos = TranslatePos + 120;

        Dictionary.setOnAction(e -> {
            /*transition.setToX(DictionaryPos);
            transition.play();*/
            movingAnimation(transition,DictionaryPos);
            handleActive(Dictionary);

        });

        Learner.setOnAction(e->{
            movingAnimation(transition,LeanerPos);
            handleActive(Learner);


            ScreenManager.getInstance().switchToLearner();
        });

        Translate.setOnAction(e->{
            movingAnimation(transition,TranslatePos);
            handleActive(Translate);
        });

        Game.setOnAction(e->{
            movingAnimation(transition,GamePos);
            handleActive(Game);
        });
    }

    public void movingAnimation(TranslateTransition transition,double pos)
    {
        transition.setToX(pos);
        transition.play();
    }

    public void handleActive (Button button)
    {
        if (button.getText().equals(Dictionary.getText()))
        {
            if (!Dictionary.getStyleClass().contains("active"))
            {
                Dictionary.getStyleClass().add("active");
            }
        }
        else
        {
            if (Dictionary.getStyleClass().contains("active"))
            {
                Dictionary.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Learner.getText()))
        {
            if (!Learner.getStyleClass().contains("active"))
            {
                Learner.getStyleClass().add("active");
            }
        }
        else
        {
            if (Learner.getStyleClass().contains("active"))
            {
                Learner.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Translate.getText()))
        {
            if (!Translate.getStyleClass().contains("active"))
            {
                Translate.getStyleClass().add("active");
            }
        }
        else
        {
            if (Translate.getStyleClass().contains("active"))
            {
                Translate.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Game.getText()))
        {
            if (!Game.getStyleClass().contains("active"))
            {
                Game.getStyleClass().add("active");
            }
        }
        else
        {
            if (Game.getStyleClass().contains("active"))
            {
                Game.getStyleClass().remove("active");
            }
        }
    }

}
