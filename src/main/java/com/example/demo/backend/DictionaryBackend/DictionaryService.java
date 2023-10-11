package com.example.demo.backend.DictionaryBackend;

import java.util.ArrayList;
import java.util.List;

class DictionaryService {
    private final WordRepository wordRepository;

    DictionaryService (WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    };
    protected List<String> search (String input) {
        return wordRepository.searchSimilar(DictionaryDatabase.Instance().getConnection(), input);
    }

    protected String transferWord (String input) {
        StandardWord word = wordRepository.searchForWord(DictionaryDatabase.Instance().getConnection(), input);
        StringBuilder wordBuild = new StringBuilder();
        if (word == null) {
            return "";
        }
        wordBuild.append(word.getWord()).append("_").append(word.getWord_type()).append("_").
                append(word.getPronunciation()).append("_").append(word.getDefinition()).append("_");
        List<String> examples = word.getExamples();
        for (String example : examples) {
            wordBuild.append(example).append("&");
        }
        wordBuild.deleteCharAt(wordBuild.length() - 1);
        return wordBuild.toString();
    }
}
