package com.example.demo.frontend.Common;

import java.util.List;

public abstract class LearnerFuncToDict {
    public abstract List<String> getRecentFolders(int userId);

    public abstract List<String> getAllFolders(int userId);

    public abstract List<String> getAllLists(String folder);

    public abstract void addToList(String folder, String list, Word word);

    public abstract int getFolderId(String folderName);

    public abstract void updateRecentFolder(String folderName);
}
