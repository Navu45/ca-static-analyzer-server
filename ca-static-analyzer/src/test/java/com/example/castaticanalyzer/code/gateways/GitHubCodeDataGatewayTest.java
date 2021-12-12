package com.example.castaticanalyzer.code.gateways;

import com.example.castaticanalyzer.code.DTO.GithubRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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