package com.example.castaticanalyzer.analyzer.code.DTO;

import com.example.castaticanalyzer.authentication.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


/** @DomainEntity */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@Table(name = "repos", schema = "public")
public class GithubRepo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repo_id")
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_repos",
            joinColumns = @JoinColumn(name = "repo_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;
    @Column
    private String owner;
    @Column
    private String repo;
    @Column
    private String sourceDir;

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
        } catch (MalformedURLException | URISyntaxException e) {
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
