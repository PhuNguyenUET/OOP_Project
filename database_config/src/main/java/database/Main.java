package database;

import database.DictionaryManagement;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection connection = Connect.connect();
        // String input = "action";
        
        // try {
        //     Statement statement = connection.createStatement();
        //     String query = "SELECT * FROM words WHERE word = \'" + input + "\'";
        //     ResultSet resultSet = statement.executeQuery(query);
        //     String word = "";
        //     String pronunciation = "";
        //     List<Explanation> explanations = new ArrayList<>();
        //     List<String> types = new ArrayList<>();
        //     List<String> explain = new ArrayList<>();
        //     if (resultSet.next()) {
        //         word = resultSet.getString("word");
        //         pronunciation = resultSet.getString("pronunciation");
        //     }
            
        //     query = "SELECT * FROM explanation WHERE word = \'" + input + "\'";
        //     resultSet = statement.executeQuery(query);
        //     while (resultSet.next()) {
        //         String type = resultSet.getString("type");
        //         String explanation = resultSet.getString("explanation");
        //         // String q = "SELECT * FROM phrases WHERE word = \'" + word + "\' AND wordExplanation = \'" + explanation +"\'";
        //         // ResultSet rs = statement.executeQuery(q);
        //         // List<String> phrases = new ArrayList<>();
        //         // while (rs.next()) {
        //         //     // List<String> phrases = new ArrayList<>();
        //         //     String phrase = rs.getString("phrase");
        //         //     String phraseExplanation = rs.getString("phraseExplanation");
        //         //     phrases.add(phrase + ": " + phraseExplanation);
        //         // }
        //         // Explanation ex = new Explanation.ExplanationBuilder(explanation).setWordType(type).setExamples(phrases).build();
        //         // explanations.add(ex);
        //         // System.out.println(type + " " + explanation);
        //         types.add(type);
        //         explain.add(explanation);
        //     }

        //     for (int i = 0; i < explain.size(); i++) {
        //         query = "SELECT * FROM phrases WHERE word = \'" + word + "\' AND wordExplanation = \'" + explain.get(i) +"\'";
        //         resultSet = statement.executeQuery(query);
        //         List<String> phrases = new ArrayList<>();
        //         while (resultSet.next()) {
        //             String phrase = resultSet.getString("phrase");
        //             String phraseExplanation = resultSet.getString("phraseExplanati on");
        //             phrases.add(phrase + ": " + phraseExplanation);
        //         }
        //         Explanation ex = new Explanation.ExplanationBuilder(explain.get(i)).setWordType(types.get(i)).setExamples(phrases).build();
        //         explanations.add(ex);
        //     }

        //     for (Explanation exs : explanations) {
        //         System.out.println(exs.getWord_type() + " " + exs.getDefinition());
        //         for (String ph : exs.getExamples()) {
        //             System.out.println("\t" + ph);
        //         }
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        GetWordList.getWordList(connection, null);
        // FileManagement.test();
    }
}