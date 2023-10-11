package com.example.demo.backend.DictionaryBackend;

import com.example.demo.backend.Common.Word;

import java.util.ArrayList;
import java.util.List;

public class StandardWord extends Word {
    private final String word_type;
    private final String pronunciation;
    private final List<String> examples;

    public List<String> getExamples() {
        if (examples == null) {
            return new ArrayList<>();
        }
        return examples;
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

    protected static class WordBuilder {
        private String word;
        private String definition;
        private String word_type;
        private String pronunciation;

        private List<String> examples;

        protected WordBuilder(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }

        protected WordBuilder setWordType(String word_type) {
            this.word_type = word_type;
            return this;
        }

        protected WordBuilder setPronunciation(String pronunciation) {
            this.pronunciation = pronunciation;
            return this;
        }

        protected WordBuilder setExamples(List<String> examples) {
            this.examples = examples;
            return this;
        }

        protected StandardWord build() {
            return new StandardWord(this);
        }
    }
}
