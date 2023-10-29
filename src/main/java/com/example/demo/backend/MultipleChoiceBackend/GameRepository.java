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
            String query = "";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String question = rs.getString("");
                List<String> options = new ArrayList<>();
                int rightOption = rs.getInt("");
                String dif = rs.getString("");
                Question ques = new Question(question, options, rightOption, dif);
                questions.add(ques);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
