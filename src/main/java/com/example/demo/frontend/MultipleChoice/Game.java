package com.example.demo.frontend.MultipleChoice;

import com.example.demo.frontend.Common.GameFunction;

import java.util.List;

public class Game extends GameFunction {
    private final Difficulty difficulty;
    private final List<Question> questions;
    private int score = 0;
    private int quesIndex = 0;

    public String getDifficulty() {
        return difficulty.name();
    }

    public Question getCurrentQuestion() {
        if (quesIndex >= questions.size()) {
            return null;
        }
        return questions.get(quesIndex);
    }

    public int getScore() {
        return score;
    }

    public int getQuesIndex() {
        return quesIndex + 1;
    }

    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
        questions = GameIntegration.Instance().getQuestions(difficulty.name());
    }

    public void nextQuestion() {
        quesIndex ++;
    }

    public boolean checkAnswer(int choice) {
        boolean check = questions.get(quesIndex).checkAnswer(choice);
        if(check) score += 10;
        return check;
    }

    public int getCorrectAnswer() {
        return questions.get(quesIndex).getCorrectOption();
    }

    public int getTotalQuestion() {
        return questions.size();
    }

    public boolean isFinished() {
        return quesIndex == questions.size() - 1;
    }
}
