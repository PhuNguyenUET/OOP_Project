package com.example.demo.frontend.Common;

import java.util.List;

public abstract class LearnerFuncToDict {
    public abstract List<String> getRecentFolders();
    public abstract List<String> getAllFolders();
    public abstract List<String> getAllLists(String folder);
    public abstract void addToList(String folder, String list);
}
