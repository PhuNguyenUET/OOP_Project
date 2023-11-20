package database;

import database.DictionaryManagement;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection connection = Connect.connect();
        
        GetWordList.getWordList(connection);
        GetExplanationList.getExplanationList(connection);
        GetPhraseList.getPhrseList(connection);

        CreateMultipleChoice.createQuestionList(connection, "easy");
        CreateMultipleChoice.createQuestionList(connection, "easy");
        CreateMultipleChoice.createQuestionList(connection, "easy");
    }
}