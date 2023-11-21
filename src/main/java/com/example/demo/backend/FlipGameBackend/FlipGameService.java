package com.example.demo.backend.FlipGameBackend;

import com.example.demo.frontend.FlipGameFrontEnd.ImgOfGame;

import java.util.ArrayList;
import java.util.List;

public class FlipGameService {
    FlipGameReposity flipGameReposity = new FlipGameReposity();

    public FlipGameService() {

    }

    public List<ImgOfGame> getImageFromTopic()
    {
        return flipGameReposity.loadImageAsJson();
    }

    public List<ImgOfGame> getImageFromTopic(String topic)
    {
        return flipGameReposity.loadImageAsJson(topic);
    }

    public List<String> getAllTopicName(){
        List<String> res = new ArrayList<>();
        String respon = flipGameReposity.getAllFolderName();
        String[] topics = respon.split(" ");
        for(String topic: topics)
        {
            res.add(topic);
        }
        if(res.size()==1 && res.get(0).equals(""))
        {
            res.remove(0);
        }
        return res;
    }
}
