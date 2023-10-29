package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.backend.DictionaryBackend.WordController;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    protected List<String> searchSimilar(String input) {
        return backend.search(input);
    }
}
