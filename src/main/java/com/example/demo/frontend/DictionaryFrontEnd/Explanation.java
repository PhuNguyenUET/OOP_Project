package com.example.demo.frontend.DictionaryFrontEnd;

import java.util.ArrayList;
import java.util.List;

class Explanation {
    private String definition;
    private String word_type;
    private List<String> examples;

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public String getDefinition() {
        return definition;
    }

    public String getWord_type() {
        return word_type;
    }

    public List<String> getExamples() {
        if (examples == null) {
            return new ArrayList<>();
        }
        return examples;
    }

    public Explanation(){}

    private Explanation(ExplanationBuilder builder) {
        this.word_type = builder.word_type;
        this.definition = builder.definition;
        this.examples = builder.examples;
    }

    protected static class ExplanationBuilder {
        private String word_type;
        private final String definition;
        private List<String> examples;

        protected ExplanationBuilder(String definition) {
            this.definition = definition;
        }

        protected ExplanationBuilder setWordType (String word_type) {
            this.word_type = word_type;
            return this;
        }
        protected ExplanationBuilder setExamples(List<String> examples) {
            this.examples = examples;
            return this;
        }

        protected Explanation build() {
            return new Explanation(this);
        }
    }
}
