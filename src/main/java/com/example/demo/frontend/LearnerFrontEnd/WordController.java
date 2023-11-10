package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.ListReposity;
import com.example.demo.backend.LearnerBackend.WordManager;
import com.example.demo.backend.LearnerBackend.WordReposity;
import com.example.demo.backend.TextToSpeech;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;

public class WordController {

    int curPage = 0;
    @FXML
    private Button backBtn;

    @FXML

    private Button listBtnName;

    @FXML

    private FlowPane wordListContainer;

    @FXML

    private HBox pageListContainer;

    @FXML
    private HBox addBtn;

    @FXML
    private VBox addWordContainer;

    @FXML
    private TextField inputFormWord;

    @FXML
    private TextField inputFormType;

    @FXML
    private TextField inputFormDefinition;

    @FXML
    private Button addWord;

    @FXML
    private Button usingStandard;

    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));

    @FXML
    private TranslateTransition toastMesTransition;

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
    private LearnerScreenChanger learnerScreenChanger = new LearnerScreenChanger();

    public void initialize() {
        System.out.println(ScreenManager.getInstance().getListName());

        listBtnName.setText(ListReposity.getInstance().getListName(ScreenManager.getInstance().getListId()));

        backBtn.setOnAction(e -> {
//            ScreenManager.getInstance().switchToList(ScreenManager.getInstance().getFolderId());
            learnerScreenChanger.switchToList(ScreenManager.getInstance().getFolderId());
        });

        addBtn.setOnMouseClicked(e -> {
            e.consume();
            addWordContainer.setVisible(!addWordContainer.isVisible());
            inputFormWord.requestFocus();
        });

        ScreenManager.getInstance().getScene().setOnMouseClicked(e -> {
            addWordContainer.setVisible(false);
        });

        addWordContainer.setOnMouseClicked(e->{
            e.consume();
        });

        pauseTransition.setOnFinished(e -> {
            toastMesTransition.setToX(360);
            toastMesTransition.play();
        });

        addWord.setOnMouseClicked(e -> {
            if (!WordReposity.getInstance().addNewList(inputFormWord.getText().trim(), inputFormType.getText().trim(), inputFormDefinition.getText().trim(), ScreenManager.getInstance().getListId())) {
                toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
                URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                Image image = new Image(imageUrl.toString());
                textMessage.setText("Invalid Word Input");
                textMessageDes.setText("Please try again!");
                toastIcon.setImage(image);
                toastMesTransition.setToX(-28);
                toastMesTransition.play();
                pauseTransition.play();
            } else {
                int n = WordManager.getInstance().updateAndGetWordFromList(ScreenManager.getInstance().getListId()).size();
                int cnt_page = 0;
                if (n % 10 == 0) {
                    cnt_page = n / 10;
                } else {
                    cnt_page = n / 10 + 1;
                }
                curPage = cnt_page - 1;
                wordRender(ScreenManager.getInstance().getListId());
            }
            addWordContainer.setVisible(false);
        });

        wordListContainer.toFront();

        wordRender(ScreenManager.getInstance().getListId());
    }

    public void wordRender(int listId) {
        List<UserWord> listWord = WordManager.getInstance().updateAndGetWordFromList(listId);
        int n = listWord.size();
        int cnt_page = 0;
        if (n % 10 == 0) {
            cnt_page = n / 10;
        } else {
            cnt_page = n / 10 + 1;
        }
        if (curPage >= cnt_page && curPage!=0) {
            curPage = curPage - 1;
        }
        wordRenderInPage(curPage, listId);
        ObservableList<Node> pages = pageListContainer.getChildren();

        pageListContainer.getChildren().clear();
        for (int i = 0; i < cnt_page; i++) {
            int index = i;
            VBox pageContain = new VBox();
            pageContain.setAlignment(Pos.CENTER);
            Button page = new Button("" + (i + 1));
            page.getStyleClass().add("page");

            if (i == curPage) {
                page.getStyleClass().add("active");
            }

            pageContain.getChildren().add(page);
            pageListContainer.getChildren().add(pageContain);

            page.setOnAction(e -> {
                wordRenderInPage(index, listId);
                this.curPage = index;
                for (Node node : pages) {
                    if (node instanceof VBox) {
                        VBox container = (VBox) node;
                        if (container.getChildren().size() > 0) {
                            Button button = (Button) container.getChildren().get(0);
                            button.getStyleClass().remove("active");
                        }
                    }
                }

                page.getStyleClass().add("active");
            });
        }

    }

    public void wordRenderInPage(int page, int listId) {
        List<UserWord> listWord = WordManager.getInstance().updateAndGetWordFromList(listId);
        int n = listWord.size();
        int size = n;
        if (10 * page + 10 < n) {
            size = 10 * page + 10;
        }
        wordListContainer.getChildren().clear();
        for (int i = page * 10; i < size; i++) {
            int id = listWord.get(i).getId();
            StackPane containerStack = new StackPane();
            VBox container = new VBox();
            container.setAlignment(Pos.CENTER);
            container.setPrefWidth(240);
            container.setPrefHeight(240);
            container.getStyleClass().add("wordListContainer");
            HBox hcontainer = new HBox();
            hcontainer.setAlignment(Pos.CENTER);
            VBox.setVgrow(hcontainer, Priority.ALWAYS);
            VBox wordContainer = new VBox();
            wordContainer.setSpacing(15);
            wordContainer.getStyleClass().add("wordContainer");
            HBox.setHgrow(wordContainer, Priority.ALWAYS);
            wordContainer.setAlignment(Pos.CENTER);
            VBox engWordContainer = new VBox();
            Label engWord = new Label(listWord.get(i).getWord());
            engWord.getStyleClass().add("engWord");
            engWordContainer.getChildren().add(engWord);
            VBox typeContainer = new VBox();
            Label type = new Label(listWord.get(i).getType());
            type.getStyleClass().add("type");
            typeContainer.getChildren().add(type);
            HBox audio = new HBox();
            audio.setSpacing(15);
            VBox imgContainer = new VBox();
            imgContainer.getStyleClass().add("imgContainer");
            String imagePath = "/com/example/demo/assets/audio.png";
            URL imageUrl = getClass().getResource(imagePath);
            ImageView imageView = new ImageView(new Image(imageUrl.toString()));
            imgContainer.getChildren().add(imageView);
            VBox pronunConatiner = new VBox();
            Label pronun = new Label(listWord.get(i).getPronunciation());
            pronun.getStyleClass().add("pronunciation");
            pronunConatiner.getChildren().add(pronun);
            audio.getChildren().add(imgContainer);
            audio.getChildren().add(pronunConatiner);
            VBox definitionConatiner = new VBox();
            Label definition = new Label(listWord.get(i).getDefinition());
            definition.getStyleClass().add("desciption");
            definitionConatiner.getChildren().add(definition);
            HBox detailContainer = new HBox();
            detailContainer.setAlignment(Pos.CENTER);
            Button detailBtn = new Button("Detail");
            detailBtn.setTranslateX(-16);
            detailBtn.getStyleClass().add("detailBtn");
            detailContainer.getChildren().add(detailBtn);
            wordContainer.getChildren().addAll(engWordContainer, typeContainer, audio, definitionConatiner, detailContainer);
            hcontainer.getChildren().add(wordContainer);
            container.getChildren().add(hcontainer);
            String deletePath = "/com/example/demo/assets/delete_20.png";
            URL deleteUrl = getClass().getResource(deletePath);
            ImageView imageViewDelete = new ImageView(new Image(deleteUrl.toString()));
            imageViewDelete.setTranslateX(-8);
            imageViewDelete.setTranslateY(-8);
            imageViewDelete.getStyleClass().add("deleteImg");
            StackPane.setAlignment(imageViewDelete, Pos.BOTTOM_RIGHT);
            containerStack.getChildren().addAll(container, imageViewDelete);
            wordListContainer.getChildren().add(containerStack);

            detailBtn.setOnAction(e -> {

            });

            imgContainer.setOnMouseClicked(e -> {
                TextToSpeech.processTextToSpeech(engWord.getText());
            });

            imageViewDelete.setOnMouseClicked(e -> {
                WordReposity.getInstance().removeWordWithId(id);
                wordRender(ScreenManager.getInstance().getListId());
            });
        }
    }
}
