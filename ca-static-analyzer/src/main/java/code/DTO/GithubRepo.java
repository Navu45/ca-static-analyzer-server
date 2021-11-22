package code.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "sourceDir", columnDefinition = "src/main")
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

    public String getFileRawContentURL(String githubFilePath) {
        return "https://raw.githubusercontent.com/" + owner + '/' + repo + "/contents" + githubFilePath;
    }
}
