package com.example.demo.backend.MultipleChoiceBackend;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> options;
    private final int correctOption;

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    private Difficulty difficulty;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
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

    public Question(String question, List<String> options, int correctOption, String difficulty) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        switch (difficulty) {
            case "Easy" -> this.difficulty = Difficulty.EASY;
            case "Medium" -> this.difficulty = Difficulty.MEDIUM;
            case "Hard" -> this.difficulty = Difficulty.HARD;
        }
    }
}
