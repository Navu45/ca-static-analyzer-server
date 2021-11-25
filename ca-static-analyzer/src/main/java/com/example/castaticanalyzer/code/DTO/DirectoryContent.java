package com.example.castaticanalyzer.code.DTO;

public abstract class DirectoryContent {

    private String path;
    private String name;

    abstract boolean isFile();

    boolean isDirectory() {
        return !isFile();
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
