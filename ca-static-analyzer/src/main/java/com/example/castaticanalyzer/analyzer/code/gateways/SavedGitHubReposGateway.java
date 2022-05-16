package com.example.castaticanalyzer.analyzer.code.gateways;

import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.authentication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedGitHubReposGateway extends JpaRepository<GithubRepo, Long> {

    List<GithubRepo> findByUser(User user);
}
