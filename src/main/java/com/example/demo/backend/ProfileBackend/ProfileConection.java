package com.example.demo.backend.ProfileBackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProfileConection {
    private static ProfileConection _instance = null;

    private ProfileConection() {

    }

    public static ProfileConection getInstance() {
        if (_instance == null) {
            _instance = new ProfileConection();

        }
        return _instance;
    }

    private static Connection connection;
    private String DATABASE_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12661833";

    private String user = "sql12661833";

    private String password = "Siht7VcMkf";

    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, user, password);;
                System.out.println("Connected to SQLite database" + DATABASE_URL);
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

}