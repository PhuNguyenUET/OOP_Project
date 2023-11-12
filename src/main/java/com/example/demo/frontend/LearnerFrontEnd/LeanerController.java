package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.ConnectComponentLearner;
import com.example.demo.backend.LearnerBackend.FolderManager;
import com.example.demo.backend.LearnerBackend.FolderReposity;
import com.example.demo.backend.LearnerBackend.WordManager;
import com.example.demo.backend.TextToSpeech;
//import com.example.demo.frontend.libChildFrontEnd.libChildController;
import com.example.demo.frontend.Common.DictFuncToLearner;
import com.example.demo.frontend.Common.Word;
import com.example.demo.frontend.DictionaryFrontEnd.ConnectComponentDict;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;

public class LeanerController {

    @FXML
    public Pane paneContainer;
    @FXML
    private Button FolderBtn;

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

//    @FXML
//    private StackPane Word1Container;
//
//    @FXML
//    private StackPane Word2Container;
//
//    @FXML
//    private StackPane Word3Container;

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
    private Label TypePopUp;

    @FXML
    private Label PronunciationPopUp;

    @FXML
    private Label DefinitionPopUp;

    @FXML

    private VBox libChildContainer;

    @FXML
    private HBox rencentContainer;

    @FXML
    private VBox FolderContainer;

    @FXML
    private VBox newWordContainer;

    @FXML
    private VBox PopUpAudio;

    @FXML
    private VBox twoFolderContainer;

    private DictFuncToLearner dictFuncToLearner = new ConnectComponentDict();

    private FolderReposity folderReposity = new FolderReposity();

    private LearnerScreenChanger learnerScreenChanger = new LearnerScreenChanger();

    public void initialize() {
        System.out.println(paneContainer);
        transition1 = new TranslateTransition(Duration.seconds(0.5), movingAnimation);

        double FolderPos = 0;

        double ListPos = 178;

//        FolderBtn.setOnAction(e -> {
//            transition1.setToX(FolderPos);
//            transition1.play();
//            FolderBtn.getStyleClass().add("active");
//            ListBtn.getStyleClass().remove("active");
//            showAllBtn.setText("View All Folder");
//            firstName.setText("Folder Name");
//            secondName.setText("Folder Name");
//        });

        yourLib.setOnAction(e -> {
            e.consume();
            paneContainer.setVisible(!paneContainer.isVisible());
            paneContainer.toFront();
            renderTwoFolder();
        });

        yourLibContain.setOnMouseClicked(e -> {
            e.consume();
            paneContainer.setVisible(!paneContainer.isVisible());
            paneContainer.toFront();
            System.out.println("Lib Click");
            renderTwoFolder();
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
                learnerScreenChanger.switchToFolder();
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

        PopUpAudio.setOnMouseClicked(e -> {
            TextToSpeech.processTextToSpeech(WordPopUp.getText());
        });

        recentFolderRender();

        newWordRender();

        recentWordRender();
    }

    public void movingAnimation(TranslateTransition transition, double pos) {
        transition.setToX(pos);
        transition.play();
    }

    public void recentFolderRender() {
        List<Folder> recentFolder = FolderManager.getIntance().getRecentFolder();
        FolderContainer.getChildren().clear();
        for (Folder item : recentFolder) {
            HBox containerHbox = new HBox();
            containerHbox.setAlignment(Pos.CENTER_LEFT);
            containerHbox.setSpacing(20);
            containerHbox.getStyleClass().add("recentFolderContainer");
            String imagePath = "/com/example/demo/assets/folder.png";
            URL imageUrl = getClass().getResource(imagePath);
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));
            VBox containerVbox = new VBox();
            containerVbox.setSpacing(20);
            Label label = new Label(item.getName());
            label.getStyleClass().add("FolderNameText");
            containerVbox.getChildren().add(label);
            containerHbox.getChildren().addAll(imageView, containerVbox);
            FolderContainer.getChildren().add(containerHbox);

            containerHbox.setOnMouseClicked(e -> {
                folderReposity.addRecentFolder(item.getId());
                learnerScreenChanger.switchToList(item.getId());
            });

        }
    }

