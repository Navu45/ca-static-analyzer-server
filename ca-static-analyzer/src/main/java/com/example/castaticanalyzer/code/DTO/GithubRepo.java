package com.example.castaticanalyzer.code.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubRepo {
    private Long id;

    private String owner;

    private String repo;

    private String sourceDir;

    public GithubRepo() {
    }

    public GithubRepo(String owner, String repo, String sourceDir) {
        this.owner = owner;
        this.repo = repo;
        this.sourceDir = sourceDir;
    }

    public GithubRepo(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
    }

    public String getSourceDirURL() {
        return "https://api.github.com/repos/" + owner + '/' + repo + "/contents/" + sourceDir;
    }

    public String getFileRawContentURL(String githubFilePath) {
        return "https://raw.githubusercontent.com/" + owner + '/' + repo + "/master/" + githubFilePath;
    }

    @Override
    public String toString() {
        return "GithubRepo{" + getFileRawContentURL("---") +
                ", " + getSourceDirURL() +
                "}";
    }
}
