package com.example.demo.backend.DictionaryBackend;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class DictionaryService {
    private final WordRepository wordRepository;
    private final Connection connection = DictionaryDatabase.Instance().getConnection();

    DictionaryService (WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    };
    protected List<String> search (String input) {
        return wordRepository.searchSimilar(connection, input);
    }

    protected String transferWord (String input)  {
        StandardWord word = wordRepository.searchForWord(connection, input);
        if (word == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(word);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    protected String getDailyNewWords() {
        String today = LocalDate.now().toString();
        List<StandardWord> lst;
        System.out.println(today);
        System.out.println(wordRepository.getMostRecentDailyVisit(connection));
        if (today.equals(wordRepository.getMostRecentDailyVisit(connection))) {
            lst = wordRepository.getWordAtDay(connection, today);
        } else {
            lst = wordRepository.getRandomThreeWords(connection);
            wordRepository.updateWordToday(connection, today, lst.get(0).getWord(), lst.get(1).getWord(), lst.get(2).getWord());
        }
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(lst);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    protected String getRecentSearches(int userID) {
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(wordRepository.getRecentSearches(connection, userID));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    protected void updateRecentSearches(String word, int userID) {
        if (!wordRepository.checkWordExist(connection, word)) {
            return;
        }
        wordRepository.updateRecentSearches(connection, word, userID);
        if (wordRepository.getRecentSearchesCount(connection, userID) >= 50) {
            wordRepository.cleanRecentSearches(connection, userID);
        }
    }

    protected boolean checkWordExist(String word) {
        return wordRepository.checkWordExist(connection, word);
    }
}
