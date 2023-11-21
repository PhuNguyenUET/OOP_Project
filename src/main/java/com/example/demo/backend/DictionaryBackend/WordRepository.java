package com.example.demo.backend.DictionaryBackend;

import com.example.demo.backend.TranslateBackend.GoogleTranslate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class WordRepository {
    protected List<String> searchSimilar(Connection connection, String input)
    {
        List<String> similarList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT word FROM words "
                    + " WHERE word LIKE " + " '" + input + "%' LIMIT 8";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("word");
                similarList.add(english_word);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return similarList;
    }

    protected StandardWord searchForWord(Connection connection, String input) {
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM words WHERE word = '" + input + "'";
            ResultSet resultSet = statement.executeQuery(query);
            String word = "";
            String pronunciation = "";
            List<Explanation> explanations = new ArrayList<>();
            List<String> types = new ArrayList<>();
            List<String> explain = new ArrayList<>();
            if (resultSet.next()) {
                word = resultSet.getString("word");
                pronunciation = resultSet.getString("pronunciation");
            }
            
            query = "SELECT * FROM explanation WHERE word = '" + input + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String explanation = resultSet.getString("explanation");
                if (explanation.isEmpty()) {
                    explanation = GoogleTranslate.translate("en", "vi", word);
                }
                types.add(type);
                explain.add(explanation);
            }

            for (int i = 0; i < explain.size(); i++) {
                query = "SELECT * FROM phrases WHERE word = '" + word + "' AND wordExplanation = '" + explain.get(i) +"'";
                resultSet = statement.executeQuery(query);
                List<String> phrases = new ArrayList<>();
                while (resultSet.next()) {
                    String phrase = resultSet.getString("phrase");
                    String phraseExplanation = resultSet.getString("phraseExplanation");
                    phrases.add(phrase + ": " + phraseExplanation);
                }
                
                Explanation ex = new Explanation.ExplanationBuilder(explain.get(i)).setWordType(types.get(i)).setExamples(phrases).build();
                explanations.add(ex);
            }
            if (word.isEmpty()) {
                return null;
            }
            return new StandardWord(word, pronunciation, explanations);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected boolean checkWordExist(Connection connection, String word) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT word FROM words "
                    + " WHERE word = " + " '" + word + "'";
            ResultSet rs = statement.executeQuery(query);
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected List<StandardWord> getRandomThreeWords (Connection connection) {
        List<StandardWord> res = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT * FROM words " +
                    "ORDER BY RANDOM() " +
                    "LIMIT 3;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("word");
                res.add(searchForWord(connection, english_word));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    protected List<StandardWord> getRecentSearches (Connection connection, int userID) {
        List<StandardWord> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT DISTINCT w.wordID, w.word as word " +
                    "FROM recent_searches rs, words w " +
                    "WHERE rs.wordID = w.wordID " +
                    "AND rs.userID = " + userID  +
                    " ORDER BY rs.searchID DESC " +
                    "LIMIT 3;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("word");
                res.add(searchForWord(connection, english_word));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    protected void updateRecentSearches (Connection connection, String word, int userID) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO recent_searches (wordID, userID) values " +
                    "((SELECT wordID FROM words WHERE word = '" + word + "'), " + userID + ");";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected int getRecentSearchesCount (Connection connection, int userID) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT COUNT(*) as total FROM recent_searches GROUP BY userID;";
            ResultSet rs = statement.executeQuery(query);
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected void cleanRecentSearches (Connection connection, int userID) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM recent_searches WHERE searchID = " +
                    "(SELECT MIN(searchID) FROM recent_searches) AND userID =" + userID + ";";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected List<StandardWord> getWordAtDay (Connection connection, String date) {
        List<StandardWord> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT w.word as word " +
                    "FROM words w, daily d " +
                    "WHERE d.day = '" + date + "' AND " +
                    "(d.wordID1 = w.wordID OR d.wordID2 = w.wordID OR d.wordID3 = w.wordID);";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("word");
                res.add(searchForWord(connection, english_word));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    protected void updateWordToday (Connection connection, String date,
                                    String word1, String word2, String word3) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO daily (day, wordID1, wordID2, wordID3) values " +
                    "('" + date + "', (SELECT wordID FROM words WHERE word = '" + word1 + "')," +
                    " (SELECT wordID FROM words WHERE word = '" + word2 + "')," +
                    " (SELECT wordID FROM words WHERE word = '" + word3 + "'));";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String getMostRecentDailyVisit (Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT day FROM daily WHERE dayID = (SELECT MAX(dayID) FROM daily);";
            ResultSet rs = statement.executeQuery(query);
            return rs.getString("day");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
