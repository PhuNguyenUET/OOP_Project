package com.example.demo.backend.DictionaryBackend;

import java.util.ArrayList;
import java.util.List;

public class StandardWord {
    private final String word;
    private final String definition;
    private final String word_type;
    private final String pronunciation;
    private final List<String> examples;

    public List<String> getExamples() {
        if (examples == null) {
            return new ArrayList<>();
        }
        return examples;
    }
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getWord_type() {
        return word_type;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    private StandardWord(WordBuilder builder) {
        this.word = builder.word;
        this.definition = builder.definition;
        this.word_type = builder.word_type;
        this.pronunciation = builder.pronunciation;
        this.examples = builder.examples;
    }

    public static class WordBuilder {
        private String word;
        private String definition;

        private String word_type;
        private String pronunciation;

        private List<String> examples;

        public WordBuilder(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }

        public WordBuilder setWordType(String word_type) {
            this.word_type = word_type;
            return this;
        }

        public WordBuilder setPronunciation(String pronunciation) {
            this.pronunciation = pronunciation;
            return this;
        }

        public WordBuilder setExamples(List<String> examples) {
            this.examples = examples;
            return this;
        }

        public StandardWord build() {
            return new StandardWord(this);
        }
    }
}
