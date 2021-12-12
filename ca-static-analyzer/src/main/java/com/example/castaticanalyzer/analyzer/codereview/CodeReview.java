package com.example.castaticanalyzer.analyzer.codereview;


import com.example.castaticanalyzer.analyzer.problems.Problem;
import com.example.castaticanalyzer.code.DTO.GithubRepo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class CodeReview {
    private List<Problem> problems;
    private GithubRepo repo;
    private LocalDateTime date;

    public CodeReview(List<Problem> problems, GithubRepo repo, LocalDateTime date) {
        this.problems = problems;
        this.repo = repo;
        this.date = date;
    }

    public String getDate() {
        return DateTimeFormatter.ofPattern("HH:mm MM-dd-yy").format(date);
    }

    @Override
    public String toString() {
        return "CodeReview{" +
                "problems=" + problems.toString() +
                "date=" + date +
                '}';
    }
}
