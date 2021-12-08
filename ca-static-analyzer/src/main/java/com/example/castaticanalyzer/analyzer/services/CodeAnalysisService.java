package com.example.castaticanalyzer.analyzer.services;

import com.example.castaticanalyzer.code.DTO.Code;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.code.gateways.GitHubCodeDataGateway;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CodeAnalysisService {

    GitHubCodeDataGateway githubCodeDataGateway;


    public ParsedCode reviewGitHubSourceCode(GithubRepo repo) {
        ParsedCode parsedCode = new ParsedCode();
        List<Code> codeList;
        try {
            codeList = githubCodeDataGateway.getSourceCode(repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedCode;
    }
}
