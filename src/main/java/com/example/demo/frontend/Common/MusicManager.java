package com.example.demo.frontend.Common;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MusicManager {
    private static MusicManager _instance;
    private MediaPlayer mediaPlayer;
    private boolean music = true;

    private String musicPathCurrent;

    private MusicManager() {
    }

    public String getMusicPathCurrent() {
        return musicPathCurrent;
    }

    public void setMusicPathCurrent(String musicPathCurrent) {
        this.musicPathCurrent = musicPathCurrent;
    }

    public static MusicManager getInstance() {
        if (_instance == null) {
            _instance = new MusicManager();
        }
        return _instance;
    }

    public void play1(String musicPath) {
        stop();
        URL musicUrl = getClass().getResource(musicPath);
        if (musicUrl == null) {
            System.out.println("Invalid MusicPath");
            return;
        }
        Media media = new Media(musicUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.6);
        if(MusicManager.getInstance().getMusic()) {
            mediaPlayer.play();
        }
        setMusicPathCurrent(musicPath);
    }

    public void play2(String musicPath) {
        stop();
        URL musicUrl = getClass().getResource(musicPath);
        if (musicUrl == null) {
            System.out.println("Invalid MusicPath");
            return;
        }
        Media media = new Media(musicUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.6);
        if(MusicManager.getInstance().getMusic()) {
            mediaPlayer.play();
        }
        setMusicPathCurrent(musicPath);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(mediaPlayer.getStartTime()));
    }

    public void playIf(String musicPath)
    {
        if(!musicPath.equals(musicPathCurrent))
        {
            play2(musicPath);
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void turnMusic() {
        music = !music;
    }

    public boolean getMusic() {
        return music;
    }

}
