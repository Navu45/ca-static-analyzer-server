package com.example.castaticanalyzer.analyzer.antlrImpl;

import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaLexer;
import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParser;
import com.example.castaticanalyzer.code.entity.CodeReview;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.stereotype.Component;

@Component
public class AntlrAnalyzer {

    public CodeReview parseCode(String code)
    {
        JavaParser javaParser = (JavaParser) initializeParser(code);
        CleanArchitectureVisitor visitor = new CleanArchitectureVisitor();
        JavaParser.CompilationUnitContext tree = javaParser.compilationUnit();
        return visitor.visit(tree);
    }


    private Parser initializeParser(String code) {
        CharStream inputStream = CharStreams.fromString(code);
        JavaLexer javaLexer = new JavaLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(javaLexer);
        return new JavaParser(commonTokenStream);
    }
}
