package com.example.demo.backend;

import com.example.demo.backend.LearnerBackend.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class userDatabaseConnect {
    private static userDatabaseConnect _instance = null;

    private userDatabaseConnect() {

    }

    public static userDatabaseConnect getInstance() {
        if (_instance == null) {
            _instance = new userDatabaseConnect();

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
