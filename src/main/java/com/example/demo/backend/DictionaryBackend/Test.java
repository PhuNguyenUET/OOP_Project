package com.example.demo.backend.DictionaryBackend;

import com.example.demo.frontend.Common.Word;

public class Test {
    static WordController wc = new WordController();

    public static void main(String[] args) {
        System.out.println(wc.getRecentSearches());
    }
}
