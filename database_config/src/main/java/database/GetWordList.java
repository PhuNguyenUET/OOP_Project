package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetWordList {
    public static void getWordList(Connection connection) {
        File inFile = new File("src\\main\\resources\\E_V.txt");        //Personal file to get database
        File outFile = new File("src\\main\\resources\\output.txt");    //Personal file to test conversion

        try {
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(outFile);

            int count = 0;
            int dbcount = 0;

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                count++;
                String word = "";
                String pronunciation = "";

                int i = 0;
                while (line.charAt(i) != '<') {
                    word += line.charAt(i);
                    i++;
                }

                //Skip substring of html format
                while (line.charAt(i) == '<') {
                    while (line.charAt(i) != '>') {
                        i++;
                    }

                    i++;
                }

                //Skip the word after
                while (line.charAt(i) != '/' && line.charAt(i) != '<') {
                    i++;
                }

                if (line.charAt(i) == '/') {
                    pronunciation += "/";
                    i++;
                    while (line.charAt(i) != '/') {
                        pronunciation += line.charAt(i);
                        i++;
                    }

                    pronunciation += "/";
                }

                //Test the result
                // fileWriter.write(count + " " + word + " " + pronunciation + "\n");

                //Insert to sqlite database
                String query = "INSERT INTO words (wordID, word, pronunciation) VALUES (?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, count);
                    preparedStatement.setString(2, word);
                    preparedStatement.setString(3, pronunciation);
                    dbcount += preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(dbcount + " line added to database.");

            fileReader.close();
            bufferedReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int skipHtmlFormat(String line, int i) {
        while (line.charAt(i) == '<') {
            while (line.charAt(i) != '>') {
                i++;
            }

            i++;
        }

        return i;
    }
}
