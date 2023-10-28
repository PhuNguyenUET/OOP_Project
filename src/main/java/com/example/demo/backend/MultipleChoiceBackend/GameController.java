package com.example.demo.backend.MultipleChoiceBackend;

public class GameController {
    GameRepository gameRepository = new GameRepository();
    GameService gameService = new GameService(gameRepository);
    public String getQuestions (String difficulty) {
        if (!difficulty.equals("Easy") && !difficulty.equals("Medium") && !difficulty.equals("Hard")) {
            return "Invalid difficulty level";
        }

        return gameService.getQuestions(difficulty);
    }
}
