package com.example.demo.frontend.DictionaryFrontEnd;

public class WordDisplayController {
    // Observer? Is there a way to notify the system when word changes, so that
    // we don't have to destroy the
    private StandardWord word;
    private SearchBarController searchBar;

    protected SearchBarController getSearchBar() {
        return searchBar;
    }

    protected void setSearchBar(SearchBarController searchBar) {
        this.searchBar = searchBar;
    }

    protected StandardWord getWord() {
        return word;
    }

    protected void setWord(StandardWord word) {
        this.word = word;
    }
}
