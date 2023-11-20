package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateImageList {
    public static void createImageList(Connection connection, String topic, int startingID) {
        File file = new File("src\\main\\resources\\" + topic + ".txt");

        String line = "";

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String word = "";

            while ((line = bufferedReader.readLine()) != null) {
                startingID++;
                word = line;
                String path = "src\\main\\resources\\" + topic + "\\" + word + ".png";
                File image = new File(path);
                byte[] res = data(image);
                
                try {
                    String query = "INSERT INTO testimage (id, name, topic, image) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, startingID);
                    preparedStatement.setString(2, word);
                    preparedStatement.setString(3, topic);
                    preparedStatement.setBytes(4, res);                        
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] data(File file) {
        byte[] imageData = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageData;
    }

    public static void test() {
        byte[] a = data(new File("src\\main\\data\\car.png"));
        for (byte i : a) {
            System.out.print(i);
        }
        System.out.println();
    }
}
