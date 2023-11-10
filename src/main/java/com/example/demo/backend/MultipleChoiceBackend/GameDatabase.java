package com.example.demo.backend.MultipleChoiceBackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class GameDatabase {
    private static GameDatabase _instance = null;

    private GameDatabase() {
    }

    protected static GameDatabase Instance() {
        if (_instance == null) {
            _instance = new GameDatabase();
            connect();
        }
        return _instance;
    }

    private static Connection connection;
    private static final String DATABASE_URL = "src/main/resources/database/multiplechoice.db"; // Thay thế bằng đường dẫn tới tệp cơ sở dữ liệu của bạn.

    private static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_URL);
            System.out.println("Connected to SQLite database");
        } catch (SQLException e) {
            System.err.println("Error connecting to SQLite database.");
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        return connection;
    }
}
