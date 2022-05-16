package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.codereview.CodeReview;
import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.authentication.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/** @InterfaceAdapter */
@RestController
@AllArgsConstructor
@Tag(name = "Analyzer")
public class AnalyzerController {

    private CodeAnalysisService service;

    @GetMapping("/analyze")
    public ResponseEntity<Object> analyzeGitHubCode(@Valid @RequestBody GithubRepo repo) {
        CodeReview review = service.reviewGitHubSourceCode(repo);
        review.add(linkTo(methodOn(AnalyzerController.class).analyzeGitHubCode(repo)).withSelfRel());
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
