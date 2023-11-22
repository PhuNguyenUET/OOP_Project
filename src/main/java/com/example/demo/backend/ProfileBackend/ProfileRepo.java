package com.example.demo.backend.ProfileBackend;

import com.example.demo.backend.LearnerBackend.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepo {
    public void insertToSession(double time, LocalDate date, int userId) {
        try {
            String sql = "INSERT INTO session (date, time, userId) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(sql);
            preparedStatement.setDouble(2, time);
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTimeForDate(double time, LocalDate date, int userId) {
        String sql = "UPDATE session SET time = ? WHERE date = ? AND userId = ?";

        try {

            Connection connection = Connect.getInstance().connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, time);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));
            preparedStatement.setInt(3, userId);

            int cnt = preparedStatement.executeUpdate();

            if (cnt > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("No record found for the specified date and userId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<TimeUsage> getRecentTimeUsage(int userId) {
        List<TimeUsage> timeUsages = new ArrayList<>();
        try {
            String query = "SELECT date, SUM(time) AS total_time FROM session WHERE userId = ? GROUP BY date ORDER BY date DESC LIMIT 4";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet dataFetch = preparedStatement.executeQuery();
            while (dataFetch.next()) {
                String dateData = dataFetch.getString("date");
                double timeData = dataFetch.getFloat("total_time");
                timeUsages.add(new TimeUsage(dateData, timeData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeUsages;
    }

    public double getRecentTimeUsageOfDate(LocalDate date,int userId) {
        double res = -1;
        try {
            String query = "SELECT time AS total_time FROM session WHERE date = ? and userId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            preparedStatement.setDate(1,java.sql.Date.valueOf(date));
            preparedStatement.setInt(2, userId);
            ResultSet dataFetch = preparedStatement.executeQuery();
            while (dataFetch.next()) {
                res = dataFetch.getDouble("total_time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean checkDateIsExist(LocalDate date, int userId) {
        boolean check = false;
        try {
            String query = "SELECT COUNT(*) count FROM session WHERE date = ? AND userId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int cnt = 0;
            if (resultSet.next()) {
                cnt = resultSet.getInt("count");
                if (cnt > 0) {
                    check = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static void main(String[] args) {
        ProfileRepo profileRepo = new ProfileRepo();
        List<TimeUsage> list = profileRepo.getRecentTimeUsage(5);
        for (TimeUsage timeUsage : list) {
            System.out.println(timeUsage.getDate() + "    " + timeUsage.getTime());
        }
    }
}
