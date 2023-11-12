package com.example.demo.frontend.FlipGameFrontEnd;

import com.example.demo.ScreenManager;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;

public class PlayerInfoController {
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private TextField player1Input;

    @FXML
    private TextField player2Input;

    @FXML
    private HBox toastMes;

    @FXML
    public ImageView toastIcon;

    @FXML

    private Image toastImg;

    @FXML
    private Label textMessage;

    @FXML
    private Label textMessageDes;

    @FXML
    private TranslateTransition toastMesTransition;

    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));


    public void initialize() {
        btn1.setOnAction(e -> {
            String player1Name = player1Input.getText().trim();
            String player2Name = player2Input.getText().trim();
            if (player1Name.length() < 2 && player2Name.length() < 2) {
                toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
                URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                Image image = new Image(imageUrl.toString());
                textMessage.setText("Invalid");
                textMessageDes.setText("The name must be more than 2 characters.");
                toastIcon.setImage(image);
                toastMesTransition.setToX(-28);
                toastMesTransition.play();
                pauseTransition.play();
            } else {
                GameScreenChanger.getIntance().setPlayer1Name(player1Name);
                GameScreenChanger.getIntance().setPlayer2Name(player2Name);
                GameScreenChanger.getIntance().switchToSelectTopic();
            }
        });

        btn2.setOnAction(e -> {
            ScreenManager.getInstance().switchToGame();
        });

        pauseTransition.setOnFinished(e -> {
            toastMesTransition.setToX(360);
            toastMesTransition.play();
        });

        player2Input.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                btn1.fire();
            }
        });

    }
}
