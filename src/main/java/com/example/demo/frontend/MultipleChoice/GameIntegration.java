package com.example.demo.frontend.MultipleChoice;

import com.example.demo.backend.MultipleChoiceBackend.GameController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class GameIntegration {
    private static GameIntegration _instance = null;

    private GameIntegration() {
    }

    protected static GameIntegration Instance() {
        if (_instance == null) {
            _instance = new GameIntegration();
        }
        return _instance;
    }

    private final GameController backend = new GameController();

    protected List<Question> getQuestions(String difficulty) {
        List<Question> questions = new ArrayList<>();
        String backendResponse = backend.getQuestions(difficulty);
        if (backendResponse.isEmpty()) {
            return questions;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            questions = mapper.readValue(backendResponse, new TypeReference<List<Question>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
