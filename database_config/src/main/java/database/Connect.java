package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection connection;
    private static final String DATABASE_URL = "database_config\\resources\\dictionary.db"; // Thay thế bằng đường dẫn tới tệp cơ sở dữ liệu của bạn.
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