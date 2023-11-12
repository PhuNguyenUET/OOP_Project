package com.example.demo.backend.FlipGameBackend;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FlipGameDatabase {
    private static FlipGameDatabase _instance = null;

    private FlipGameDatabase()
    {
    }

    public static FlipGameDatabase getInstance()
    {
        if(_instance==null)
        {
            _instance = new FlipGameDatabase();
        }
        return _instance;
    }

    private static Connection connection;
    private String DATABASE_URL = "src/main/resources/database/FlipGame.db"; // Thay thế bằng đường dẫn tới tệp cơ sở dữ liệu của bạn.
    public Connection connect() {
        Connection connection = null;
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_URL);
                System.out.println("Connected to SQLite database" + DATABASE_URL);
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
