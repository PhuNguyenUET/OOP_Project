package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateMultipleChoice {
    public static void createQuestionList(Connection connection, String difficulty) {
        File quesFile = new File("src\\main\\resources\\" + difficulty + ".txt");
        File ansFile = new File("src\\main\\resources\\" + difficulty + "key.txt");

        try {
            FileReader quesFileReader = new FileReader(quesFile);
            FileReader ansFileReader = new FileReader(ansFile);

            BufferedReader qBufferedReader = new BufferedReader(quesFileReader);
            BufferedReader aBufferedReader = new BufferedReader(ansFileReader);

            String line;
            String ques = "";
            String ansA = "";
            String ansB = "";
            String ansC = "";
            String ansD = "";

            int count = 0;
            while ((line = qBufferedReader.readLine()) != null) {
                count++;
                switch (count % 5) {
                    case 1:
                        if (count <= 9) ques = line.substring(3);
                        else ques = line.substring(4);
                        break;
                    case 2:
                        ansA = line.substring(3);
                        break;
                    case 3:
                        ansB = line.substring(3);
                        break;
                    case 4:
                        ansC = line.substring(3);
                        break;
                    case 0:
                        ansD = line.substring(3);

                        String query = "INSERT INTO test" + difficulty + " (questionID, question, A, B, C, D) VALUES (?, ?, ?, ?, ?, ?)";
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, count / 5);
                            preparedStatement.setString(2, ques);
                            preparedStatement.setString(3, ansA);
                            preparedStatement.setString(4, ansB);
                            preparedStatement.setString(5, ansC);
                            preparedStatement.setString(6, ansD);
                            preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        break;
                }
            }

            count = 0;
            while ((line = aBufferedReader.readLine()) != null) {
                count++;
                String query = "UPDATE test" + difficulty + " SET answer = ? WHERE questionID = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, line);
                    preparedStatement.setInt(2, count);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            qBufferedReader.close();
            aBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
