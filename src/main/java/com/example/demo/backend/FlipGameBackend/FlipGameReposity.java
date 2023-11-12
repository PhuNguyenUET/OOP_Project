package com.example.demo.backend.FlipGameBackend;

import com.example.demo.frontend.FlipGameFrontEnd.ImgOfGame;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FlipGameReposity {


    public FlipGameReposity() {
    }

    public void saveImage(File file) {
        try {
            // Đọc dữ liệu ảnh từ tệp và lưu vào cơ sở dữ liệu
            byte[] imageData = Files.readAllBytes(file.toPath());
            String insertQuery = "INSERT INTO images (data) VALUES (?)";
            PreparedStatement statement = FlipGameDatabase.getInstance().connect().prepareStatement(insertQuery);
            statement.setBytes(1, imageData);
            statement.executeUpdate();
            statement.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(String imagePath,String name, String topic) {
        File file = new File(imagePath);
        try {
            if (file.exists()) {
                byte[] imageData = Files.readAllBytes(file.toPath());
                String insertQuery = "INSERT INTO FlipImage (image,name,topic) VALUES (?,?,?)";
                PreparedStatement statement = FlipGameDatabase.getInstance().connect().prepareStatement(insertQuery);
                statement.setBytes(1, imageData);
                statement.setString(2, name);
                statement.setString(3, topic);
                statement.executeUpdate();
                statement.close();
            } else {
                System.out.println("Tệp ảnh không tồn tại.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Image loadImage() {
        try {
            String selectQuery = "SELECT image FROM FlipImageTmp LIMIT 1";
            PreparedStatement statement = FlipGameDatabase.getInstance().connect().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("image");
                InputStream stream = new ByteArrayInputStream(imageData);
                return new Image(stream);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteImage(String name) {
        try {
            String deleteQuery = "DELETE FROM FlipImage WHERE name = ?";
            PreparedStatement statement = FlipGameDatabase.getInstance().connect().prepareStatement(deleteQuery);
            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Đã xóa hình ảnh có tên '" + name + "'");
            } else {
                System.out.println("Không tìm thấy hình ảnh có tên '" + name + "'");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<ImgOfGame> loadImageAsJson() {
        try {
            List<ImgOfGame> list = new ArrayList<>();
            String selectQuery = "SELECT image,name FROM FlipImage";
            PreparedStatement statement = FlipGameDatabase.getInstance().connect().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            int cnt=0;
            while (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("image");
                String name = resultSet.getString("name");
                InputStream stream = new ByteArrayInputStream(imageData);
                Image image = new Image(stream);

                ImgOfGame imgOfGame = new ImgOfGame();
                imgOfGame.setImage(image);
                imgOfGame.setName(name);
                list.add(imgOfGame);
                cnt++;
            }
            System.out.println(cnt);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAllFolderName()
    {
        try{
            String selectQuery = "SELECT DISTINCT topic FROM FlipImage";
            PreparedStatement statement = FlipGameDatabase.getInstance().connect().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            StringBuilder res = new StringBuilder();
            while(resultSet.next())
            {
                String topicName = resultSet.getString("topic");
                res.append(topicName).append(" ");
            }
            return res.toString();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Không thể lấy tên topic";
        }
    }

    public List<ImgOfGame> loadImageAsJson(String topic) {
        List<ImgOfGame> list = new ArrayList<>();
        try (Connection connection = FlipGameDatabase.getInstance().connect()) {
            String sql = "SELECT image, name FROM FlipImage WHERE topic = ? limit 20";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, topic);

                try (ResultSet resultSet = statement.executeQuery()) {
                    int cnt = 0;

                    while (resultSet.next()) {
                        byte[] imageData = resultSet.getBytes("image");
                        String name = resultSet.getString("name");

                        try (InputStream stream = new ByteArrayInputStream(imageData)) {
                            Image image = new Image(stream);

                            ImgOfGame imgOfGame = new ImgOfGame();
                            imgOfGame.setImage(image);
                            imgOfGame.setName(name);
                            list.add(imgOfGame);
                            cnt++;
                        } catch (IOException e) {
                            // Handle image loading exception
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Loaded " + cnt + " images.");
                }
            } catch (SQLException e) {
                // Handle SQL exception related to the prepared statement
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Handle SQL exception related to connection
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        FlipGameReposity flipGameReposity = new FlipGameReposity();
        System.out.println(flipGameReposity.getAllFolderName());
//        flipGameReposity.deleteImage("space");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/anteater.png","anteater","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/anglerfish.png","anglerfish","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/camel.png","camel","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/crocodile.png","crocodile","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/chameleon.png","chameleon","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/dinosaur.png","dinosaur","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/dragonfly.png","dragonfly","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/duck.png","swan","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/flamingo.png","flamingo","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/grasshopper.png","grasshopper","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/lizard.png","lizard","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/narwhal.png","narwhal","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/ostrich.png","ostrich","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/owl.png","owl","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/panda.png","panda","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/porcupine.png","porcupine","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/raccoon.png","raccoon","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/rhino.png","rhino","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/squirrel.png","squirrel","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/stingray.png","stingray","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/toucan.png","toucan","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/turtle.png","turtle","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/animal/warlus.png","warlus","animal");
//        flipGameReposity.saveImage("C:/Users/laptop/Desktop/OOP_Project - Copy (2) - Copy/src/main/resources/com/example/demo/assets/fish.png","fish");
//        FlipGameService flipGameService = new FlipGameService();
//        List<ImgOfGame> list = flipGameService.getImageFromTopic("animal");
//        for (ImgOfGame item : list) {
//            System.out.println(item.getName());
//        }
    }
}
