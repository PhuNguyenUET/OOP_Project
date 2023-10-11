package com.example.demo.frontend.LoginComponent;

import com.example.demo.backend.*;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Rectangle movingRectangle;

    @FXML
    private TranslateTransition transition;

    @FXML
    private TranslateTransition toastMesTransition;

    @FXML
    private HBox toastMes;

    @FXML
    public Button submitButton;

    @FXML
    public ImageView toastIcon;

    @FXML
    private Image toastImg;

    @FXML
    private Label textMessage;

    @FXML
    private Label textMessageDes;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;


    public void initialize() {
        // Tạo TranslateTransition và đặt thời gian di chuyển
        transition = new TranslateTransition(Duration.seconds(0.5), movingRectangle);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2)); // Đợi 2 giây trước khi thực hiện chuyển tiếp
        toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
        // Xác định khoảng cách di chuyển
//        double moveDistance = signUpButton.getLayoutX() - loginButton.getLayoutX();
        double moveDistance= 130;
        double xPositionLogin = loginButton.getLayoutX();
        double xPositionSign = signUpButton.getLayoutX();
        System.out.println(xPositionLogin);
        System.out.println(xPositionSign);
        System.out.println(movingRectangle.getLayoutX());
        System.out.println(moveDistance);
        // Đặt sự kiện khi nút "Login" được nhấp
        loginButton.setOnAction(event -> {
            userName.setText("");
            password.setText("");
            transition.setToX(60);
            transition.play();
            System.out.println("Login button clicked!");
            loginButton.getStyleClass().add("active");
            signUpButton.getStyleClass().remove("active");
            submitButton.setText("Login");
        });

        // Đặt sự kiện khi nút "Sign Up" được nhấp
        signUpButton.setOnAction(event -> {
            userName.setText("");
            password.setText("");
            transition.setToX(-65);
            transition.play();
            System.out.println("Sign up button clicked!");
            loginButton.getStyleClass().remove("active");
            signUpButton.getStyleClass().add("active");
            submitButton.setText("Register");
        });

        pauseTransition.setOnFinished(e -> {
            toastMesTransition.setToX(360);
            toastMesTransition.play();
        });
        submitButton.setOnAction(e -> {
            String userValue=userName.getText();
            String passValue=password.getText();
            Connection connection=userDatabaseConnect.connect();
            if(submitButton.getText().equals("Login")){
                if(userDatabaseSQL.check(connection,userValue,passValue)){
                    URL imageUrl = getClass().getResource("/com/example/demo/assets/check.png");
                    Image image = new Image(imageUrl.toString());
                    textMessage.setText("Successful Login");
                    textMessageDes.setText("Password and Username are correct.");
                    toastIcon.setImage(image);
                    toastMesTransition.setToX(-28);
                    toastMesTransition.play();
                    pauseTransition.play();
                }
                else
                {
                    URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                    Image image = new Image(imageUrl.toString());
                    textMessage.setText("Error Login");
                    textMessageDes.setText("Incorrect Password or Username.");
                    toastIcon.setImage(image);
                    toastMesTransition.setToX(-28);
                    toastMesTransition.play();
                    pauseTransition.play();
                }
            }
            else {
                if(userDatabaseSQL.isUsernameExists(connection,userValue)){
                    URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                    Image image = new Image(imageUrl.toString());
                    textMessage.setText("Error Login");
                    textMessageDes.setText("Username already existed. Please use a different username.");
                    toastIcon.setImage(image);
                    toastMesTransition.setToX(-28);
                    toastMesTransition.play();
                    pauseTransition.play();
                }
                else
                {
                    userDatabaseSQL.insertIntoDict(connection,userValue,passValue,1);
                    URL imageUrl = getClass().getResource("/com/example/demo/assets/check.png");
                    Image image = new Image(imageUrl.toString());
                    textMessage.setText("Successful Register");
                    textMessageDes.setText("You can start your journey in my App");
                    toastIcon.setImage(image);
                    toastMesTransition.setToX(-28);
                    toastMesTransition.play();
                    pauseTransition.play();
                }
            }
        } );
    }
}