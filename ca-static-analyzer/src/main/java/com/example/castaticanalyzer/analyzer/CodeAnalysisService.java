package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.codereview.CodeReview;
import com.example.castaticanalyzer.analyzer.problems.Problem;
import com.example.castaticanalyzer.analyzer.problems.ProblemType;
import com.example.castaticanalyzer.code.DTO.Code;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.code.gateways.GitHubCodeDataGateway;
import com.example.castaticanalyzer.user.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** @UseCase */

@Service
public class CodeAnalysisService {

    GitHubCodeDataGateway githubCodeDataGateway;

    Analyzer analyzer;

    public CodeAnalysisService(GitHubCodeDataGateway githubCodeDataGateway,
                               Analyzer analyzer) {
        this.githubCodeDataGateway = githubCodeDataGateway;
        this.analyzer = analyzer;
    }

    public CodeReview reviewGitHubSourceCode(GithubRepo repo, User user) {
        List<Code> codeList = new ArrayList<>();
        try {
            codeList = githubCodeDataGateway.getSourceCode(repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Problem> problems = analyzer.analyze(codeList);
        if (problems.size() == 0) {
            problems.add(new Problem("No problems found.", ProblemType.SUCCESS));
        }

        return new CodeReview(problems, repo, LocalDateTime.now());
    }
}
