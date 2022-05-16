package com.example.castaticanalyzer.analyzer.code.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


/** @DomainEntity */

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

    public boolean CheckURL() {
        try
        {
            URL u = new URL(getSourceDirURL()); // this would check for the protocol
            u.toURI(); // does the extra checking required for validation of URI
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GithubRepo{" + getFileRawContentURL("---") +
                ", " + getSourceDirURL() +
                "}";
    }
}
