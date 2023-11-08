package com.example.demo.backend.MultipleChoiceBackend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class GameRepository {
    public List<Question> getQuestionsForTest(Connection connection, String difficulty) {
        List<Question> questions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM " + difficulty.toLowerCase() + " ORDER BY RANDOM() LIMIT 10";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                //Get question
                String question = rs.getString("question");

                //Get options
                List<String> options = new ArrayList<>();
                options.add(rs.getString("A"));
                options.add(rs.getString("B"));
                options.add(rs.getString("C"));
                options.add(rs.getString("D"));

                //Get answer to question
                String ans = rs.getString("answer");
                int rightOption = 0;
                switch (ans) {
                    case "A" -> rightOption = 1;
                    case "B" -> rightOption = 2;
                    case "C" -> rightOption = 3;
                    case "D" -> rightOption = 4;
                }
                Question ques = new Question(question, options, rightOption, difficulty);
                questions.add(ques);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
