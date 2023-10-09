package com.example.demo.frontend.libChildFrontEnd;

import com.example.demo.backend.*;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;

public class libChildController {
    @FXML
    private Button FolderBtn;

    @FXML

    private Button ListBtn;

    @FXML

    private VBox movingAnimation;

    @FXML

    private TranslateTransition transition;

    @FXML

    private Button showAllBtn;

    @FXML

    private Label firstName;

    @FXML

    private Label secondName;

    public void initialize() {
        transition = new TranslateTransition(Duration.seconds(0.5),movingAnimation);

        double FolderPos=0;

        double ListPos=178;

        FolderBtn.setOnAction(e->{
            transition.setToX(FolderPos);
            transition.play();
            FolderBtn.getStyleClass().add("active");
            ListBtn.getStyleClass().remove("active");
            showAllBtn.setText("View All Folder");
            firstName.setText("Folder Name");
            secondName.setText("Folder Name");
        });

        ListBtn.setOnAction(e->{
            transition.setToX(ListPos);
            transition.play();
            FolderBtn.getStyleClass().remove("active");
            ListBtn.getStyleClass().add("active");
            showAllBtn.setText("View All List");
            firstName.setText("List Name");
            secondName.setText("List Name");
        });


    }

}
