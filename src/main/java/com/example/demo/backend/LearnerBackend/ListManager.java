package com.example.demo.backend.LearnerBackend;

import com.example.demo.frontend.LearnerFrontEnd.ListUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListManager {
    private static ListManager instance_;

    private List<ListUser> listOfFolder = new ArrayList<>();

    private ListManager() {
    }

    public static ListManager getInstance() {
        if (instance_ == null) {
            instance_ = new ListManager();
        }
        return instance_;
    }

    public List<ListUser> getListOfFolder() {
        return listOfFolder;
    }

    public void setListOfFolder(List<ListUser> listOfFolder) {
        this.listOfFolder = listOfFolder;
    }

    public List<ListUser> updateAndGetAllListOfFolder(int folderId) {
        String jsonInput = ListReposity.getInstance().getAllListFromFolderTest(folderId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<ListUser> userList = objectMapper.readValue(jsonInput, new TypeReference<List<ListUser>>() {
            });
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
            List<ListUser> userList = new ArrayList<>();
            return userList;
        }
    }

    public List<String> getAllListForDict(int folderId) {
        String lists = ListReposity.getInstance().getAllListsForDict(folderId);
        String[] respon = lists.split(" ");
        List<String> res = new ArrayList<>();
        for (String s : respon) {
            res.add(s);
        }
        return res;
    }
}
