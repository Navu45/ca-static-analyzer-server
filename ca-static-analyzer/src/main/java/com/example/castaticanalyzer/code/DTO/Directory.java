package com.example.castaticanalyzer.code.DTO;

import java.util.List;

public class Directory extends DirectoryContent{

    private List<DirectoryContent> children;
    private Directory parent;

    @Override
    boolean isFile() {
        return false;
    }

    public void add(DirectoryContent content) {
         add(content, 0);
    }

    public void add(DirectoryContent content, int i) {
        String currentDirectory = content.getPath().split("/")[i];
        for (DirectoryContent child:
             children) {
            if (!isThisDirectory(content)) {
                if (child.isDirectory()) {
                    Directory childDirectory = (Directory) child;
                    childDirectory.add(content, ++i);
                }
                else {

                }
            }
        }
    }

    private boolean isThisDirectory(DirectoryContent content) {
        return content.getName().equals(getName());
    }
}
