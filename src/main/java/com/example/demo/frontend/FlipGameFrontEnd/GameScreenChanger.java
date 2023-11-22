package com.example.demo.frontend.FlipGameFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.frontend.Common.MusicManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GameScreenChanger {

    private static GameScreenChanger _instance = null;

    private GameScreenChanger() {}

    public static GameScreenChanger getIntance()
    {
        if(_instance == null)
        {
            _instance = new GameScreenChanger();
        }
        return _instance;
    }

    private String player1Name;

    private String player2Name;

    private String topicName;

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void switchToGame1() {
        MusicManager.getInstance().playIf("/com/example/demo/music/song2.mp3");
        try {
            FXMLLoader game = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/MultipleChoice/select-difficulty-screen.fxml"));
            StackPane gameScreen = game.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, gameScreen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToGame2() {
        MusicManager.getInstance().playIf("/com/example/demo/music/song2.mp3");
        try {
            FXMLLoader game = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/FlipGameFrontEnd/InfoOfPlayer.fxml"));
            StackPane gameScreen = game.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, gameScreen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToSelectTopic()
    {
        try {
            FXMLLoader game = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/FlipGameFrontEnd/SelectTopic.fxml"));
            StackPane gameScreen = game.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, gameScreen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToFlipGame()
    {
        try {
            FXMLLoader game = new FXMLLoader(getClass().getResource("/com/example/demo/frontend/FlipGameFrontEnd/FlipGame.fxml"));
            StackPane gameScreen = game.load();
            ScreenManager.getInstance().getRoot().getChildren().set(0, gameScreen);
            ScreenManager.getInstance().applyFadeInEffect((StackPane) ScreenManager.getInstance().getRoot().getChildren().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
