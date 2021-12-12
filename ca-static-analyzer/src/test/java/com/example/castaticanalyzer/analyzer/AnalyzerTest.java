package com.example.castaticanalyzer.analyzer;

import com.example.castaticanalyzer.analyzer.parsing.CodeParser;
import com.example.castaticanalyzer.analyzer.problems.ProblemBuilder;
import com.example.castaticanalyzer.code.DTO.Code;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AnalyzerTest {

    String code1 = "package com.example.castaticanalyzer.analyzer.parsing;\n" +
            "\n" +
            "import com.example.castaticanalyzer.analyzer.antlrImpl.CleanArchitectureVisitor;\n" +
            "import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaLexer;\n" +
            "import com.example.castaticanalyzer.analyzer.antlrGenerated.JavaParser;\n" +
            "import com.example.castaticanalyzer.analyzer.AnalyzerController;\n" +
            "import com.example.castaticanalyzer.analyzer.CodeAnalysisService;\n" +
            "import com.example.castaticanalyzer.code.DTO.Code;\n" +
            "import org.antlr.v4.runtime.CharStream;\n" +
            "import org.antlr.v4.runtime.CharStreams;\n" +
            "import org.antlr.v4.runtime.CommonTokenStream;\n" +
            "import org.antlr.v4.runtime.Parser;\n" +
            "import org.springframework.stereotype.Component;\n" +
            "\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/** @DomainEntity */\n" +
            "@Component\n" +
            "public class CodeParser {\n" +
            "    AnalyzerController controller;\n" +
            "    CodeAnalysisService service;\n" +
            "\n" +
            "    public List<ParsedCode> getCodeParsedCode(List<Code> codeList) {\n" +
            "        List<ParsedCode> parsedCodeList = new ArrayList<>();\n" +
            "        for (Code code :\n" +
            "                codeList) {\n" +
            "            ParsedCode review = parseCode(code.getData());\n" +
            "            review.setSourceCodeName(code.getName());\n" +
            "            parsedCodeList.add(review);\n" +
            "        }\n" +
            "        return parsedCodeList;\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    public ParsedCode parseCode(String code)\n" +
            "    {\n" +
            "        JavaParser javaParser = (JavaParser) initializeParser(code);\n" +
            "        CleanArchitectureVisitor visitor = new CleanArchitectureVisitor();\n" +
            "        JavaParser.CompilationUnitContext tree = javaParser.compilationUnit();\n" +
            "        return visitor.visit(tree);\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private Parser initializeParser(String code) {\n" +
            "        CharStream inputStream = CharStreams.fromString(code);\n" +
            "        JavaLexer javaLexer = new JavaLexer(inputStream);\n" +
            "        CommonTokenStream commonTokenStream = new CommonTokenStream(javaLexer);\n" +
            "        return new JavaParser(commonTokenStream);\n" +
            "    }\n" +
            "}\n";

    String code2 = "package com.example.castaticanalyzer.analyzer.controllers;\n" +
            "\n" +
            "/** @InterfaceAdapter */\n" +
            "public class AnalyzerController {\n" +
            "}\n";

    String code3 = "package com.example.castaticanalyzer.analyzer.services;\n" +
            "\n" +
            "import com.example.castaticanalyzer.code.DTO.Code;\n" +
            "import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;\n" +
            "import com.example.castaticanalyzer.code.DTO.GithubRepo;\n" +
            "import com.example.castaticanalyzer.code.gateways.GitHubCodeDataGateway;\n" +
            "import org.springframework.stereotype.Service;\n" +
            "\n" +
            "import java.io.IOException;\n" +
            "import java.util.List;\n" +
            "\n" +
            "/** @UseCase */\n" +
            "\n" +
            "@Service\n" +
            "public class CodeAnalysisService {\n" +
            "\n" +
            "    GitHubCodeDataGateway githubCodeDataGateway;\n" +
            "\n" +
            "\n" +
            "    public ParsedCode reviewGitHubSourceCode(GithubRepo repo) {\n" +
            "        ParsedCode parsedCode = new ParsedCode();\n" +
            "        List<Code> codeList;\n" +
            "        try {\n" +
            "            codeList = githubCodeDataGateway.getSourceCode(repo);\n" +
            "        } catch (IOException e) {\n" +
            "            e.printStackTrace();\n" +
            "        }\n" +
            "        return parsedCode;\n" +
            "    }\n" +
            "}\n";
    @TestConfiguration
    static class TestConfig {

        @Bean
        public Analyzer analyzer() {
            return new Analyzer(parser(), builder());
        }

        @Bean
        public ProblemBuilder builder() {
            return new ProblemBuilder();
        }

        @Bean
        public CodeParser parser() {
            return new CodeParser();
        }

    }

    @Autowired
    Analyzer analyzer;

    @Test
    void checkDependencyRule() {
        List<Code> list = new ArrayList<>();
        list.add(new Code("com/example/castaticanalyzer/analyzer/parsing/CodeParser.java", code1));
        list.add(new Code("com/example/castaticanalyzer/analyzer/controllers/AnalyzerController.java", code2));
        list.add(new Code("com/example/castaticanalyzer/analyzer/services/CodeAnalysisService.java", code3));
        System.out.println(analyzer.analyze(list));

    }
}