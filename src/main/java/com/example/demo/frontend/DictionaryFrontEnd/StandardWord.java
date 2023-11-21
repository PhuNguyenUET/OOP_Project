package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.frontend.Common.Word;

import java.util.ArrayList;
import java.util.List;

public class StandardWord implements Word {
    private String word;

    private String pronunciation;

    private List<Explanation> explanations;

    public List<String> getType()
    {
        if (explanations.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        res.add(explanations.get(0).getWord_type());
        return res;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public void setExplanations(List<Explanation> explanations) {
        this.explanations = explanations;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public List<String> getDefinition() {
        List<String> definitonList = new ArrayList<>();
        for (Explanation explanation : explanations) {
            definitonList.add(explanation.getDefinition());
        }
        return definitonList;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public List<Explanation> getExplanations() {
        return explanations;
    }

    public StandardWord(){}

    public StandardWord(String word, String pronunciation) {
        this.word = word;
        this.pronunciation = pronunciation;
    }

    public StandardWord(String word, String pronunciation, List<Explanation> explanations) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.explanations = explanations;
    }
}
