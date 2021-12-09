package com.example.castaticanalyzer.code.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Getter
@Setter
public class Code {
    private String path;
    private String data;
    private String name;

    public Code(String path, String data) {
        this.path = path;
        this.data = data;
        name = path.split("/")[path.split("/").length - 1].replace(".java", "");
    }

    public Code() {

    }
}
