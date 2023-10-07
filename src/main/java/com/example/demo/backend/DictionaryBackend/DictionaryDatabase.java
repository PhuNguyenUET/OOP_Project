package com.example.demo.backend.DictionaryBackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DictionaryDatabase {
    private static DictionaryDatabase _instance = null;

    protected DictionaryDatabase() {
    }

    public static DictionaryDatabase Instance() {
        if (_instance == null) {
            _instance = new DictionaryDatabase();
            connect();
        }
        return _instance;
    }

    private static Connection connection;
    private static final String DATABASE_URL = "src/main/resources/database/Dictionary.db"; // Thay thế bằng đường dẫn tới tệp cơ sở dữ liệu của bạn.

    private static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_URL);
            System.out.println("Connected to SQLite database");
        } catch (SQLException e) {
            System.err.println("Error connecting to SQLite database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}