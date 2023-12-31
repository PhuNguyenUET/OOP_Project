package com.example.demo.frontend.navBarFrontEnd;

import com.example.demo.HelloApplication;
import com.example.demo.ScreenManager;

import com.example.demo.backend.ProfileBackend.ProfileConection;
import com.example.demo.backend.ProfileBackend.ProfileRepo;
import com.example.demo.frontend.Common.MusicManager;
import com.example.demo.frontend.SettingsFrontEnd.SettingsIntegration;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class NavbarController implements Initializable {
    @FXML
    private Button Dictionary;

    @FXML
    private Button Learner;

    @FXML
    private Button Translate;

    @FXML
    private Button Game;

    @FXML
    private TranslateTransition transition;

    @FXML
    private Rectangle movingRec;

    @FXML
    public Circle profileButton;
    @FXML
    public Circle profilePictureDisplay;
    @FXML
    public HBox profileChange;
    @FXML
    public HBox settings;
    @FXML
    public HBox logout;
    @FXML
    public VBox popUpWindow;
    @FXML
    public Label welcomeLabel;

    private boolean popUpDisabled = true;
    private boolean popUpVisible = false;

    public Button getDictionary() {
        return Dictionary;
    }

    public Button getLearner() {
        return Learner;
    }

    public Button getTranslate() {
        return Translate;
    }

    public Button getGame() {
        return Game;
    }

    private List<Button> list = new ArrayList<>();

    public List<Button> getList() {
        return list;
    }

    @FXML
    private Canvas canvas;

    double DictionaryPos = 58;

    double LeanerPos = DictionaryPos + 170;

    double TranslatePos = LeanerPos + 170;

    double GamePos = TranslatePos + 170;

    ProfileRepo profileRepo = new ProfileRepo();

    private Canvas getCanvas() {
        return this.canvas;
    }

    public void initialize() {
        Dictionary.setOnAction(e -> {
            MusicManager.getInstance().playIf("/com/example/demo/music/song.mp3");
            handleActive(Dictionary);
            ScreenManager.getInstance().switchToDict();
            movingRec.setVisible(true);
        });

        Learner.setOnAction(e -> {
            MusicManager.getInstance().playIf("/com/example/demo/music/song.mp3");
//            movingAnimation(transition, LeanerPos);
            handleActive(Learner);
            ScreenManager.getInstance().switchToLearner();
            movingRec.setVisible(true);
        });

        Translate.setOnAction(e -> {
            MusicManager.getInstance().playIf("/com/example/demo/music/song.mp3");

//            movingAnimation(transition, TranslatePos);
            handleActive(Translate);
            ScreenManager.getInstance().switchToTranslate();
            movingRec.setVisible(true);
        });

        Game.setOnAction(e -> {
            MusicManager.getInstance().playIf("/com/example/demo/music/song.mp3");

//            movingAnimation(transition, GamePos);
            handleActive(Game);
            ScreenManager.getInstance().switchToGame();
            movingRec.setVisible(true);
        });

        profileChange.setOnMouseClicked(e -> {
            ScreenManager.getInstance().switchToProfile();
            movingRec.setVisible(false);
        });
    }

    public void movingAnimation(TranslateTransition transition, double pos) {
        transition = new TranslateTransition(Duration.seconds(0.75), movingRec);
        transition.setToX(pos);
        transition.play();
    }

    public void updateProfileImage() {
        String imgUrl = SettingsIntegration.Instance().getImageURL(ScreenManager.getInstance().getUserId());
        ImagePattern pattern = null;
        try {
            pattern = new ImagePattern(
                    new Image(getClass().getResource(imgUrl).toURI().toString())
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        profileButton.setFill(pattern);
        profilePictureDisplay.setFill(pattern);
    }

    public void resetPopupWindow() {
        popUpWindow.setVisible(false);
        popUpWindow.setDisable(true);
    }

    public void handleActive(Button button) {
        if (button.getText().equals(Dictionary.getText())) {
            if (!Dictionary.getStyleClass().contains("active")) {
                Dictionary.getStyleClass().add("active");
            }
            movingAnimation(transition, DictionaryPos);
        } else {
            if (Dictionary.getStyleClass().contains("active")) {
                Dictionary.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Learner.getText())) {
            if (!Learner.getStyleClass().contains("active")) {
                Learner.getStyleClass().add("active");
            }
            movingAnimation(transition, LeanerPos);

        } else {
            if (Learner.getStyleClass().contains("active")) {
                Learner.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Translate.getText())) {
            if (!Translate.getStyleClass().contains("active")) {
                Translate.getStyleClass().add("active");
            }
            movingAnimation(transition, TranslatePos);

        } else {
            if (Translate.getStyleClass().contains("active")) {
                Translate.getStyleClass().remove("active");
            }
        }
        if (button.getText().equals(Game.getText())) {
            if (!Game.getStyleClass().contains("active")) {
                Game.getStyleClass().add("active");
            }
            movingAnimation(transition, GamePos);

        } else {
            if (Game.getStyleClass().contains("active")) {
                Game.getStyleClass().remove("active");
            }
        }
    }

    @FXML
    public void displayPopUpWindow(Event event) {
        event.consume();
        updateProfileImage();
        welcomeLabel.setText("Welcome, " + SettingsIntegration.Instance().getName(ScreenManager.getInstance().getUserId()));
        popUpWindow.setVisible(true);
        popUpWindow.setDisable(false);
    }

    @FXML
    public void changeToSettings() {
        movingRec.setVisible(false);
        ScreenManager.getInstance().switchToSettings();
    }

    @FXML
    public void loggingOut() {
        long timeUsage = System.currentTimeMillis() - ScreenManager.getInstance().getLoginTime();
        double timeHour = timeUsage * 1.0 / (60 * 1000 * 1.0);
        System.out.println("Thời gian phiên đăng nhập đến khi log out là : " + timeHour + " minutes");
        if (ScreenManager.getInstance().getLoginDate() != null) {
            if (profileRepo.checkDateIsExist(ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId())) {
                double lastTime  = profileRepo.getRecentTimeUsageOfDate(ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
                double newTime = lastTime + timeHour;
                profileRepo.updateTimeForDate(newTime,ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
            } else {
                profileRepo.insertToSession(timeHour, ScreenManager.getInstance().getLoginDate(), ScreenManager.getInstance().getUserId());
            }
        }
        ScreenManager.getInstance().setLoginDate(null);
        ScreenManager.getInstance().switchToLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
        updateProfileImage();
    }
}
