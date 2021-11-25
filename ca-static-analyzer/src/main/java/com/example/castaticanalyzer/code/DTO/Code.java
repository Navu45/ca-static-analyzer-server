package com.example.castaticanalyzer.code.DTO;

public class Code extends DirectoryContent{
    private String data;

    @Override
    boolean isFile() {
        return true;
    }
}
