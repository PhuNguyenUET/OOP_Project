package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.backend.LearnerBackend.WordReposity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordManager {
    private static WordManager instance_;

    private List<ListUser> listOfFolder = new ArrayList<>();
    private WordManager(){}

    public static WordManager getInstance(){
        if (instance_ == null)
        {
            instance_ = new WordManager();
        }
        return instance_;
    }

    public List<ListUser> getListOfFolder() {
        return listOfFolder;
    }

    public void setListOfFolder(List<ListUser> listOfFolder) {
        this.listOfFolder = listOfFolder;
    }

    public List<UserWord> updateAndGetWordFromList(int listId)
    {
        String jsonInput = WordReposity.getInstance().getAllWordFromList(listId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<UserWord> userWords = objectMapper.readValue(jsonInput, new TypeReference<List<UserWord>>() {});
//            System.out.println("ListUser: /n");
//            for (UserWord user : userWords) {
//                System.out.println("id: " + user.getId() + ", Word: " + user.getWord() + ", ListId: " + user.getListId());
//            }
            return userWords;
        } catch (IOException e) {
            e.printStackTrace();
            List<UserWord> userWords = new ArrayList<>();
            return userWords;
        }
    }

    public List<UserWord> getThreeWords()
    {
        String jsonInput = WordReposity.getInstance().getThreeWordFromList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<UserWord> userWords = objectMapper.readValue(jsonInput, new TypeReference<List<UserWord>>() {});
//            System.out.println("ListUser: /n");
//            for (UserWord user : userWords) {
//                System.out.println("id: " + user.getId() + ", Word: " + user.getWord() + ", ListId: " + user.getListId());
//            }
            return userWords;
        } catch (IOException e) {
            e.printStackTrace();
            List<UserWord> userWords = new ArrayList<>();
            return userWords;
        }
    }
}
