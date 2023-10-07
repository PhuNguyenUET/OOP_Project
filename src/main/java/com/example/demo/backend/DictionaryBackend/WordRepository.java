package com.example.demo.backend.DictionaryBackend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    public static List<StandardWord> searchSimilar(Connection connection, String input)
    {
        List<StandardWord> similarList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT * FROM dictionary "   + " WHERE english_word LIKE " + " '" + input + "%'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("english_word");
                String word_type=rs.getString("word_type");
                String pronunciation=rs.getString("pronunciation");
                String vietnamese_word=rs.getString("vietnamese_meaning");
                StandardWord word =  new StandardWord.WordBuilder(english_word, vietnamese_word).
                        setWordType(word_type).setPronunciation(pronunciation).build();
                similarList.add(word);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return similarList;
    }

    public static StandardWord searchForWord(Connection connection, String input) {
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM dictionary " + " WHERE english_word = " + " '" + input + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String english_word=rs.getString("english_word");
                String word_type=rs.getString("word_type");
                String pronunciation=rs.getString("pronunciation");
                String vietnamese_word=rs.getString("vietnamese_meaning");
                StandardWord word =  new StandardWord.WordBuilder(english_word, vietnamese_word).
                        setWordType(word_type).setPronunciation(pronunciation).build();
                return word;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
