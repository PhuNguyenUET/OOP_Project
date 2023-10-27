package com.example.demo.frontend.DictionaryFrontEnd;

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

public class WordNotExistScreenController implements Initializable {
    SearchBarController searchBarController;

    @FXML
    public TextField searchBar;

    @FXML
    public Button xButton;

    @FXML
    public Button searchButton;

    @FXML
    public VBox suggestionBox;

    URL imageUrl = getClass().getResource("/com/example/demo/assets/search.png");
    Image searchImg = new Image(imageUrl.toString());
    ImageView search = new ImageView(searchImg);

    public SearchBarController getSearchBarController() {
        return searchBarController;
    }

    public void setSearchBarController(SearchBarController searchBarController) {
        this.searchBarController = searchBarController;
    }

    @FXML
    protected void clearSearch(ActionEvent event) {
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
    protected void back (ActionEvent event) throws IOException {
        DictionarySceneChanger.Instance().switchToHomeScreen(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setFitWidth(35);
        search.setFitHeight(35);
        suggestionBox.setVisible(false);
        searchButton.setGraphic(search);
    }
}
