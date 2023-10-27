package com.example.demo.backend.DictionaryBackend;

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
            String query = "SELECT * FROM words WHERE word = \'" + input + "\'";
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
            
            query = "SELECT * FROM explanation WHERE word = \'" + input + "\'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String explanation = resultSet.getString("explanation");
                // String q = "SELECT * FROM phrases WHERE word = \'" + word + "\' AND wordExplanation = \'" + explanation +"\'";
                // ResultSet rs = statement.executeQuery(q);
                // List<String> phrases = new ArrayList<>();
                // while (rs.next()) {
                //     // List<String> phrases = new ArrayList<>();
                //     String phrase = rs.getString("phrase");
                //     String phraseExplanation = rs.getString("phraseExplanation");
                //     phrases.add(phrase + ": " + phraseExplanation);
                // }
                // Explanation ex = new Explanation.ExplanationBuilder(explanation).setWordType(type).setExamples(phrases).build();
                // explanations.add(ex);
                // System.out.println(type + " " + explanation);
                types.add(type);
                explain.add(explanation);
            }

            for (int i = 0; i < explain.size(); i++) {
                query = "SELECT * FROM phrases WHERE word = \'" + word + "\' AND wordExplanation = \'" + explain.get(i) +"\'";
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
            return new StandardWord(word, pronunciation, explanations);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
