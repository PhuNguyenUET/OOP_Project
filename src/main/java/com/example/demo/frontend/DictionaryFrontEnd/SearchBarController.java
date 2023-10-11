package com.example.demo.frontend.DictionaryFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.List;

class SearchBarController {
    void searchForWord (ActionEvent event, String input) throws IOException {
        StandardWord word = DictionaryIntegration.Instance().transferTo(input);
        DictionarySceneChanger.Instance().switchToWordDisplay(event, word);
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

    protected List<String> getSimilarWordsFromDb () {
        return DictionaryIntegration.Instance().searchSimilar(searchField.toString());
    }
}
