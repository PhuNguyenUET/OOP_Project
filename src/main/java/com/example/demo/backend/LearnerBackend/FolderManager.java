package com.example.demo.backend.LearnerBackend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.ScreenManager;
import com.example.demo.frontend.LearnerFrontEnd.Folder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FolderManager {
    private static FolderManager instance_;

    private FolderManager() {

    }

    public static FolderManager getIntance() {
        if (instance_ == null) {
            instance_ = new FolderManager();
        }
        return instance_;
    }

    private List<Folder> listFolder = new ArrayList<>();

    public List<Folder> getListFolder() {
        return listFolder;
    }

    public void setListFolder(List<Folder> listFolder) {
        this.listFolder = listFolder;
    }

    public List<Folder> updateAndGetListFolder() {
        String inputJSon = FolderReposity.getInstance().getAllFolderTest(ScreenManager.getInstance().getUserId());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Folder> userFolderList = objectMapper.readValue(inputJSon, new TypeReference<List<Folder>>() {
            });

            for (Folder user : userFolderList) {
                System.out.println("FolderId: " + user.getId() + ", Name: " + user.getName());
            }

            System.out.println("update Folder");
            return userFolderList;
        } catch (IOException e) {
            e.printStackTrace();
            List<Folder> tmp = new ArrayList<>();
            return tmp;
        }
    }

    public List<Folder> getRecentFolder()
    {
        String recentFolderJson = FolderReposity.getInstance().getRecentFolder(ScreenManager.getInstance().getUserId());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Folder> userFolderList = objectMapper.readValue(recentFolderJson, new TypeReference<List<Folder>>() {
            });
            for (Folder user : userFolderList) {
                System.out.println("FolderId: " + user.getId() + ", Name: " + user.getName());
            }

            System.out.println("update Folder");
            return userFolderList;
        } catch (IOException e) {
            e.printStackTrace();
            List<Folder> tmp = new ArrayList<>();
            return tmp;
        }
    }

    public List<Folder> getTwoFolderRandom()
    {
        String recentFolderJson = FolderReposity.getInstance().getTwoFoldersRandom(ScreenManager.getInstance().getUserId());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Folder> userFolderList = objectMapper.readValue(recentFolderJson, new TypeReference<List<Folder>>() {
            });
            for (Folder user : userFolderList) {
                System.out.println("FolderId: " + user.getId() + ", Name: " + user.getName());
            }

            System.out.println("update Folder");
            return userFolderList;
        } catch (IOException e) {
            e.printStackTrace();
            List<Folder> tmp = new ArrayList<>();
            return tmp;
        }
    }


}
