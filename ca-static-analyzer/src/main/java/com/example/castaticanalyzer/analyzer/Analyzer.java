package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.parsing.CodeParser;
import com.example.castaticanalyzer.analyzer.parsing.DependencyData;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import com.example.castaticanalyzer.analyzer.problems.Problem;
import com.example.castaticanalyzer.analyzer.problems.ProblemBuilder;
import com.example.castaticanalyzer.code.DTO.Code;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Analyzer {

    CodeParser parser;
    ProblemBuilder builder;

    public Analyzer(CodeParser parser, ProblemBuilder builder) {
        this.parser = parser;
        this.builder = builder;
    }

    public List<Problem> analyze(List<Code> codeList) {
        builder.reset();
        List<ParsedCode> parsedCodeList = parser.getCodeParsedCode(codeList);
        List<DependencyData> currentDependencies = new ArrayList<>();

        for (int i = 0; i < parsedCodeList.size(); i++) {
            ParsedCode code1 = parsedCodeList.get(i);
            for (int j = i + 1; j < parsedCodeList.size(); j++) {
                ParsedCode code2 = parsedCodeList.get(j);
                if (cleanArchitectureLayerExists(code1)
                        && cleanArchitectureLayerExists(code2)) {
                    currentDependencies.addAll(checkDependencyRule(code1, code2, currentDependencies));
                }
            }
            setWarnings(code1);
        }
        return builder.getResult();
    }

    private void setWarnings(ParsedCode code) {
        if (!cleanArchitectureLayerExists(code))
            builder.setNotDefinedLayerWarning(code);
    }

    public boolean cleanArchitectureLayerExists(ParsedCode code) {
        return code.getLayerLevel() != 0;
    }

    public List<DependencyData> checkDependencyRule(ParsedCode code1, ParsedCode code2,
                                                    List<DependencyData> currentDependencies) {
        List<DependencyData> dependencies = new ArrayList<>();
        if (code1.haveAsDependency(code2) && fromLowerLevel(code1, code2)
                || code2.haveAsDependency(code1) && fromLowerLevel(code2, code1))
        {
            List<DependencyData> dependencyList = code1.getErrorDependency(code2);
            for (DependencyData dependency :
                    dependencyList) {
                if (!currentDependencies.contains(dependency)) {
                    builder.setMessage()
                            .setDependencyError(code1.getLayer(), code2.getLayer())
                            .setErrorSource(code1, dependency);
                    dependencies.add(dependency);
                }
            }
        }
        return dependencies;
    }

    private boolean fromLowerLevel(ParsedCode code1, ParsedCode code2) {
        return code1.getLayerLevel() < code2.getLayerLevel();
    }
}