    public void recentWordRender() {
        List<Word> listWord = dictFuncToLearner.getRecentSearches();
        int n=listWord.size();
        System.out.println("listWord has " + listWord.size() + " words");
        rencentContainer.getChildren().clear();
        for (int i = 0; i < n; i++) {
            String word = listWord.get(i).getWord();
            VBox container = new VBox();
            container.setSpacing(15);
            container.getStyleClass().add("wordContainer");
            container.setTranslateY(36);
            VBox engWordContainer = new VBox();
            Label engWord = new Label(listWord.get(i).getWord());
            engWord.getStyleClass().add("engWord");
            engWordContainer.getChildren().add(engWord);
            VBox typeContainer = new VBox();
            Label type = new Label(listWord.get(i).getType().get(0));
            type.getStyleClass().add("type");
            typeContainer.getChildren().add(type);
            HBox audio = new HBox();
            audio.setSpacing(15);
            VBox imgContainer = new VBox();
            String imagePath = "/com/example/demo/assets/audio.png";
            URL imageUrl = getClass().getResource(imagePath);
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));
            imgContainer.getChildren().add(imageView);
            VBox pronunContainer = new VBox();
            Label pronunciation = new Label(listWord.get(i).getPronunciation());
            pronunciation.getStyleClass().add("pronunciation");
            pronunContainer.getChildren().add(pronunciation);
            audio.getChildren().add(imgContainer);
            audio.getChildren().add(pronunContainer);
            VBox definitionContainer = new VBox();
            Label difinition = new Label(listWord.get(i).getDefinition().get(0));
            difinition.getStyleClass().add("description");
            definitionContainer.getChildren().add(difinition);
            container.getChildren().addAll(engWordContainer, typeContainer, audio, definitionContainer);
            rencentContainer.getChildren().add(container);

            imgContainer.setOnMouseClicked(e -> {
                e.consume();
                TextToSpeech.processTextToSpeech(word);
            });

            container.setOnMouseClicked(e -> {

                System.out.println("Chuyen huong");
            });
        }
    }

    public void newWordRender() {
        List<Word> words = dictFuncToLearner.getDailyNewWords();
        newWordContainer.getChildren().clear();
        for (Word word : words) {
            StackPane containerStack = new StackPane();
            containerStack.getStyleClass().add("word");
            Label label = new Label(word.getWord());
            label.getStyleClass().add("textBody");
            StackPane.setAlignment(label, Pos.TOP_LEFT);
            containerStack.getChildren().add(label);
            newWordContainer.getChildren().add(containerStack);
            containerStack.setOnMouseClicked(e -> {
                e.consume();
                PopUpContainer.setVisible(true);
                WordPopUp.setText(word.getWord());
                TypePopUp.setText(word.getType().get(0));
                if (!word.getPronunciation().equals("null"))
                    PronunciationPopUp.setText(word.getWord());
                else
                    PronunciationPopUp.setText("");
                DefinitionPopUp.setText(word.getDefinition().get(0));
            });
        }
    }

    public void renderTwoFolder() {
        List<Folder> folders = FolderManager.getIntance().getTwoFolderRandom();
        twoFolderContainer.getChildren().clear();
        for (Folder folder : folders) {
            HBox containerHBox = new HBox();
            containerHBox.setAlignment(Pos.CENTER);
            VBox containerVBox = new VBox();
            containerVBox.setSpacing(10);
            containerVBox.setAlignment(Pos.CENTER_LEFT);
            containerVBox.getStyleClass().add("textContainer");
            Label label = new Label(folder.getName());
            containerVBox.getChildren().add(label);
            containerHBox.getChildren().add(containerVBox);
            twoFolderContainer.getChildren().add(containerHBox);

            containerHBox.setOnMouseClicked(e->{
                learnerScreenChanger.switchToList(folder.getId());
            });
        }
    }
}
