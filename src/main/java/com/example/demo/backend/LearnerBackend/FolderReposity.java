package com.example.demo.backend.LearnerBackend;

import com.example.demo.ScreenManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FolderReposity {

    public FolderReposity()
    {

    }

    public void addNewFolder(String folderName) {
        try {
            String insertQuery = "INSERT INTO folder (name,userId) VALUES (?,?)";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(insertQuery);
            preparedStatement.setString(1, folderName);
            preparedStatement.setInt(2, ScreenManager.getInstance().getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRecentFolder(int folderId) {
        try {
            String insertQuery = "INSERT INTO HistoryFolder (folderId) VALUES (?)";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(insertQuery);
            preparedStatement.setInt(1, folderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllFolder(int userId) {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder where userId = " + userId;
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

    public String getAllFolderTest(int userId) {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder where userId = " + userId;
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


    public String getRecentFolder(int userId) {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT DISTINCT f.name, f.id\n" +
                    "FROM folder f\n" +
                    "JOIN HistoryFolder hf ON hf.folderId = f.id\n" +
                    "where f.userId = " + userId + "\n" +
                    "ORDER BY hf.historyId DESC\n" +
                    "LIMIT 4;\n";
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

    public String getTwoFoldersRandom(int userId) {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder WHERE userId = " + userId + " ORDER BY RAND() LIMIT 2;";
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

    public String getLastRecentFolderName(int userId)
    {
        String res = "";
        try {
            String query = "SELECT f.name FROM folder f JOIN HistoryFolder h ON h.folderId = f.id  WHERE h.historyId = ( SELECT MAX(historyId) FROM HistoryFolder WHERE folderId IN ( SELECT id FROM folder WHERE userId = " + userId + " ))";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                res = resultSet.getString("name");
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve folder data";
        }
    }


    public void removeRecentFolde(int userId)
    {
        try {
            String deleteQuery = "DELETE h1 " +
                    "FROM HistoryFolder h1 " +
                    "JOIN folder f ON h1.folderId = f.id " +
                    "LEFT JOIN ( " +
                    "    SELECT h2.historyId " +
                    "    FROM HistoryFolder h2 " +
                    "    JOIN folder f2 ON f2.id = h2.folderId " +
                    "    WHERE f2.userId = ? " +
                    "    ORDER BY h2.historyId DESC " +
                    "    LIMIT 4 " +
                    ") AS subquery ON h1.historyId = subquery.historyId " +
                    "WHERE f.userId = ? AND subquery.historyId IS NULL";
            PreparedStatement preparedStatement = Connect.getInstance().connect().prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            int cnt = preparedStatement.executeUpdate();
            if(cnt>0)
            {
                System.out.println("Đã xóa " + cnt + " recent folder");
            }
            else
            {
                System.out.println("chưa đủ recent folder để xóa");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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

    public int getFolderId(String folderName) {
        try {
            int result = -1;
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT id FROM folder where name=" + "'" + folderName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result = resultSet.getInt("id");
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean canToAddFolder(int userId) {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder where userId = " + userId;
            ResultSet resultSet = statement.executeQuery(query);
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

    public boolean folderIsExist(int userId, String folderName) {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder where userId = " + userId +" and name = '" + folderName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            int cnt = 0;
            while (resultSet.next()) {
                cnt++;
            }
            if (cnt > 0) {
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

    public String getRecentFoldersForDict(int userId)
    {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT DISTINCT f.name, f.id\n" +
                    "FROM folder f\n" +
                    "JOIN HistoryFolder hf ON hf.folderId = f.id\n" +
                    "where f.userId = " + userId + "\n" +
                    "ORDER BY hf.historyId DESC\n" +
                    "LIMIT 4;\n";
            ResultSet resultSet = statement.executeQuery(query);
            StringBuilder res = new StringBuilder();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.print(name + " ");
                res.append(name).append(" ");
            }
            return res.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve folder data";
        }
    }

    public String getAllFoldersForDict(int userId)
    {
        try {
            Statement statement = Connect.getInstance().connect().createStatement();
            String query = "SELECT * FROM folder where userId = " + userId;
            ResultSet resultSet = statement.executeQuery(query);
            StringBuilder res = new StringBuilder();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(name + " ");
                res.append(name).append(" ");
            }
            return res.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to retrieve folder data";
        }
    }
}
