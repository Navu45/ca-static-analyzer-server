package com.example.castaticanalyzer.analyzer.code.gateways;

import com.example.castaticanalyzer.analyzer.code.DTO.Code;
import com.example.castaticanalyzer.analyzer.code.DTO.GithubRepo;

import java.io.IOException;
import java.util.List;
/** @UseCase */

public interface CodeDataGateway {
    List<Code> getSourceCode(GithubRepo repo) throws IOException;
}
