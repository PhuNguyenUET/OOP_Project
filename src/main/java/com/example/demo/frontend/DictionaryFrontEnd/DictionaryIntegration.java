package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.backend.DictionaryBackend.WordController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DictionaryIntegration {
    private static DictionaryIntegration _instance = null;

    private DictionaryIntegration() {
    }

    ;

    protected static DictionaryIntegration Instance() {
        if (_instance == null) {
            _instance = new DictionaryIntegration();
        }
        return _instance;
    }

    private final WordController backend = new WordController();

    protected StandardWord transferTo(String input) {
        String stringResponse = backend.transferWord(input);
        List<String> items = Arrays.asList(stringResponse.split(","));
        List<String> examples_list = new ArrayList<>();
        if (items.size() == 5) {
            String examples = items.get(items.size() - 1);
            items.remove(items.size() - 1);
            examples_list = Arrays.asList(examples.split("&"));
        }
        StandardWord word = new StandardWord.WordBuilder(items.get(0), items.get(3))
                .setWordType(items.get(1)).setPronunciation(items.get(2)).setExamples(examples_list).build();
        return word;
    }

    protected List<String> searchSimilar(String input) {
        return backend.search(input);
    }
}
