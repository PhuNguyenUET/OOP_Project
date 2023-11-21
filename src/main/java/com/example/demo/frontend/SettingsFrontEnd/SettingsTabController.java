package com.example.demo.frontend.SettingsFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.frontend.Common.MusicManager;
import com.example.demo.frontend.navBarFrontEnd.NavbarController;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsTabController implements Initializable {

    @FXML
    ImageView currentProfilePicture;
    @FXML
    Button profileSaveButton;
    @FXML
    Button nameSaveButton;
    @FXML
    Button usernameSaveButton;
    @FXML
    Button passwordSaveButton;
    @FXML
    Label errorMessage;
    @FXML
    Label errorDescription;
    @FXML
    Label nameDescription;
    @FXML
    HBox messagePane;
    @FXML
    ImageView checkImage;
    @FXML
    TextField newNameTextField;
    @FXML
    PasswordField newNamePassword;
    @FXML
    TextField newUsernameTextField;
    @FXML
    PasswordField newUsernamePassword;
    @FXML
    PasswordField oldPassword;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField confirmNewPassword;
    @FXML
    HBox firstRow;
    @FXML
    HBox secondRow;
    @FXML
    Button musicButton;
    @FXML
    private TranslateTransition toastMesTransition;
    @FXML
    private PauseTransition pauseTransition;
    List<ImageView> profilePictures = new ArrayList<>();
    NavbarController navbarController;

    int currentProfileId = -1;

    public void setNavbarController(NavbarController navbarController) {
        this.navbarController = navbarController;
    }

    private void displayMessagePane (boolean successful, String message, String messageDes) {
        URL imageUrl = null;
        if (successful) {
            imageUrl = getClass().getResource("/com/example/demo/assets/check.png");
        } else {
            imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
        }
        Image image = new Image(imageUrl.toString());
        checkImage.setImage(image);
        errorMessage.setText(message);
        errorDescription.setText(messageDes);
        toastMesTransition.setToX(-28);
        toastMesTransition.play();
        pauseTransition.play();
        System.out.println("Ended");
    }

    private void onProfilePictureClicked (int idx) {
        currentProfileId = idx;
        currentProfilePicture.setImage(profilePictures.get(idx).getImage());
    }

    private void initializePictures() {
        for (int i = 0; i <= 20; i++) {
            ImageView img = new ImageView();
            String url = "/com/example/demo/assets/ProfilePicture/profile" + i + ".jpg";
            URL imageUrl = getClass().getResource(url);
            Image image = new Image(imageUrl.toString());
            img.setImage(image);
            img.setFitHeight(62);
            img.setFitWidth(64);
            int finalI = i;
            img.setOnMouseClicked(event -> onProfilePictureClicked(finalI));
            img.getStyleClass().add("avatarChoice");
            profilePictures.add(img);
        }
    }

    @FXML
    public void saveProfilePicture(Event event) {
        String result = SettingsIntegration.Instance().updateProfilePictureID(ScreenManager.getInstance().getUserId(),
                currentProfileId);
        if (result.equals("Profile Picture updated successfully")) {
            navbarController.updateProfileImage();
            displayMessagePane(true, "Success", result);
        } else {
            displayMessagePane(false, "Failed", result);
        }
    }

    @FXML
    public void saveName(Event event) {
        String result = SettingsIntegration.Instance().updateName(ScreenManager.getInstance().getUserId(),
                newNameTextField.getText(), newNamePassword.getText());
        if (result.equals("Name updated successfully")) {
            nameDescription.setText("You are currently refered to as: "
                    + SettingsIntegration.Instance().getName(ScreenManager.getInstance().getUserId()));
            displayMessagePane(true, "Success", result);
            ScreenManager.getInstance().getNavbarController().resetPopupWindow();
        } else {
            displayMessagePane(false, "Failed", result);
        }
    }

    @FXML
    public void saveNameKeyEvent(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            nameSaveButton.fire();
        }
    }

    @FXML
    public void saveUsernameKeyEvent(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            usernameSaveButton.fire();
        }
    }

    @FXML
    public void savePasswordKeyEvent(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            passwordSaveButton.fire();
        }
    }

    @FXML
    public void saveUserName(Event event) {
        String result = SettingsIntegration.Instance().updateUsername(ScreenManager.getInstance().getUserId(),
                newUsernameTextField.getText(), newUsernamePassword.getText());
        if (result.equals("Username updated successfully")) {
            displayMessagePane(true, "Success", result);
        } else {
            displayMessagePane(false, "Failed", result);
        }
    }

    @FXML
    public void savePassword(Event event) {
        String result = SettingsIntegration.Instance().updatePassword(ScreenManager.getInstance().getUserId(),
                oldPassword.getText(), newPassword.getText(), confirmNewPassword.getText());
        if (result.equals("Password updated successfully")) {
            displayMessagePane(true, "Success", result);
        } else {
            displayMessagePane(false, "Failed", result);
        }
    }

    @FXML
    public void turnOnOffMusic(Event event) {
        MusicManager.getInstance().turnMusic();
        if (MusicManager.getInstance().getMusic()) {
            musicButton.setText("Music: ON");
            MusicManager.getInstance().play2("/com/example/demo/music/song.mp3");
        } else {
            musicButton.setText("Music: OFF");
            MusicManager.getInstance().stop();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pauseTransition = new PauseTransition(Duration.seconds(2));
        toastMesTransition = new TranslateTransition(Duration.seconds(0.75), messagePane);
        pauseTransition.setOnFinished(e -> {
            toastMesTransition.setToX(380);
            toastMesTransition.play();
        });
        nameDescription.setText("You are currently refered to as: "
                + SettingsIntegration.Instance().getName(ScreenManager.getInstance().getUserId()));
        currentProfileId = SettingsIntegration.Instance().getProfileID(ScreenManager.getInstance().getUserId());
        String imgUrl = "/com/example/demo/assets/ProfilePicture/profile" + currentProfileId + ".jpg";
        URL imageUrl = getClass().getResource(imgUrl);
        Image image = new Image(imageUrl.toString());
        currentProfilePicture.setImage(image);
        initializePictures();
        for(int i = 0; i <= 10; i++) {
            firstRow.getChildren().add(profilePictures.get(i));
        }
        for(int i = 11; i <= 20; i++) {
            secondRow.getChildren().add(profilePictures.get(i));
        }
        if (MusicManager.getInstance().getMusic()) {
            musicButton.setText("Music: ON");
        } else {
            musicButton.setText("Music: OFF");
        }
    }
}
