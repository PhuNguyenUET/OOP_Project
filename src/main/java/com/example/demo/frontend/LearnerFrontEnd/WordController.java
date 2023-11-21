package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.ListReposity;
import com.example.demo.backend.LearnerBackend.WordManager;
import com.example.demo.backend.LearnerBackend.WordReposity;
import com.example.demo.backend.TextToSpeech;
import com.example.demo.frontend.Common.DictFuncToLearner;
import com.example.demo.frontend.Common.Word;
import com.example.demo.frontend.DictionaryFrontEnd.ConnectComponentDict;
import com.example.demo.frontend.DictionaryFrontEnd.StandardWord;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.Optional;

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

    private VBox visibleChangeContainer = null;
    private LearnerScreenChanger learnerScreenChanger = new LearnerScreenChanger();

    private ListReposity listReposity = new ListReposity();

    private WordReposity wordReposity = new WordReposity();

    private DictFuncToLearner dictFuncToLearner = new ConnectComponentDict();

    @FXML
    private VBox dialogWrapper;

    @FXML
    private Button dialogOk;
    @FXML
    private Button dialogCancel;

    public void initialize() {
        System.out.println(ScreenManager.getInstance().getListName());

        listBtnName.setWrapText(true);
        listBtnName.setText(listReposity.getListName(ScreenManager.getInstance().getListId()));

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
            ScreenManager.getInstance().getNavbarController().resetPopupWindow();
            addWordContainer.setVisible(false);
            if (visibleChangeContainer != null) {
                visibleChangeContainer.setVisible(false);
                visibleChangeContainer = null;
            }
        });

        addWordContainer.setOnMouseClicked(e -> {
            e.consume();
        });

        pauseTransition.setOnFinished(e -> {
            toastMesTransition.setToX(360);
            toastMesTransition.play();
        });

        addWord.setOnMouseClicked(e -> {
            if (!wordReposity.addNewList(inputFormWord.getText().trim(), inputFormType.getText().trim(), inputFormDefinition.getText().trim(), "", ScreenManager.getInstance().getListId())) {
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

        usingStandard.setOnAction(e -> {
            String engWord = inputFormWord.getText().trim();
            System.out.println("Tu can them trong thu vien la" + engWord + " " + dictFuncToLearner.isWordInDict(engWord));
            if (dictFuncToLearner.isWordInDict(engWord) && !wordReposity.wordIsExist(inputFormWord.getText().trim(), ScreenManager.getInstance().getListId())) {
                Word word = dictFuncToLearner.getDetails(engWord);
                wordReposity.addNewList(word.getWord(), word.getType().get(0), word.getDefinition().get(0), word.getPronunciation(), ScreenManager.getInstance().getListId());
                int n = WordManager.getInstance().updateAndGetWordFromList(ScreenManager.getInstance().getListId()).size();
                int cnt_page = 0;
                if (n % 10 == 0) {
                    cnt_page = n / 10;
                } else {
                    cnt_page = n / 10 + 1;
                }
                curPage = cnt_page - 1;
                wordRender(ScreenManager.getInstance().getListId());
            } else {
                messageRender("Can not add this word", "Please enter a word that does not contain capital letters");
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
        if (curPage >= cnt_page && curPage != 0) {
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
            int index = i;
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
            Label pronun = new Label("");
            if (!listWord.get(i).getPronunciation().equals("null")) {
                pronun = new Label(listWord.get(i).getPronunciation());
            }
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

            String editPath = "/com/example/demo/assets/edit_20.png";
            URL editUrl = getClass().getResource(editPath);
            ImageView imageViewEdit = new ImageView(new Image(editUrl.toString()));
            imageViewEdit.setTranslateX(-8);
            imageViewEdit.setTranslateY(8);
            imageViewEdit.getStyleClass().add("edit");
            StackPane.setAlignment(imageViewEdit, Pos.TOP_RIGHT);

            VBox changeContainer = new VBox();
            changeContainer.setVisible(false);
            changeContainer.setAlignment(Pos.CENTER);
            changeContainer.getStyleClass().add("addFolder");
            StackPane.setAlignment(changeContainer, Pos.TOP_RIGHT);
            changeContainer.setTranslateY(-60);
            changeContainer.setSpacing(15);
            changeContainer.setMaxHeight(240);
            changeContainer.setMinHeight(240);
            changeContainer.setPrefHeight(240);
            changeContainer.setMaxWidth(240);
            changeContainer.setPrefWidth(240);
            changeContainer.setMinWidth(240);
            Label label = new Label("Change this word");
            label.getStyleClass().add("newFolderText");
            TextField wordInput = new TextField();
            wordInput.setPromptText("Word");
            wordInput.getStyleClass().add("inputForm");
            TextField typeInput = new TextField();
            typeInput.setPromptText("Type");
            typeInput.getStyleClass().add("inputForm");
            TextField definitionInput = new TextField();
            definitionInput.setPromptText("Definition");
            definitionInput.getStyleClass().add("inputForm");
            wordInput.setText(listWord.get(index).getWord());
            typeInput.setText(listWord.get(index).getType());
            definitionInput.setText(listWord.get(index).getDefinition());

            HBox buttonContainer = new HBox();
            buttonContainer.setAlignment(Pos.CENTER);
            Button changeBtn = new Button("Change");
            changeBtn.getStyleClass().add("addFolderName");
            buttonContainer.getChildren().add(changeBtn);

            changeContainer.getChildren().addAll(wordInput, typeInput, definitionInput, buttonContainer);

            containerStack.getChildren().addAll(container, imageViewDelete, imageViewEdit, changeContainer);
            wordListContainer.getChildren().add(containerStack);

            imageViewEdit.setOnMouseClicked(e -> {
                e.consume();
                if (visibleChangeContainer != null) {
                    visibleChangeContainer.setVisible(false);
                }
                changeContainer.setVisible(true);
                visibleChangeContainer = changeContainer;
                wordInput.requestFocus();
            });

            changeBtn.setOnAction(e -> {
                String newWord = wordInput.getText();
                String newType = typeInput.getText();
                String newDefinition = definitionInput.getText();
                if (wordReposity.updateWord(newWord, newType, newDefinition, id)) {
                    wordRender(ScreenManager.getInstance().getListId());
                } else {
                    messageRender("Invalid", "Please enter another value!");
                }
            });

            definitionInput.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    changeBtn.fire();
                }
            });

            wordInput.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    changeBtn.fire();
                }
            });

            typeInput.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    changeBtn.fire();
                }
            });

            detailBtn.setOnAction(e -> {
                if (!dictFuncToLearner.isWordInDict(listWord.get(index).getWord())) {
                    messageRender("Can not find this word", "This word does not exist in the dictionary");
                } else {
                    Word tmpWord = dictFuncToLearner.getDetails(listWord.get(index).getWord());
                    ScreenManager.getInstance().switchToWordDisplay((StandardWord) tmpWord);
                    ScreenManager.getInstance().getNavbarController().handleActive(ScreenManager.getInstance().getNavbarController().getDictionary());
                }
            });

            imgContainer.setOnMouseClicked(e -> {
                TextToSpeech.processTextToSpeech(engWord.getText());
            });

            imageViewDelete.setOnMouseClicked(e -> {
                e.consume();
                turnOnDialog();
                dialogOk.setOnAction(event -> {
                    wordReposity.removeWordWithId(id);
                    wordRender(ScreenManager.getInstance().getListId());
                    turnOffDialog();
                });
                dialogCancel.setOnAction(event -> {
                    turnOffDialog();
                });
//                wordReposity.removeWordWithId(id);
//                wordRender(ScreenManager.getInstance().getListId());
            });
        }
    }

    public void messageRender(String textMessageInput, String textMessageDesInput) {
        toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
        URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
        Image image = new Image(imageUrl.toString());
        textMessage.setText(textMessageInput);
        textMessageDes.setText(textMessageDesInput);
        toastIcon.setImage(image);
        toastMesTransition.setToX(-28);
        toastMesTransition.play();
        pauseTransition.play();
    }

    public void turnOnDialog() {
        dialogWrapper.setVisible(true);
        dialogWrapper.setDisable(false);
    }

    public void turnOffDialog() {
        dialogWrapper.setVisible(false);
        dialogWrapper.setDisable(true);
    }
}
