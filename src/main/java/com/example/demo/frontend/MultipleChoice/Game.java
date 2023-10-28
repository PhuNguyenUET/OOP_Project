package com.example.demo.frontend.MultipleChoice;

import java.util.List;

public class Game {
    Difficulty difficulty;
    List<Question> questions;
    int score = 0;

    int quesIndex = 0;

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty.name();
    }

    public Question getCurrentQuestion() {
        return questions.get(quesIndex);
    }

    public int getScore() {
        return score;
    }

    public int getQuesIndex() {
        return quesIndex;
    }

    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
        GameIntegration.Instance().getQuestions(difficulty.name());
    }

    public void nextQuestion() {
        quesIndex ++;
    }
}
