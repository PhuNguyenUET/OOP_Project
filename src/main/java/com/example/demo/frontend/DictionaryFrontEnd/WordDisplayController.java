package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.backend.TextToSpeech;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WordDisplayController implements Initializable {
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
    }

    protected StandardWord getWord() {
        return word;
    }

    protected void setWord(StandardWord word) {
        this.word = word;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setFitWidth(35);
        search.setFitHeight(35);
        suggestionBox.setVisible(false);
        searchButton.setGraphic(search);
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
}
