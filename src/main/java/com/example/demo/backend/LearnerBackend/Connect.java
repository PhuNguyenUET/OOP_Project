package com.example.demo.backend.LearnerBackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connect _instance = null;

    private Connect() {

    }

    public static Connect getInstance() {
        if (_instance == null) {
            _instance = new Connect();

        }
        return _instance;
    }

    private Connection connection;
    private String DATABASE_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12663890";

    private String user = "sql12663890";

    private String password = "FqLUKwYHh1";

    private String SUB_DATABASE_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12663993";

    private String subUser = "sql12663993";

    private String subPassword = "QKvwS3h8s3";

    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DATABASE_URL, user, password);;
                System.out.println("Connected to SQLite database" + DATABASE_URL);
            } catch (SQLException e) {
                System.err.println("Error connecting to SQLite database.");
                e.printStackTrace();
                try{
                    connection = DriverManager.getConnection(SUB_DATABASE_URL, subUser, subPassword);
                }
                catch (SQLException en)
                {
                    System.err.println("Error connecting to SQLite database.");
                    e.printStackTrace();
                }
            }

        }
        return connection;
    }

}