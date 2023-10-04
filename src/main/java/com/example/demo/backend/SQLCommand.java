package com.example.demo.backend;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCommand {
    public static void processFileData(Connection connection, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách dữ liệu thành từ, nghĩa, loại từ, và phát âm
                String[] parts = line.split(" ");
                if (parts.length >= 4) {
                    String word = parts[0];
                    String wordType = parts[1];
                    String pronunciation = parts[2];
                    String meaning = "";
                    for (int i = 3; i < parts.length; i++) {
                        meaning+=parts[i];
                        if (i < parts.length - 1) {
                            meaning+=" "; // Thêm dấu cách nếu không phải phần cuối
                        }
                    }
                    // Tạo truy vấn INSERT
                    String insertQuery = "INSERT INTO dictionary (english_word, vietnamese_meaning, word_type, pronunciation) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, word);
                        preparedStatement.setString(2, meaning);
                        preparedStatement.setString(3, wordType);
                        preparedStatement.setString(4, pronunciation);
                        int rowEffect=preparedStatement.executeUpdate();
                        System.out.println("Số lượng hàng được thêm vào" + rowEffect);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoDict(Connection connection,String english_word, String vietnamese_meaning, String word_type,String pronunciation){
        try {
            String insertQuery="INSERT INTO dictionary (english_word,word_type,pronunciation,vietnamese_meaning) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, english_word);
            preparedStatement.setString(2, word_type);
            preparedStatement.setString(3, pronunciation);
            preparedStatement.setString(4, vietnamese_meaning);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromDict(Connection connection,String english_word)
    {
        try{
            String query="DELETE FROM dictionary WHERE english_word = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, english_word);
            int rowEffective = statement.executeUpdate();
            System.out.println("Num of rows delete: " + rowEffective);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void searchAll(Connection connection,String tableName,String input)
    {
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT * FROM dictionary "   + " WHERE english_word LIKE " + " '" + input + "%'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String english_word=rs.getString("english_word");
                String word_type=rs.getString("word_type");
                String pronunciation=rs.getString("pronunciation");
                String vietnamese_word=rs.getString("vietnamese_meaning");
                System.out.println(english_word+" "+word_type+" "+pronunciation+" "+vietnamese_word);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEnglishWord(Connection connection,String tableName,String inputVal, String outputVal)
    {
        try{
            String updateQuery= "UPDATE " + tableName + " SET english_word= ? where english_word = ? ";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1,outputVal);
            statement.setString(2,inputVal);
            int rowEffective=statement.executeUpdate();
            System.out.println(rowEffective);
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateVienameseWord(Connection connection,String tableName,String inputVal, String outputVal)
    {
        try{
            String updateQuery= "UPDATE " + tableName + " SET vietnamese_meaning= ? where vietnamese_meaning = ? ";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1,outputVal);
            statement.setString(2,inputVal);
            int rowEffective=statement.executeUpdate();
            System.out.println(rowEffective);
        } catch(SQLException e)
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

    public static void showAll(Connection connection){
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM dictionary";
            ResultSet resultSet = statement.executeQuery(query);
            int cnt=0;
            while (resultSet.next()) {
                cnt++;
                String english_word=resultSet.getString("english_word");
                String vietnamese_word=resultSet.getString("vietnamese_meaning");
                String word_type=resultSet.getString("word_type");
                String pronunciation=resultSet.getString("pronunciation");
                System.out.println(english_word + " " + word_type + " " + pronunciation + " " + vietnamese_word);
            }
            System.out.println("Số dòng : " + cnt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll(Connection connection,String tableName){
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + tableName;
            int rowEffect=statement.executeUpdate(query);
            System.out.println("Số dòng đã bị xóa: " + rowEffect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exportData(Connection connection, String filePath) {
        try{
            Statement statement = connection.createStatement();
            String query = "Select * from dictionary" ;
            ResultSet resultSet= statement.executeQuery(query);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                while (resultSet.next()) {
                    // Đọc dữ liệu từ cơ sở dữ liệu
                    String english_word=resultSet.getString("english_word");
                    String vietnamese_word=resultSet.getString("vietnamese_meaning");
                    String word_type=resultSet.getString("word_type");
                    String pronunciation=resultSet.getString("pronunciation");
                    String data=english_word + " " + word_type + " " + pronunciation + " " + vietnamese_word;
                    // Ghi dữ liệu vào tập tin
                    fileWriter.write(data);
                    fileWriter.write("\n"); // Để tạo dòng mới cho mỗi dòng dữ liệu
                }
                System.out.println("Dữ liệu đã được ghi vào " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
