package com.example.demo.frontend.LearnerFrontEnd;

public class UserWord {
    private int id;
    private String word;
    private String type;
    private String pronunciation;
    private String definition;
    private int listId;


    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public String getDefinition() {
        return definition;
    }

    public int getListId() {
        return listId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
}
