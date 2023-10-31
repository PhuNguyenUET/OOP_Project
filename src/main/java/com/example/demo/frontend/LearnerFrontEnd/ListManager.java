package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.backend.LearnerBackend.ListReposity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListManager {
    private static ListManager instance_;

    private List<ListUser> listOfFolder = new ArrayList<>();
    private ListManager(){}

    public static ListManager getInstance(){
        if (instance_ == null)
        {
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

    public List<ListUser> updateAndGetAllListOfFolder(int folderId)
    {
        String jsonInput = ListReposity.getInstance().getAllListFromFolderTest(folderId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<ListUser> userList = objectMapper.readValue(jsonInput, new TypeReference<List<ListUser>>() {});
//            System.out.println("ListUser: /n");
//            for (ListUser user : userList) {
//                System.out.println("ListId: " + user.getId() + ", Name: " + user.getName());
//            }
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
            List<ListUser> userList = new ArrayList<>();
            return userList;
        }
    }

}
