package com.example.demo.frontend.FlipGameFrontEnd;

import com.example.demo.backend.FlipGameBackend.FlipGameService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int sizeOfGame = 40;
    private Player Player1;
    private Player Player2;

    private FlipGameService flipGameService = new FlipGameService();

    public List<ImgOfGame> getList() {
        return list;
    }

    public void setList(List<ImgOfGame> list) {
        this.list = list;
    }

    private List<ImgOfGame> list = new ArrayList<>();

    public Game(String topic,String name1,String name2)
    {
        sizeOfGame = 40;
        list = flipGameService.getImageFromTopic(topic);
        System.out.println("Ten topic hioen tai la:" + topic);
        System.out.println("So anh co trong topic nay la: " + list.size());
        Collections.shuffle(list);
        Player1 = new Player(name1,0);
        Player2 = new Player(name2,0);
    }

    public int getSizeOfGame() {
        return sizeOfGame;
    }

    public Player getPlayer1() {
        return Player1;
    }

    public Player getPlayer2() {
        return Player2;
    }

    public void setSizeOfGame(int sizeOfGame) {
        this.sizeOfGame = sizeOfGame;
    }

    public void setPlayer1(Player player1) {
        Player1 = player1;
    }

    public void setPlayer2(Player player2) {
        Player2 = player2;
    }

    boolean isFinished()
    {
        if(sizeOfGame <= 0)
        {
            return true;
        }
        return false;
    }

    String nameOfWinner()
    {
        if(Player1.getScore()>Player2.getScore())
        {
            return Player1.getName();
        }
        else if(Player1.getScore()<Player2.getScore())
        {
            return Player2.getName();
        }
        return "Tỉ số hòa!";
    }
}
