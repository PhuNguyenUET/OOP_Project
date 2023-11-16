package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.ConnectComponentLearner;
import com.example.demo.backend.TextToSpeech;
import com.example.demo.frontend.Common.LearnerFuncToDict;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WordDisplayController implements Initializable {

    private LearnerFuncToDict lf = new ConnectComponentLearner();

    private List<Label> folders = new ArrayList<>();

    private List<Label> lists = new ArrayList<>();

    @FXML
    public TextField searchBar;
    @FXML
    public Button xButton;
    @FXML
    public Button searchButton;
    @FXML
    public VBox suggestionBox;
    @FXML
    public VBox explanationBox;
    @FXML
    public Label wordDisplay;
    @FXML
    public Label pronunciation;
    @FXML
    public VBox popUpAddWindow;
    @FXML
    public Label addSelection;
    @FXML
    public VBox selectionList;
    @FXML
    public ScrollPane definitonScrollPane;
    @FXML
    public ScrollPane addScrollPane;

    URL imageUrl = getClass().getResource("/com/example/demo/assets/search.png");
    Image searchImg = new Image(imageUrl.toString());
    ImageView search = new ImageView(searchImg);

    // Observer? Is there a way to notify the system when word changes, so that
    // we don't have to destroy the
    private StandardWord word;
    private SearchBarController searchBarController;

    protected SearchBarController getSearchBar() {
        return searchBarController;
    }

    protected void setSearchBar(SearchBarController searchBar) {
        this.searchBarController = searchBar;
        this.searchBar.setText(searchBarController.getSearchField());
    }

    protected StandardWord getWord() {
        return word;
    }

    protected void setWord(StandardWord word) {
        this.word = word;
    }

    public void setContent() {
        wordDisplay.setText(word.getWord());
        pronunciation.setText(word.getPronunciation());

        List<Explanation> explanations = word.getExplanations();

        for (Explanation explanation : explanations) {
            Label wordType = new Label(explanation.getWord_type());
            wordType.setWrapText(true);
            wordType.getStyleClass().add("typeLabel");
            Label definition = new Label(explanation.getDefinition());
            definition.setWrapText(true);
            definition.getStyleClass().add("definitionLabel");
            explanationBox.getChildren().add(wordType);
            explanationBox.getChildren().add(definition);
            for (String example : explanation.getExamples()) {
                Label exampleLabel = new Label(example);
                exampleLabel.setWrapText(true);
                exampleLabel.getStyleClass().add("exampleLabel");
                explanationBox.getChildren().add(exampleLabel);
            }
        }
    }

    @FXML
    protected void clearSearch() {
        searchBarController.clearSearch();
        searchBar.clear();
        suggestionBox.setVisible(false);
    }

    @FXML
    protected void goTo(ActionEvent event) throws IOException {
        searchBarController.searchForWord(event, searchBar.getText());
    }

    @FXML
    protected void showSuggestions() {
        suggestionBox.getChildren().clear();
        searchBarController.setSearchField(searchBar.getText());
        suggestionBox.setVisible(true);
        suggestionBox.getChildren().addAll(searchBarController.getSuggestion());
    }

    @FXML
    protected void handleKey (KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            searchBarController.searchForWord(event, searchBarController.getSearchField());
        }
    }

    @FXML
    protected void speakUp() {
        TextToSpeech.processTextToSpeech(word.getWord());
    }

    @FXML
    protected void clearPopups (MouseEvent event) {
        suggestionBox.setVisible(false);
        popUpAddWindow.setDisable(true);
        popUpAddWindow.setVisible(false);
    }

    @FXML
    protected void addToYourList (Event event)
    {
        addSelection.setText("Select your folder");
        displayAllFolders();
        popUpAddWindow.setDisable(false);
        popUpAddWindow.setVisible(true);
    }

    @FXML
    protected void showScrollBarDef() {
        showScrollBar(definitonScrollPane);
    }

    @FXML
    protected void hideScrollBarDef() {
        hideScrollBar(definitonScrollPane);
    }

    @FXML
    protected void showScrollBarAdd() {
        showScrollBar(addScrollPane);
    }

    @FXML
    protected void hideScrollBarAdd() {
        hideScrollBar(addScrollPane);
    }

    private void showScrollBar (ScrollPane sp) {
        for (Node node : sp.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                ScrollBar scrollBar = (ScrollBar) node;
                if (scrollBar.getOrientation() == Orientation.VERTICAL) {
                    if (explanationBox.getHeight() > definitonScrollPane.getHeight()) {
                        scrollBar.setVisible(true);
                    }
                }
            }
        }
    }
    private void hideScrollBar (ScrollPane sp) {
        for (Node node : sp.lookupAll(".scroll-bar")) {
            if (node instanceof ScrollBar) {
                ScrollBar scrollBar = (ScrollBar) node;
                if (scrollBar.getOrientation() == Orientation.VERTICAL) {
                    scrollBar.setVisible(false);
                }

            }
        }
    }

    private void displayAllFolders() {
        folders.clear();
        selectionList.getChildren().clear();
        List<String> f = lf.getAllFolders(ScreenManager.getInstance().getUserId());
        for (int i = 0; i < f.size(); i++) {
            Label temp = new Label(f.get(i));
            temp.getStyleClass().add("selectLabel");
            temp.setOnMouseClicked(event -> {
                addSelection.setText("Select your list");
                displayAllLists(temp.getText());
            });
            folders.add(temp);
            selectionList.getChildren().add(temp);
        }
    }

    private void displayAllLists(String folder) {
        lists.clear();
        selectionList.getChildren().clear();
        List<String> f = lf.getAllLists(folder);
        for (int i = 0; i < f.size(); i++) {
            Label temp = new Label(f.get(i));
            temp.getStyleClass().add("selectLabel");
            temp.setOnMouseClicked(event -> {
                popUpAddWindow.setDisable(true);
                popUpAddWindow.setVisible(false);
                lf.addToList(folder, temp.getText(), word);
            });
            folders.add(temp);
            selectionList.getChildren().add(temp);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setFitWidth(35);
        search.setFitHeight(35);
        suggestionBox.setVisible(false);
        popUpAddWindow.setDisable(true);
        popUpAddWindow.setVisible(false);
        searchButton.setGraphic(search);
        hideScrollBarAdd();
        hideScrollBarDef();
    }
}
