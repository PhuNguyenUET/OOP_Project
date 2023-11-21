package com.example.demo.backend.DictionaryBackend;

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

        return dictionaryService.search(input.toLowerCase());
    }

    public String transferWord (String input) {
        int n = input.length();

        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (!isLetter(c) && c != '-' && c != ' ') {
                return "";
            }
        }

        return dictionaryService.transferWord(input.toLowerCase());
    }

    public String getDailyNewWords() {
        return dictionaryService.getDailyNewWords();
    }

    public String getRecentSearches(int userID) {
        return dictionaryService.getRecentSearches(userID);
    }

    public void updateRecentSearches(String word, int userID) {
        dictionaryService.updateRecentSearches(word, userID);
    }

    public boolean checkWordExist(String word) {
        int n = word.length();

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (!isLetter(c) && c != '-' && c != ' ') {
                return false;
            }
        }

        return dictionaryService.checkWordExist(word.toLowerCase());
    }
}
