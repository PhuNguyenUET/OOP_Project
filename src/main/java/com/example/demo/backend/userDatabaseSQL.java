package com.example.demo.backend;

import java.sql.*;

public class userDatabaseSQL {
    public static void createtable(Connection connection)
    {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE information (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username VARCHAR(20), " +
                    "password VARCHAR(20), " +
                    "profilePictureId INTEGER" +
                    ")";
            statement.execute(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void showTable(Connection connection)
    {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            // Bước 3: Xử lý kết quả truy vấn và hiển thị danh sách các bảng
            System.out.println("Các bảng trong cơ sở dữ liệu:");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoDict(Connection connection,String username, String password, int profilePictureId){
        try {
            String insertQuery="INSERT INTO information (username,password,profilePictureId) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, profilePictureId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showAll(Connection connection){
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM information";
            ResultSet resultSet = statement.executeQuery(query);
            int cnt=0;
            while (resultSet.next()) {
                cnt++;
                String username=resultSet.getString("username");
                String password=resultSet.getString("password");
                String profilePictureId=resultSet.getString("profilePictureId");
                System.out.println(username + " " + password + " " + profilePictureId);
            }
            System.out.println("Số dòng : " + cnt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean check(Connection connection,String username,String password)
    {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM information as i Where i.username=" + "'"+username+"'";
            ResultSet resultSet = statement.executeQuery(query);
            String passwordRes="";
            while (resultSet.next()) {
                passwordRes=resultSet.getString("password");
            }
            return password.equals(passwordRes);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isUsernameExists(Connection connection, String username) {
        try {
            String query = "SELECT COUNT(*) FROM information WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Trả về true nếu tên người dùng tồn tại, ngược lại trả về false.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi xảy ra hoặc không tìm thấy tên người dùng, trả về false.
    }
}
