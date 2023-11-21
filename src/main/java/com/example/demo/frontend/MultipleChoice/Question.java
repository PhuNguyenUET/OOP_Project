package com.example.demo.frontend.MultipleChoice;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int correctOption;

    private Difficulty difficulty;

    public String getDifficulty() {
        return difficulty.name();
    }

    public void setDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy" -> this.difficulty = Difficulty.Easy;
            case "Medium" -> this.difficulty = Difficulty.Medium;
            case "Hard" -> this.difficulty = Difficulty.Hard;
        }
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public Question(){}

    public Question(String question, List<String> options, int correctOption, String difficulty) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        switch (difficulty) {
            case "Easy" -> this.difficulty = Difficulty.Easy;
            case "Medium" -> this.difficulty = Difficulty.Medium;
            case "Hard" -> this.difficulty = Difficulty.Hard;
        }
    }

    public boolean checkAnswer (int choice) {
        return choice == correctOption;
    }
}