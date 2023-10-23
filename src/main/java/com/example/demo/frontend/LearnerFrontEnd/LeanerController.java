package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.frontend.LearnerFrontEnd.LeanerController;
//import com.example.demo.frontend.libChildFrontEnd.libChildController;
import com.example.demo.frontend.libChildFrontEnd.libChildController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class LeanerController {

    @FXML
    public Pane paneContainer;
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
    @FXML

    private Button yourLib;


    private TranslateTransition transition1;

    @FXML
    private HBox yourLibContain;

    @FXML
    private StackPane Word1Container;

    @FXML
    private StackPane Word2Container;

    @FXML
    private StackPane Word3Container;

    @FXML
    private Label Word1Text;

    @FXML
    private Label Word2Text;

    @FXML

    private Label Word3Text;

    @FXML

    private ImageView closePopUp;

    @FXML

    private Pane PopUpContainer;

    @FXML

    private Label WordPopUp;


    public void initialize() {
        System.out.println(paneContainer);
        transition1 = new TranslateTransition(Duration.seconds(0.5),movingAnimation);

        double FolderPos=0;

        double ListPos=178;

        FolderBtn.setOnAction(e->{
            transition1.setToX(FolderPos);
            transition1.play();
            FolderBtn.getStyleClass().add("active");
            ListBtn.getStyleClass().remove("active");
            showAllBtn.setText("View All Folder");
            firstName.setText("Folder Name");
            secondName.setText("Folder Name");
        });

        ListBtn.setOnAction(e->{
            transition1.setToX(ListPos);
            transition1.play();
            FolderBtn.getStyleClass().remove("active");
            ListBtn.getStyleClass().add("active");
            showAllBtn.setText("View All List");
            firstName.setText("List Name");
            secondName.setText("List Name");
        });

        yourLib.setOnAction(e->{
            paneContainer.setVisible(!paneContainer.isVisible());
            paneContainer.toFront();

        });

        yourLibContain.setOnMouseClicked(e->{
            paneContainer.setVisible(!paneContainer.isVisible());
            paneContainer.toFront();
        });


        Word1Container.setOnMouseClicked(e->{
            PopUpContainer.setVisible(true);
            System.out.println("Word1 click");
            WordPopUp.setText(Word1Text.getText());
        });

        Word2Container.setOnMouseClicked(e->{
            PopUpContainer.setVisible(true);
            System.out.println("Word2 click");
            WordPopUp.setText(Word2Text.getText());
        });

        Word3Container.setOnMouseClicked(e->{
            PopUpContainer.setVisible(true);
            System.out.println("Word3 click");
            WordPopUp.setText(Word3Text.getText());
        });

        closePopUp.setOnMouseClicked(e->{
            System.out.println("Close");
            System.out.println(PopUpContainer.isVisible());
            PopUpContainer.setVisible(false);
            System.out.println(PopUpContainer.isVisible());
        });

        showAllBtn.setOnAction(e -> {
            if(showAllBtn.getText().equals("View All Folder"))
            {
                System.out.println("Change to Folder");
                ScreenManager.getInstance().switchToFolder();
//                FXMLLoader folderUI=ScreenManager.getInstance().getFolderUI();
//                System.out.println(folderUI);
            }
        });

        System.out.println(PopUpContainer.isVisible());
    }

    public void movingAnimation(TranslateTransition transition,double pos)
    {
        transition.setToX(pos);
        transition.play();
    }

}
