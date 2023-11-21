package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.backend.DictionaryBackend.WordController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DictionaryIntegration {
    private static DictionaryIntegration _instance = null;

    private DictionaryIntegration() {
    }

    protected static DictionaryIntegration Instance() {
        if (_instance == null) {
            _instance = new DictionaryIntegration();
        }
        return _instance;
    }

    private final WordController backend = new WordController();

    protected StandardWord transferTo(String input) {
        String stringResponse = backend.transferWord(input);
        if (stringResponse.isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        StandardWord word = new StandardWord();
        try {
            word = mapper.readValue(stringResponse, StandardWord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return word;
    }

    protected StandardWord getSimplifiedWord(String input) {
        String stringResponse = backend.transferWord(input);
        if (stringResponse.isEmpty()) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        StandardWord word = new StandardWord();
        try {
            word = mapper.readValue(stringResponse, StandardWord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Explanation res = word.getExplanations().get(0);
        word.getExplanations().clear();
        word.getExplanations().add(res);
        return word;
    }

    protected List<String> searchSimilar(String input) {
        return backend.search(input);
    }

    protected List<StandardWord> getDailyNewWords() {
        String stringResponse = backend.getDailyNewWords();

        ObjectMapper mapper = new ObjectMapper();
        List<StandardWord> lst = new ArrayList<>();
        try {
            lst = mapper.readValue(
                    stringResponse, new TypeReference<List<StandardWord>>() { });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return lst;
    }

    protected List<StandardWord> getRecentSearches(int userID) {
        String stringResponse = backend.getRecentSearches(userID);

        ObjectMapper mapper = new ObjectMapper();
        List<StandardWord> lst = new ArrayList<>();
        try {
            lst = mapper.readValue(
                    stringResponse, new TypeReference<List<StandardWord>>() { });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return lst;
    }

    protected void updateRecentSearches(String word, int userID) {
        backend.updateRecentSearches(word, userID);
    }

    protected boolean checkWordExist(String word) {
        return backend.checkWordExist(word);
    }
}
