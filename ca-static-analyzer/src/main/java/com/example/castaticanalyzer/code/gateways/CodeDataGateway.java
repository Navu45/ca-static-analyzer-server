package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.code.entity.CodeModel;
import com.example.castaticanalyzer.code.entity.GithubRepo;

import java.io.IOException;

public interface CodeDataGateway {
    CodeModel getCodeModel(String[] targetConcepts, GithubRepo repo) throws IOException;
}
