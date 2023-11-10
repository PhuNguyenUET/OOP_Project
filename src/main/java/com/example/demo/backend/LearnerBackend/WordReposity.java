package com.example.demo.backend.LearnerBackend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordReposity {
    private static WordReposity instance_;

    private WordReposity() {
    }

    public static WordReposity getInstance() {
        if (instance_ == null) {
            instance_ = new WordReposity();
        }
        return instance_;
    }

    public boolean addNewList(String word, String type, String definition, int listId) {
        try {
            if (word.equals("") || type.equals("") || definition.equals("")) {
                return false;
            }
            String insertQuery = "INSERT INTO UserWord (word,type,definition,listId) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(insertQuery);
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, definition);
            preparedStatement.setInt(4, listId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getAllWordFromList(int listId) {
        try {
            String query = "SELECT * FROM UserWord WHERE listId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            preparedStatement.setInt(1, listId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> listData = new ArrayList<>();

            while (resultSet.next()) {
                String word = resultSet.getString("word");
                String type = resultSet.getString("type");
                String pronunciation = resultSet.getString("pronunciation");
                String definition = resultSet.getString("definition");
                int wordId = resultSet.getInt("id");
                String wordJson = String.format("{\"id\": %d, \"word\": \"%s\", \"type\": \"%s\", \"pronunciation\": \"%s\", \"definition\": \"%s\"}", wordId, word, type, pronunciation, definition);
                listData.add(wordJson);
            }
            System.out.println("Số dòng: " + listData.size());
            String res = "[" + String.join(",", listData) + "]";
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve words from the list";
        }
    }

    public boolean removeAllListInFolder(int listId) {
        try {
            String deleteQuery = "DELETE FROM UserWord WHERE listId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, listId);
            int rowsAffected = preparedStatement.executeUpdate();
            boolean check = true;
            if (rowsAffected > 0) {
                System.out.println("Danh sách trong thư mục có folderId = " + listId + " đã bị xóa thành công.");
                System.out.println("Tổng số từ bị xoá: " + rowsAffected);
            } else {
                check = false;
                System.out.println("Không tìm thấy danh sách có trong thư mục có folderId = " + listId + ".");
            }
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeWordWithId(int id) {
        try {
            String deleteQuery = "DELETE FROM UserWord WHERE id = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            boolean check = true;
            if (rowsAffected > 0) {
                System.out.println("List có id = " + id + " đã bị xóa thành công.");
            } else {
                check = false;
                System.out.println("Không tìm List có id = " + id + ".");
            }
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getThreeWordFromList() {
        try {
            String query = "SELECT * FROM UserWord Order by id desc limit 3";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> listData = new ArrayList<>();

            while (resultSet.next()) {
                String word = resultSet.getString("word");
                String type = resultSet.getString("type");
                String pronunciation = resultSet.getString("pronunciation");
                String definition = resultSet.getString("definition");
                int wordId = resultSet.getInt("id");
                String wordJson = String.format("{\"id\": %d, \"word\": \"%s\", \"type\": \"%s\", \"pronunciation\": \"%s\", \"definition\": \"%s\"}", wordId, word, type, pronunciation, definition);
                listData.add(wordJson);
            }
            System.out.println("Số dòng: " + listData.size());
            String res = "[" + String.join(",", listData) + "]";
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve words from the list";
        }
    }

}
