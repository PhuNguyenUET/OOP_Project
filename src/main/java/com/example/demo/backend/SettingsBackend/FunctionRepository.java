package com.example.demo.backend.SettingsBackend;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FunctionRepository {
    public boolean isPasswordCorrect(Connection connection, int userID, String password) {
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT password FROM information WHERE id = " + userID;
            ResultSet rs = statement.executeQuery(query);
            String pass = "";
            if (rs.next()){
                pass = rs.getString("password");
            }
            return pass.equals(password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean containsUsername(Connection connection, String username) {
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT id FROM information WHERE username = '" + username + "';";
            ResultSet rs = statement.executeQuery(query);
            if (rs.isBeforeFirst() ) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updatePassword(Connection connection, int userID, String newPassword) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE information SET password = '" + newPassword + "' WHERE id = " + userID;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName(Connection connection, int id) {
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT name FROM information WHERE id = " + id;
            ResultSet rs = statement.executeQuery(query);
            String name = "";
            if (rs.next()){
                name = rs.getString("name");
            }
            if (name == null) {
                return "User";
            }
            return name;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getUserName(Connection connection, int id) {
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT username FROM information WHERE id = " + id;
            ResultSet rs = statement.executeQuery(query);
            String username = "";
            if (rs.next()){
                username = rs.getString("username");
            }
            return username;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getProfileID(Connection connection, int userID) {
        try{
            Statement statement = connection.createStatement();
            String query= "SELECT profilePictureId FROM information WHERE id = " + userID;
            ResultSet rs = statement.executeQuery(query);
            int picID = -1;
            if (rs.next()){
                picID = rs.getInt("profilePictureId");
            }
            return picID;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateUsername(Connection connection, int userID, String newUserName) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE information SET username = '" + newUserName + "' WHERE id = " + userID;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateName(Connection connection, int userID, String newName) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE information SET name = '" + newName + "' WHERE id = " + userID;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProfilePicture(Connection connection, int userID, int profilePictureID) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE information SET profilePictureId = '" + profilePictureID + "' WHERE id = " + userID;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
