package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.codereview.CodeReview;
import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/** @InterfaceAdapter */
@RestController
@AllArgsConstructor
@Tag(name = "Analyzer")
public class AnalyzerController {

    private CodeAnalysisService service;

    @GetMapping("/analyze")
    public HttpEntity<CodeReview> analyzeGitHubCode(GithubRepo repo) {
        CodeReview review = service.reviewGitHubSourceCode(repo);
        review.add(linkTo(methodOn(AnalyzerController.class).analyzeGitHubCode(repo)).withSelfRel());
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
