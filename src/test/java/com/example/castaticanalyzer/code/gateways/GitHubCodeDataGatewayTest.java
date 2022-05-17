package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;
import com.example.castaticanalyzer.analyzer.code.gateways.GitHubCodeDataGateway;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
class GitHubCodeDataGatewayTest {

    GithubRepo repo = new GithubRepo("Navu45", "CarSharing", "project/src/main");

    @Autowired
    GitHubCodeDataGateway gateway;

    @Test
    void getSourceCode() throws IOException {
        System.out.println(repo.toString());
        System.err.println(gateway.getSourceCode(repo));
    }
}