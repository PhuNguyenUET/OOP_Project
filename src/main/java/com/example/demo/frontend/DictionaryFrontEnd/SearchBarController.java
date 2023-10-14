package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.List;

class SearchBarController {
    void searchForWord (Event event, String input) throws IOException {
        StandardWord word = DictionaryIntegration.Instance().transferTo(input);
        DictionarySceneChanger.Instance().switchToWordDisplay(event, this, word);
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
}
