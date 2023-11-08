package com.example.demo.frontend.Common;

import java.util.List;

public abstract class DictFuncToLearner {
    public abstract List<Word> getDailyNewWords();

    public abstract List<Word> getRecentSearches();

    public abstract boolean isWordInDict(String word);

    public abstract Word getDetails(String word);
}