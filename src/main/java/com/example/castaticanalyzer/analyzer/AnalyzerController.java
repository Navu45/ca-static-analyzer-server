package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.codereview.CodeReview;
import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.authentication.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/** @InterfaceAdapter */
@RestController
@AllArgsConstructor
@Tag(name = "Analyzer")
@RequestMapping("/analyzer")
@Slf4j
public class AnalyzerController {

    private CodeAnalysisService service;

    @PostMapping("/analyze")
    public ResponseEntity<Object> analyzeGitHubCode(@Valid @RequestBody GithubRepo repo) {
        CodeReview review = service.reviewGitHubSourceCode(repo);
        review.add(linkTo(methodOn(AnalyzerController.class).analyzeGitHubCode(repo)).withSelfRel());
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("repo/add")
    public ResponseEntity<Object> addGitHubRepo(@Valid @RequestBody GithubRepo repo) {
        List<GithubRepo> repos = service.getAllRepos();
        GithubRepo savedRepo = service.save(repo);
        log.warn(savedRepo.toString());
        repos.add(savedRepo);
        return new ResponseEntity<>(repos, HttpStatus.OK);
    }

    @GetMapping("/repos")
    public ResponseEntity<Object> getAllRepos() {
        List<GithubRepo> repoList = service.getAllRepos();
        return new ResponseEntity<>(repoList, HttpStatus.OK);
    }
}
