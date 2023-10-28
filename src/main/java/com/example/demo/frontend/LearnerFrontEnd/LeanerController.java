package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.TextToSpeech;
//import com.example.demo.frontend.libChildFrontEnd.libChildController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    @FXML

    private VBox libChildContainer;

    @FXML
    private HBox rencentContainer;

    public void initialize() {
        System.out.println(paneContainer);
        transition1 = new TranslateTransition(Duration.seconds(0.5), movingAnimation);

        double FolderPos = 0;

        double ListPos = 178;

        FolderBtn.setOnAction(e -> {
            transition1.setToX(FolderPos);
            transition1.play();
            FolderBtn.getStyleClass().add("active");
            ListBtn.getStyleClass().remove("active");
            showAllBtn.setText("View All Folder");
            firstName.setText("Folder Name");
            secondName.setText("Folder Name");
        });

        ListBtn.setOnAction(e -> {
            transition1.setToX(ListPos);
            transition1.play();
            FolderBtn.getStyleClass().remove("active");
            ListBtn.getStyleClass().add("active");
            showAllBtn.setText("View All List");
            firstName.setText("List Name");
            secondName.setText("List Name");
        });

        yourLib.setOnAction(e -> {
            paneContainer.setVisible(!paneContainer.isVisible());
            paneContainer.toFront();

        });

        yourLibContain.setOnMouseClicked(e -> {
            paneContainer.setVisible(!paneContainer.isVisible());
            paneContainer.toFront();
        });


        Word1Container.setOnMouseClicked(e -> {
            e.consume();
            PopUpContainer.setVisible(true);
            System.out.println("Word1 click");
            WordPopUp.setText(Word1Text.getText());
        });

        Word2Container.setOnMouseClicked(e -> {
            e.consume();
            PopUpContainer.setVisible(true);
            System.out.println("Word2 click");
            WordPopUp.setText(Word2Text.getText());
        });

        Word3Container.setOnMouseClicked(e -> {
            e.consume();
            PopUpContainer.setVisible(true);
            System.out.println("Word3 click");
            WordPopUp.setText(Word3Text.getText());
        });

        closePopUp.setOnMouseClicked(e -> {
            System.out.println("Close");
            System.out.println(PopUpContainer.isVisible());
            PopUpContainer.setVisible(false);
            System.out.println(PopUpContainer.isVisible());
        });

        showAllBtn.setOnAction(e -> {
            e.consume();
            if (showAllBtn.getText().equals("View All Folder")) {
                System.out.println("Change to Folder");
                ScreenManager.getInstance().switchToFolder();
            }
        });

        ScreenManager.getInstance().getScene().setOnMouseClicked(e -> {
            paneContainer.setVisible(false);
            PopUpContainer.setVisible(false);
        });

        PopUpContainer.setOnMouseClicked(e -> {
            e.consume();
        });

        libChildContainer.setOnMouseClicked(e -> {
            e.consume();
        });

        folderRender();
    }

    public void movingAnimation(TranslateTransition transition, double pos) {
        transition.setToX(pos);
        transition.play();
    }

    public void recentWordRender()
    {

    }

    public void folderRender()
    {
//            List<UserWord> listWord = new ArrayList<>();
//            listWord.add(new UserWord("Hello","v","Xin chào","helo"));
//            listWord.add(new UserWord("Vehicle","n","Phương tiện","helo"));
//            listWord.add(new UserWord("Morning","n","Buổi sáng","helo"));
//            for (int i=0;i<2;i++) {
//                String word = listWord.get(i).getWord();
//                VBox container = new VBox();
//                container.setSpacing(15);
//                container.getStyleClass().add("wordContainer");
//                container.setTranslateY(36);
//                VBox engWordContainer = new VBox();
//                Label engWord = new Label(listWord.get(i).getWord());
//                engWord.getStyleClass().add("engWord");
//                engWordContainer.getChildren().add(engWord);
//                VBox typeContainer = new VBox();
//                Label type = new Label(listWord.get(i).getType());
//                type.getStyleClass().add("type");
//                typeContainer.getChildren().add(type);
//                HBox audio = new HBox();
//                audio.setSpacing(15);
//                VBox imgContainer = new VBox();
//                String imagePath = "/com/example/demo/assets/audio.png";
//                URL imageUrl = getClass().getResource(imagePath);
//                ImageView imageView = new ImageView(new Image(imageUrl.toString()));
//                imgContainer.getChildren().add(imageView);
//                VBox pronunContainer = new VBox();
//                Label pronunciation = new Label(listWord.get(i).getPronunciation());
//                pronunciation.getStyleClass().add("pronunciation");
//                pronunContainer.getChildren().add(pronunciation);
//                audio.getChildren().add(imgContainer);
//                audio.getChildren().add(pronunContainer);
//                VBox definitionContainer= new VBox();
//                Label difinition = new Label(listWord.get(i).getDefinition());
//                difinition.getStyleClass().add("description");
//                definitionContainer.getChildren().add(difinition);
//                container.getChildren().addAll(engWordContainer,typeContainer,audio,definitionContainer);
//                rencentContainer.getChildren().add(container);
//
//                imgContainer.setOnMouseClicked(e->{
//                    e.consume();
//                    TextToSpeech.processTextToSpeech(word);
//                });
//
//                container.setOnMouseClicked(e->{
//                    System.out.println("Chuyen huong");
//                });
//            }
    }

}
