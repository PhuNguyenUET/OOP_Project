package com.example.demo.backend.DictionaryBackend;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLetter;

public class WordController {
    WordRepository wordRepository = new WordRepository();
    DictionaryService dictionaryService = new DictionaryService(wordRepository);

    public List<String> search (String input) {
        List<String> output = new ArrayList<>();
        int n = input.length();

        if (n == 0 || n == 1) {
            return output;
        }
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (!isLetter(c) && c != '-' && c != ' ') {
                return output;
            }
        }

        return dictionaryService.search(input);
    }

    public String transferWord (String input) {
        int n = input.length();

        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (!isLetter(c) && c != '-' && c != ' ') {
                return "";
            }
        }

        return dictionaryService.transferWord(input);
    }

    public String getDailyNewWords() {
        return dictionaryService.getDailyNewWords();
    }

    public String getRecentSearches() {
        return dictionaryService.getRecentSearches();
    }

    public void updateRecentSearches(String word) {
        dictionaryService.updateRecentSearches(word);
    }

    public boolean checkWordExist(String word) {
        int n = word.length();

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (!isLetter(c) && c != '-' && c != ' ') {
                return false;
            }
        }

        return dictionaryService.checkWordExist(word);
    }
}
