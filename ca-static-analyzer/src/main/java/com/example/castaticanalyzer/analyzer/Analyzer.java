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

    CodeParser parser;

    ProblemBuilder builder;

    public CodeReview checkDependencyRule(List<Code> codeList) {
        builder.reset();
        List<String> problems = new ArrayList<>();
        List<ParsedCode> parsedCodeList = parser.getCodeParsedCode(codeList);
        for (int i = 0; i < parsedCodeList.size(); i++) {
            for (int j = i + 1; j < parsedCodeList.size(); j++) {
                ParsedCode code1 = parsedCodeList.get(i);
                System.err.println(code1.toString() + i);
                ParsedCode code2 = parsedCodeList.get(j);
                System.err.println(code2.toString() + j);
                if (code1.haveAsDependency(code2) == 1 && fromLowerLevel(code1, code2))
                {
                    DependencyData dependencyData = code1.getErrorDependency(code2);
                    builder.setMessage()
                            .setDependencyError(code1.getLayer(),code2.getLayer())
                            .setErrorSource(code1, dependencyData);
                    problems.add(builder.getResult());
                }
            }
        }
        return new CodeReview(problems);
    }

    private boolean fromLowerLevel(ParsedCode code1, ParsedCode code2) {
        return code1.getLayerLevel() < code2.getLayerLevel();
    }
}
