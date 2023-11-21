package com.example.demo.backend.MultipleChoiceBackend;

import java.util.List;

public class Question {
    private final String question;
    private final List<String> options;
    private final int correctOption;

    private enum Difficulty {
        Easy,
        Medium,
        Hard
    }

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
}
