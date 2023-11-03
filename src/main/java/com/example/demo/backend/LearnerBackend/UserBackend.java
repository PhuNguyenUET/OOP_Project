package com.example.demo.backend.LearnerBackend;

import com.example.demo.backend.Connect;

import java.sql.*;

public class UserBackend {
    public int getIdByName(String name)
    {
        try {
            int userId = -1;
            Connection connection = Connect.getInstance().connect();
            String query = "SELECT id FROM information WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name); // Gán giá trị cho tham số trong truy vấn

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
