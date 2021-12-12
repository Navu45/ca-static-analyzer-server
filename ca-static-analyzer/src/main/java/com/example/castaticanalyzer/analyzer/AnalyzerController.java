package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.codereview.CodeReview;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** @InterfaceAdapter */
@Controller
public class AnalyzerController {

    private CodeAnalysisService service;

    public AnalyzerController(CodeAnalysisService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        model.addAttribute("isAnalyzing", false);
        return "profile";
    }

    @GetMapping("/analyze")
    public String analyzeGitHubCode(GithubRepo repo, Model model) {
        CodeReview review = service.reviewGitHubSourceCode(repo);
        model.addAttribute("review", review);
        model.addAttribute("isAnalyzing", true);
        return "profile";
    }
}
