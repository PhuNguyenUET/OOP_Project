package com.example.demo.frontend.LearnerFrontEnd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ListUser {

    private int id;

    private String name;
    private int folderId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setId(int listId) {
        id = listId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
}
