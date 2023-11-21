package database;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection dicConnection = ConnectDictionary.connect();
        Connection mulConnection = ConnectMultipleChoice.connect();
        Connection imgConnection = ConnectImageList.connect();
        
        GetWordList.getWordList(dicConnection);
        GetExplanationList.getExplanationList(dicConnection);
        GetPhraseList.getPhraseList(dicConnection);

        CreateMultipleChoice.createQuestionList(mulConnection, "easy");
        CreateMultipleChoice.createQuestionList(mulConnection, "medium");
        CreateMultipleChoice.createQuestionList(mulConnection, "hard");

        CreateImageList.createImageList(imgConnection, "vehicle", 0);
        CreateImageList.createImageList(imgConnection, "food", 20);
        CreateImageList.createImageList(imgConnection, "animal", 40);
        CreateImageList.createImageList(imgConnection, "furniture", 60);
        CreateImageList.createImageList(imgConnection, "electronic device", 80);
        CreateImageList.createImageList(imgConnection, "sport", 100);
    }
}