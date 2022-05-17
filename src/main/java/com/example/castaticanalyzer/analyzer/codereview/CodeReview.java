package com.example.castaticanalyzer.analyzer.codereview;


import com.example.castaticanalyzer.analyzer.problems.Problem;
import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** @DomainEntity */


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CodeReview extends RepresentationModel<CodeReview> {
    private List<Problem> problems;
    private GithubRepo repo;
    private LocalDateTime date;

    @JsonCreator
    public CodeReview(@JsonProperty("problems") List<Problem> problems,
                      @JsonProperty("repo") GithubRepo repo,
                      @JsonProperty("date") LocalDateTime date) {
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
