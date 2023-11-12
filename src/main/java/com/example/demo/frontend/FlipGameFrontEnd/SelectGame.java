package com.example.demo.frontend.FlipGameFrontEnd;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class SelectGame {
    @FXML
    private VBox choice1;

    @FXML

    private VBox choice2;


    public void initialize() {
        choice1.setOnMouseClicked(e->{
            GameScreenChanger.getIntance().switchToGame1();
        });

        choice2.setOnMouseClicked(e->{
            GameScreenChanger.getIntance().switchToGame2();
        });
    }
}
