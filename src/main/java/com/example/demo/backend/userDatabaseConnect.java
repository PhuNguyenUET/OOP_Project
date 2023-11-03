package com.example.demo.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class userDatabaseConnect {
    private static Connection connection;
    private static String DATABASE_URL = "src/main/resources/database/userInfor.db";
    public static Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_URL);
                System.out.println("Connected to SQLite database");
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
