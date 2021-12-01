package com.example.castaticanalyzer.code.entity;

import lombok.Getter;

import java.util.List;

/** @DomainEntity */
@Getter
public class CodeModel {

    List<String> sourceCode;
    String[] targetConcepts;


    public CodeModel(List<String> sourceCode, String[] targetConcepts) {
        this.sourceCode = sourceCode;
        this.targetConcepts = targetConcepts;
    }
}
