package com.example.castaticanalyzer.analyzer.antlrImpl;

import org.junit.jupiter.api.Test;

class AntlrAnalyzerTest {

    String codeData = "package com.example.castaticanalyzer.code.DTO;\n" +
            "\n" +
            "import lombok.Getter;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/** @DomainEntity */\n" +
            "@Getter\n" +
            "public class CodeModel {\n" +
            "\n" +
            "    List<String> sourceCode;\n" +
            "    String[] targetConcepts;\n" +
            "\n" +
            "\n" +
            "    public CodeModel(List<String> sourceCode, String[] targetConcepts) {\n" +
            "        this.sourceCode = sourceCode;\n" +
            "        this.targetConcepts = targetConcepts;\n" +
            "    }\n" +
            "}\n";

    AntlrAnalyzer analyzer = new AntlrAnalyzer();

    @Test
    void parseCode() {
        System.out.println(analyzer.parseCode(codeData));
    }
}