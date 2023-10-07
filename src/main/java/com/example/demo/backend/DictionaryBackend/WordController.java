package com.example.demo.backend.DictionaryBackend;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLetter;

public class WordController {
    DictionaryService dictionaryService = new DictionaryService();

    public List<String> search (String input) {
        List<String> output = new ArrayList<>();
        int n = input.length();

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
}
