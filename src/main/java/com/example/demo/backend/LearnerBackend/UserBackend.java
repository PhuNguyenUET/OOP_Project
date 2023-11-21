package com.example.demo.backend.LearnerBackend;

import com.example.demo.backend.ProfileBackend.ProfileConection;

import java.sql.*;
import java.time.LocalDate;

public class UserBackend {
    public int getIdByName(String name) {
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

    public String getNameById(int id) {
        try {
            String res = "";
            Connection connection = Connect.getInstance().connect();
            String query = "SELECT name FROM information WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); // Gán giá trị cho tham số trong truy vấn

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getString("name");
            }
            resultSet.close();
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void insertIntoInformation(Connection connection, String username, String password, int profilePictureId) {
        try {
            String insertQuery = "INSERT INTO information (username,password,profilePictureId,name) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, profilePictureId);
            preparedStatement.setString(4, "User");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoStreak(Connection connection, int streak, int userId) {
        try {
            String insertQuery = "INSERT INTO streak (streak,userId) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, streak);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStreak(Connection connection, int streak, int userId) {
        try {
            String updateQuery = "UPDATE streak SET streak = ? WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, streak);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStreak(Connection connection, int userId) {
        try {
            String query = "select streak from streak WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt("streak");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean checkYesterdayLogin(Connection connection, int userId) {
        boolean check = false;
        try {
            // Lấy ngày hôm qua
            LocalDate yesterday = LocalDate.now().minusDays(1);

            // Truy vấn kiểm tra xem có bản ghi nào cho userId vào ngày hôm qua không
            String query = "SELECT COUNT(*) AS count FROM session WHERE date = ? AND userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, java.sql.Date.valueOf(yesterday));
            preparedStatement.setInt(2, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                check = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
    public boolean check(Connection connection, String username, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM information as i Where i.username=" + "'" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);
            String passwordRes = "";
            while (resultSet.next()) {
                passwordRes = resultSet.getString("password");
            }
            return password.equals(passwordRes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsernameExists(Connection connection, String username) {
        try {
            String query = "SELECT COUNT(*) FROM information WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
