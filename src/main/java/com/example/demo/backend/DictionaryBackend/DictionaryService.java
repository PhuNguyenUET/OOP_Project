package com.example.demo.backend.DictionaryBackend;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class DictionaryService {
    private final WordRepository wordRepository;

    DictionaryService (WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    };
    protected List<String> search (String input) {
        return wordRepository.searchSimilar(DictionaryDatabase.Instance().getConnection(), input);
    }

    protected String transferWord (String input)  {
        StandardWord word = wordRepository.searchForWord(DictionaryDatabase.Instance().getConnection(), input);
        if (word == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(word);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }
}
