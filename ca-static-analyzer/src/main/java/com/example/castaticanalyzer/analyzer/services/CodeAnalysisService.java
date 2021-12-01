package com.example.castaticanalyzer.analyzer.services;

import com.example.castaticanalyzer.code.entity.CodeModel;
import com.example.castaticanalyzer.code.entity.CodeReview;
import com.example.castaticanalyzer.code.entity.GithubRepo;
import com.example.castaticanalyzer.code.gateways.GitHubCodeDataGateway;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CodeAnalysisService {

    GitHubCodeDataGateway githubCodeDataGateway;


    public CodeReview reviewGitHubSourceCode(String[] targetConcepts, GithubRepo repo) {
        CodeReview codeReview = new CodeReview();
        CodeModel codeModel;
        try {
            codeModel = githubCodeDataGateway.getCodeModel(targetConcepts, repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeReview;
    }
}
