package com.example.demo.frontend.Common;

import java.util.List;

public interface DictFuncToLearner {
    public List<Word> getDailyNewWords();

    public List<Word> getRecentSearches();

    public boolean isWordInDict(Word word);

    public void showDetails(String word);
}
