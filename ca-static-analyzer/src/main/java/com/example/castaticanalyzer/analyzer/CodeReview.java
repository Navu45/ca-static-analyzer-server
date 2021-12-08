package com.example.castaticanalyzer.analyzer;


import java.util.List;

public class CodeReview {
    private List<String> problems;

    public CodeReview(List<String> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "CodeReview{" +
                "problems=" + problems.toString() +
                '}';
    }
}
