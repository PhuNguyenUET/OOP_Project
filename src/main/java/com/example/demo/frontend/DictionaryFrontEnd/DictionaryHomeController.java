package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DictionaryHomeController implements Initializable {
    SearchBarController searchBarController;

    @FXML
    public TextField searchBar;

    @FXML
    public Button xButton;

    @FXML
    public Button searchButton;

    @FXML
    public VBox suggestionBox;

    @FXML
    public List<Label> wordList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchBarController = new SearchBarController();
        suggestionBox.setVisible(false);
    }

    @FXML
    protected void clearSearch(ActionEvent event) {
        searchBarController.clearSearch();
        searchBar.clear();
    }

    @FXML
    protected void goTo(ActionEvent event) throws IOException {
        searchBarController.searchForWord(event, searchBar.getText());
    }

    @FXML
    protected void showSuggestions() {
        suggestionBox.getChildren().clear();
        wordList.clear();
        searchBarController.setSearchField(searchBar.getText());
        List<String> suggestedWord = searchBarController.getSimilarWords();
        for (String s : suggestedWord) {
            Label label = new Label(s);
            label.setOnMouseClicked(event ->  {
                try {
                    searchBarController.searchForWord(event, label.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            wordList.add(label);
            suggestionBox.getChildren().add(label);
        }
        suggestionBox.setVisible(true);
    }

    @FXML
    protected void handleKey (KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            searchBarController.searchForWord(event, searchBarController.getSearchField());
        }
    }
}
