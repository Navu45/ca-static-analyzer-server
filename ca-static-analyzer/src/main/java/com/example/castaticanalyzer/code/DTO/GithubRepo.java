package com.example.castaticanalyzer.code.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
@Getter
@Setter
public class GithubRepo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "repo")
    private String repo;

    @Column(name = "sourceDir")
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
        return "https://api.github.com/repos/" + '/' + owner + '/' + repo + '/' + sourceDir;
    }

    public URL getFileRawContentURL(String githubFilePath) throws MalformedURLException {
        return new URL("https://raw.githubusercontent.com/" + owner + '/' + repo + "/contents" + githubFilePath);
    }
}
