package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.frontend.Common.DictFuncToLearner;
import com.example.demo.frontend.Common.Word;

import java.util.ArrayList;
import java.util.List;

public class ConnectComponentDict extends DictFuncToLearner {
    @Override
    public List<Word> getDailyNewWords() {
        List<StandardWord> lst = DictionaryIntegration.Instance().getDailyNewWords();
        return new ArrayList<>(lst);
    }

    @Override
    public List<Word> getRecentSearches() {
        List<StandardWord> lst = DictionaryIntegration.Instance().getRecentSearches();
        return new ArrayList<>(lst);
    }

    @Override
    public boolean isWordInDict(String word) {
        return DictionaryIntegration.Instance().checkWordExist(word);
    }

    @Override
    public Word getDetails(String word) {
        return DictionaryIntegration.Instance().transferTo(word);
    }
}
