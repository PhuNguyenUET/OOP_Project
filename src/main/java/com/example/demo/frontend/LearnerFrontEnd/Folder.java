package com.example.demo.frontend.LearnerFrontEnd;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<String> userList = new ArrayList<>();

    public Folder(){
        for(int i=0;i<6;i++)
        {
            String name= "folder_" + i;
            userList.add(name);
        }
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }


}
