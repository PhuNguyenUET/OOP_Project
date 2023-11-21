package com.example.demo.backend.LearnerBackend;

import com.example.demo.frontend.Common.LearnerFuncToDict;
import com.example.demo.frontend.Common.Word;

import java.util.ArrayList;
import java.util.List;



public class ConnectComponentLearner extends LearnerFuncToDict{

    FolderReposity folderReposity = new FolderReposity();

    ListReposity listReposity = new ListReposity();

    WordReposity wordReposity = new WordReposity();
    @Override
    public List<String>  getRecentFolders(int userId){
        String respon = folderReposity.getRecentFoldersForDict(userId);
        String[] folders = respon.split(" ");
        List<String>res = new ArrayList<>();
        for (String folder : folders)
        {
            res.add(folder);
        }
        if(res.size()==1)
        {
            if(res.get(0).equals(""))
            {
                res.remove(0);
            }
        }
        return res;
    }

    @Override
    public List<String> getAllFolders(int userId)
    {
        String respone = folderReposity.getAllFoldersForDict(userId);
        String[] lists = respone.split(" ");
        List<String> res = new ArrayList<>();
        for (String list : lists)
        {
            res.add(list);
        }
        if(res.size()==1)
        {
            if(res.get(0).equals(""))
            {
                res.remove(0);
            }
        }
        return res;
    }

    @Override
    public List<String> getAllLists(String folder)
    {
        int folderId = folderReposity.getFolderId(folder);
        System.out.println(folder + " " + folderId);
        String respon = listReposity.getAllListsForDict(folderId);
        String[] lists = respon.split(" ");
        List<String> res = new ArrayList<>();
        for (String list : lists)
        {
            res.add(list);
        }
        if(res.size()==1)
        {
            if(res.get(0).equals(""))
            {
                res.remove(0);
            }
        }
        return res;
    }

    @Override
    public void addToList(String folder, String list, Word word){
        int folderId = folderReposity.getFolderId(folder);
        int listId = listReposity.getListId(list,folderId);
        System.out.println("List id cần add : " + listId);
        String englishWord = word.getWord();
        String type =word.getType().get(0);
        String definition = word.getDefinition().get(0);
        String pronunciation = word.getPronunciation();
        wordReposity.addNewList(englishWord,type,definition,listId);
    }

    @Override
    public int getFolderId(String folderName) {
        return folderReposity.getFolderId(folderName);
    }

    @Override
    public void updateRecentFolder(String folderName)
    {
        int folderId = getFolderId(folderName);
        folderReposity.addRecentFolder(folderId);
    }
}
