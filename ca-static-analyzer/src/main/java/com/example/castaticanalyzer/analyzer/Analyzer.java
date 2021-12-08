package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.parsing.CodeParser;
import com.example.castaticanalyzer.analyzer.parsing.DependencyData;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import com.example.castaticanalyzer.analyzer.problems.ProblemBuilder;
import com.example.castaticanalyzer.code.DTO.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Analyzer {
    @Autowired
    CodeParser parser;
    @Autowired
    ProblemBuilder builder;

    public CodeReview checkDependencyRule(List<Code> codeList) {
        builder.reset();
        List<String> problems = new ArrayList<>();
        List<ParsedCode> parsedCodeList = parser.getCodeParsedCode(codeList);
        List<DependencyData> dependencies = new ArrayList<>();

        for (int i = 0; i < parsedCodeList.size(); i++) {
            ParsedCode code1 = parsedCodeList.get(i);
            for (int j = i + 1; j < parsedCodeList.size(); j++) {
                ParsedCode code2 = parsedCodeList.get(j);
                if (code1.haveAsDependency(code2) && fromLowerLevel(code1, code2)
                        || code2.haveAsDependency(code1) && fromLowerLevel(code2, code1))
                {
                    List<DependencyData> dependencyList = code1.getErrorDependency(code2);
                    for (DependencyData dependency :
                            dependencyList) {
                        if (!dependencies.contains(dependency)) {
                            builder.setMessage()
                                    .setDependencyError(code1.getLayer(), code2.getLayer())
                                    .setErrorSource(code1, dependency);
                            dependencies.add(dependency);
                        }
                    }
                }
            }
        }
        problems.add(builder.getResult());
        return new CodeReview(problems);
    }

    private boolean fromLowerLevel(ParsedCode code1, ParsedCode code2) {
        return code1.getLayerLevel() < code2.getLayerLevel();
    }
}
