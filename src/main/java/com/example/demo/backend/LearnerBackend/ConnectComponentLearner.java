package com.example.demo.backend.LearnerBackend;

import com.example.demo.frontend.Common.LearnerFuncToDict;
import com.example.demo.frontend.Common.Word;

import java.util.ArrayList;
import java.util.List;



public class ConnectComponentLearner extends LearnerFuncToDict{
    @Override
    public List<String>  getRecentFolders(int userId){
        List<String>res = new ArrayList<>();
        return res;
    }

    @Override
    public List<String> getAllFolders(int userId)
    {
        String respone = FolderReposity.getInstance().getAllFoldersForDict(userId);
        String[] lists = respone.split(" ");
        List<String> res = new ArrayList<>();
        for (String list : lists)
        {
            res.add(list);
        }
        return res;
    }

    @Override
    public List<String> getAllLists(String folder)
    {
        int folderId = FolderReposity.getInstance().getFolderId(folder);
        String respon = ListReposity.getInstance().getAllListsForDict(folderId);
        String[] lists = respon.split(" ");
        List<String> res = new ArrayList<>();
        for (String list : lists)
        {
            res.add(list);
        }
        return res;
    }

    @Override
    public void addToList(String folder, String list, Word word){
        int folderId = FolderReposity.getInstance().getFolderId(folder);
        int listId = ListReposity.getInstance().getListId(list);
        String englishWord = word.getWord();
    }

}
