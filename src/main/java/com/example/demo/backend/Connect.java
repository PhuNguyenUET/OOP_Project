package com.example.demo.backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection connection;
    private static String DATABASE_URL = "src/main/resources/database/Dictionary.db"; // Thay thế bằng đường dẫn tới tệp cơ sở dữ liệu của bạn.
    public static Connection connect() {
        if (connection == null) {
            try {
//                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_URL);
                System.out.println("Connected to SQLite database");
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database.");
                e.printStackTrace();
            }
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
        }
        return connection;
    }
}