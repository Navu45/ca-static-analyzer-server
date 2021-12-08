package com.example.castaticanalyzer.analyzer.services;

import com.example.castaticanalyzer.analyzer.Analyzer;
import com.example.castaticanalyzer.analyzer.CodeReview;
import com.example.castaticanalyzer.code.DTO.Code;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.code.gateways.GitHubCodeDataGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @UseCase */

@Service
public class CodeAnalysisService {

    @Autowired
    GitHubCodeDataGateway githubCodeDataGateway;

    @Autowired
    Analyzer analyzer;

    public CodeReview reviewGitHubSourceCode(GithubRepo repo) {
        List<Code> codeList = new ArrayList<>();
        try {
            codeList = githubCodeDataGateway.getSourceCode(repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (codeList.size() != 0)
            return analyzer.checkDependencyRule(codeList);
        return null;
    }
}
