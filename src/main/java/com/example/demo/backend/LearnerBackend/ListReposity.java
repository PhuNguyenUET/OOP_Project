package com.example.demo.backend.LearnerBackend;

import com.example.demo.backend.Connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListReposity {
    public static ListReposity instance_;

    private ListReposity() {
    }

    public static ListReposity getInstance() {
        if (instance_ == null) {
            instance_ = new ListReposity();
        }
        return instance_;
    }

    public void addNewList(String listName, int id) {
        try {
            String insertQuery = "INSERT INTO UserList (name,folderId) VALUES (?,?)";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(insertQuery);
            preparedStatement.setString(1, listName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getAllListFromFolderTest(int folderId) {
        try {
            String query = "SELECT * FROM UserList WHERE folderId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            preparedStatement.setInt(1, folderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> listData = new ArrayList<>();

            while (resultSet.next()) {
                int ListId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println(ListId + " " + name);
                String listJson = String.format("{\"id\": %d, \"name\": \"%s\", \"folderId\": %d}", ListId, name, folderId);
                listData.add(listJson);
            }

            System.out.println("Số dòng: " + listData.size());

            String res = "[" + String.join(",", listData) + "]";
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve lists from the folder";
        }
    }

    public boolean removeListInFolder(String listName, int folderId) {
        try {
            String deleteQuery = "DELETE FROM UserList WHERE name = ? AND id = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setString(1, listName);
            preparedStatement.setInt(2, folderId);
            int rowsAffected = preparedStatement.executeUpdate();
            boolean check = true;
            if (rowsAffected > 0) {
                System.out.println("Danh sách '" + listName + "' trong thư mục có folderId = " + folderId + " đã bị xóa thành công.");
            } else {
                check = false;
                System.out.println("Không tìm thấy danh sách có tên '" + listName + "' trong thư mục có folderId = " + folderId + ".");
            }
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeAllListInFolder(int folderId) {
        try {
            String deleteQuery = "DELETE FROM UserList WHERE folderId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, folderId);
            int rowsAffected = preparedStatement.executeUpdate();
            boolean check = true;
            if (rowsAffected > 0) {
                System.out.println("Danh sách trong thư mục có folderId = " + folderId + " đã bị xóa thành công.");
            } else {
                check = false;
                System.out.println("Không tìm thấy danh sách có trong thư mục có folderId = " + folderId + ".");
            }
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeListWithId(int id) {
        try {
            String deleteQuery = "DELETE FROM UserList WHERE id = ?";
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

    public boolean canAddNewListIntoFolder(int folderId) {
        try {
            String query = "SELECT COUNT(*) AS count FROM UserList WHERE folderId = ?";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            preparedStatement.setInt(1, folderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int cnt = 0;

            if (resultSet.next()) {
                cnt = resultSet.getInt("count");
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

    public boolean changeListName(int id, String newName) {
        try {
            if (newName.equals("")) {
                return false;
            }
            Connection connection = Connect.getInstance().connect();

            String updateQuery = "UPDATE UserList SET name = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Can not rename");
            }
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getListName(int id) {
        try {
            String result = "";
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT name FROM UserList where id=" + id;
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
}
