package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchBarController {
    void searchForWord (Event event, String input) throws IOException {
        StandardWord word = DictionaryIntegration.Instance().transferTo(input);
        if (word == null) {
            DictionarySceneChanger.Instance().switchToWordNotExistScreen(this);
        } else {
            DictionarySceneChanger.Instance().switchToWordDisplay(this, word);
        }
    }

    private String searchField;

    protected String getSearchField() {
        return searchField;
    }

    protected void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    protected void clearSearch () {
        searchField = "";
    }

    protected List<String> getSimilarWords() {
        return DictionaryIntegration.Instance().searchSimilar(searchField);
    }

    public List<Label> getSuggestion() {
        List<Label> res = new ArrayList<>();
        List<String> suggestedWord = getSimilarWords();
        for (String s : suggestedWord) {
            Label label = new Label(s);
            label.getStyleClass().add("searchResultLabel");
            label.setOnMouseClicked(event ->  {
                try {
                    searchForWord(event, label.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            label.setOnMouseEntered(mouseEvent -> {
                label.getStyleClass().add("searchResultLabelSelected");
            });
            label.setOnMouseExited(mouseEvent -> {
                label.getStyleClass().clear();
                label.getStyleClass().add("searchResultLabel");
            });
            res.add(label);
        }
        return res;
    }
}
