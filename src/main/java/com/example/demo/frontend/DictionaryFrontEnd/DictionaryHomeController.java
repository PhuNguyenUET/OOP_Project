package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class DictionaryHomeController {
    SearchBarController searchBarController = new SearchBarController();
    @FXML
    public Button dictionaryChanger;

    @FXML
    public Button learnersPathChanger;

    @FXML
    public Button translateChanger;

    @FXML
    public Button gameChanger;

    @FXML
    public Button settingsChanger;

    @FXML
    public ImageView logo;

    @FXML
    public TextField searchBar;

    @FXML
    public Button xButton;

    @FXML
    public Button searchButton;

    @FXML
    public Button translateChanger2;

    @FXML
    public Button settingsChanger2;

    @FXML
    public ImageView coffee;

    @FXML
    protected void clearSearch(ActionEvent event) {
        searchBarController.clearSearch();
        searchBar.clear();
    }

    @FXML
    protected void goTo (ActionEvent event) throws IOException {
        searchBarController.searchForWord(event, searchBar.getText());
    }
}
