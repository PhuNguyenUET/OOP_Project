package com.example.demo.backend.MultipleChoiceBackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    protected String getQuestions (String difficulty) {
        List<Question> questionList = gameRepository.getQuestionsForTest(GameDatabase.Instance().getConnection(), difficulty);
        ObjectMapper mapper = new ObjectMapper();
        String res = "";

        try {
            res = mapper.writeValueAsString(questionList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }
}
