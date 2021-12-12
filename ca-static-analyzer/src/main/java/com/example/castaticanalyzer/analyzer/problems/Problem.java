package com.example.castaticanalyzer.analyzer.problems;

import lombok.Getter;
import lombok.Setter;
/** @DomainEntity */
@Getter
@Setter
public class Problem {
    private String text;
    private ProblemType type;

    public Problem(String text, ProblemType type) {
        this.text = text;
        this.type = type;
    }

    @Override
    public String toString() {
        return text;
    }
}
