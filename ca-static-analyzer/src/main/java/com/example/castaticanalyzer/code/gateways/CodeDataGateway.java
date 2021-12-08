package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.code.DTO.Code;
import com.example.castaticanalyzer.code.DTO.GithubRepo;

import java.io.IOException;
import java.util.List;

public interface CodeDataGateway {
    List<Code> getSourceCode(GithubRepo repo) throws IOException;
}
