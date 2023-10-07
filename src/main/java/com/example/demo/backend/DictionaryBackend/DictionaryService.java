package com.example.demo.backend.DictionaryBackend;

import java.util.ArrayList;
import java.util.List;

public class DictionaryService {
    public List<String> search (String input) {
        List<StandardWord> words = WordRepository.searchSimilar(DictionaryDatabase.Instance().getConnection(), input);
        List<String> word_format = new ArrayList<>();
        for (StandardWord current : words) {
            StringBuilder wordBuild = new StringBuilder();
            wordBuild.append(current.getWord()).append("_").append(current.getWord_type()).append("_").
                    append(current.getPronunciation()).append("_").append(current.getDefinition()).append("_");
            List<String> examples = current.getExamples();
            for (String example : examples) {
                wordBuild.append(example).append("/");
            }
            wordBuild.deleteCharAt(wordBuild.length() - 1);
            word_format.add(wordBuild.toString());
        }
        return word_format;
    }

    public String transferWord (String input) {
        StandardWord word = WordRepository.searchForWord(DictionaryDatabase.Instance().getConnection(), input);
        StringBuilder wordBuild = new StringBuilder();
        if (word == null) {
            return "";
        }
        wordBuild.append(word.getWord()).append("_").append(word.getWord_type()).append("_").
                append(word.getPronunciation()).append("_").append(word.getDefinition()).append("_");
        List<String> examples = word.getExamples();
        for (String example : examples) {
            wordBuild.append(example).append("/");
        }
        wordBuild.deleteCharAt(wordBuild.length() - 1);
        return wordBuild.toString();
    }
}
