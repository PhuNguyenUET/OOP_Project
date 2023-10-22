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
            String query= "SELECT english_word FROM dictionary "
                    + " WHERE english_word LIKE " + " '" + input + "%' LIMIT 8";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("english_word");
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
            String query = "SELECT * FROM dictionary " + " WHERE english_word = " + " '" + input + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String english_word=rs.getString("english_word");
                String word_type=rs.getString("word_type");
                String pronunciation=rs.getString("pronunciation");
                String vietnamese_word=rs.getString("vietnamese_meaning");
                List<Explanation> explanations = new ArrayList<>();
                Explanation ex1 = new Explanation.ExplanationBuilder(vietnamese_word).setWordType(word_type).build();
                explanations.add(ex1);
                return new StandardWord(english_word, pronunciation, explanations);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
