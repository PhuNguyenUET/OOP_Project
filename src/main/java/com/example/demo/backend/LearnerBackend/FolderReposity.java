package com.example.demo.backend.LearnerBackend;

import com.example.demo.backend.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FolderReposity {

    public static FolderReposity instance_;

    private FolderReposity() {
    }

    public static FolderReposity getInstance() {
        if (instance_ == null) {
            instance_ = new FolderReposity();
        }
        return instance_;
    }

    public void addNewFolder(String folderName) {
        try {
            String insertQuery = "INSERT INTO folder (name) VALUES (?)";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(insertQuery);
            preparedStatement.setString(1, folderName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllFolder() {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder";
            ResultSet resultSet = statement.executeQuery(query);
            String res = "[";
            int cnt = 0;
            while (resultSet.next()) {
                cnt++;
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                System.out.println(id + " " + name);
                res += "{" + "id: " + id + "," + "name: " + name + "}";
            }
            res += "]";
            System.out.println("Số dòng : " + cnt);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve folder data";
        }
    }

    public String getAllFolderTest() {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder";
            ResultSet resultSet = statement.executeQuery(query);
            List<String> folderList = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String id = resultSet.getString("id");
                System.out.println(id + " " + name);
                String folderJson = "{\"name\": \"" + name + "\", \"id\": \"" + id + "\"}";
                folderList.add(folderJson);
            }
            System.out.println("Số dòng: " + folderList.size());

            // Ghép các đối tượng JSON trong danh sách vào một mảng JSON
            String res = "[" + String.join(",", folderList) + "]";
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve folder data";
        }
    }


    public boolean removeFolder(String folderName) {
        try {
            String deleteQuery = "DELETE FROM folder WHERE name = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setString(1, folderName);
            int rowsAffected = preparedStatement.executeUpdate();
            boolean check = true;
            if (rowsAffected > 0) {
                System.out.println("Thư mục '" + folderName + "' đã bị xóa thành công.");
            } else {
                check = false;
                System.out.println("Không tìm thấy thư mục có tên '" + folderName + "'.");
            }
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeFolder(int folderId) {
        try {
            String deleteQuery = "DELETE FROM folder WHERE id = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, folderId);
            int rowsAffected = preparedStatement.executeUpdate();
            boolean check = true;
            if (rowsAffected > 0) {
                System.out.println("Thư mục '" + folderId + "' đã bị xóa thành công.");
            } else {
                check = false;
                System.out.println("Không tìm thấy thư mục có tên '" + folderId + "'.");
            }
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getFolderName(int id) {
        try {
            String result = "";
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT name FROM folder where id=" + id;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result = resultSet.getString("name");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean canToAddFolder() {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder";
            ResultSet resultSet = statement.executeQuery(query);
            String res = "[";
            int cnt = 0;
            while (resultSet.next()) {
                cnt++;
            }
            if (cnt < 10) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeFolderName(int id, String newName) {
        try {
            if (newName.equals("")) {
                return false;
            }
            Connection connection = Connect.getInstance().connect();

            String updateQuery = "UPDATE folder SET name = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
