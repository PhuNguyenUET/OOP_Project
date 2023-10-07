package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.backend.DictionaryBackend.WordController;

import java.util.List;

public class SearchBar {
    private String searchField;

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public void clearSearch () {
        searchField = "";
    }

    //public List<String> getSimilarWordsFromDb (String current) {
    //}
}
