package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.code.gateways.SavedGitHubReposGateway;
import com.example.castaticanalyzer.analyzer.codereview.CodeReview;
import com.example.castaticanalyzer.analyzer.problems.Problem;
import com.example.castaticanalyzer.analyzer.problems.ProblemType;
import com.example.castaticanalyzer.analyzer.code.DTO.Code;
import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.analyzer.code.gateways.CodeDataGateway;
import com.example.castaticanalyzer.authentication.user.User;
import com.example.castaticanalyzer.exceptions.BadUserDataInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** @UseCase */

@Service
@RequiredArgsConstructor
public class CodeAnalysisService {

    private final CodeDataGateway githubCodeDataGateway;
    private final SavedGitHubReposGateway databaseRepoGateway;
    private final Analyzer analyzer;


    public CodeReview reviewGitHubSourceCode(GithubRepo repo) {

        if (!repo.CheckURL())
        {
            throw new BadUserDataInputException("Github Info about repo is not right:" + repo.getRepo());
        }
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

    public GithubRepo save(GithubRepo repo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repo.setUser(user);
        return databaseRepoGateway.save(repo);
    }

    public List<GithubRepo> getAllRepos() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return databaseRepoGateway.findByUser(user);
    }
}
