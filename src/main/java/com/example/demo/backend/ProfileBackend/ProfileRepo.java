package com.example.demo.backend.ProfileBackend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepo{
    public void insertToSession(double time, LocalDate date, int userId)
    {
        try{
            String sql = "INSERT INTO session (date, time, userId) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = ProfileConection.getInstance().connect().prepareStatement(sql);
            preparedStatement.setDouble(2, time);
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<TimeUsage> getRecentTimeUsage(int userId)
    {
        List<TimeUsage> timeUsages = new ArrayList<>();
        try{
            String query = "SELECT date, SUM(time) AS total_time FROM session WHERE DATEDIFF(CURDATE(), date) <= 4 and userId = ? GROUP BY date";
            PreparedStatement preparedStatement = ProfileConection.getInstance().connect().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet dataFetch = preparedStatement.executeQuery();
            while (dataFetch.next())
            {
                String dateData = dataFetch.getString("date");
                double timeData = dataFetch.getFloat("total_time");
                timeUsages.add(new TimeUsage(dateData,timeData));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return timeUsages;
    }

    public boolean checkDateIsExist(LocalDate date, int userId)
    {
        boolean check = false;
        try{
            String query = "SELECT COUNT(*) count FROM session WHERE date = ? AND userId = ?";
            PreparedStatement preparedStatement = ProfileConection.getInstance().connect().prepareStatement(query);
            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int cnt = 0;
            if(resultSet.next())
            {
                cnt = resultSet.getInt("count");
                if(cnt>0)
                {
                    check =true;
                }
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return check;
    }
}
