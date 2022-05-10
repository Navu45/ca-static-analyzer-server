package com.example.castaticanalyzer.analyzer.parsing;

import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaLexer;
import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParser;
import com.example.castaticanalyzer.analyzer.antlrImpl.CleanArchitectureVisitor;
import com.example.castaticanalyzer.analyzer.code.DTO.Code;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** @DomainEntity */
@Component
public class CodeParser {

    JavaParser javaParser;

    public List<ParsedCode> getCodeParsedCode(List<Code> codeList) {
        List<ParsedCode> parsedCodeList = new ArrayList<>();
        for (Code code :
                codeList) {
            ParsedCode review = parseCode(code.getData());
            review.setSourceCodeName(code.getName());
            parsedCodeList.add(review);
        }
        return parsedCodeList;
    }

    public ParsedCode parseCode(String code)
    {
        initializeParser(code);

        CleanArchitectureVisitor visitor = new CleanArchitectureVisitor();
        return visitor.visit(javaParser.compilationUnit());
    }

    private void initializeParser(String code) {
        CharStream inputStream = CharStreams.fromString(code);
        JavaLexer javaLexer = new JavaLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(javaLexer);
        javaParser = new JavaParser(commonTokenStream);
    }
}
