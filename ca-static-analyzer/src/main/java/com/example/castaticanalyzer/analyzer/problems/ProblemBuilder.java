package com.example.castaticanalyzer.analyzer.problems;

import com.example.castaticanalyzer.analyzer.CodeReview;
import com.example.castaticanalyzer.analyzer.parsing.CleanArchitectureLayer;
import com.example.castaticanalyzer.analyzer.parsing.DependencyData;
import com.example.castaticanalyzer.analyzer.parsing.DependencyType;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import org.springframework.stereotype.Service;

@Service
public class ProblemBuilder {

    StringBuilder result = new StringBuilder();
    public boolean messageSet = false;

    public ProblemBuilder reset() {
        result = new StringBuilder();
        messageSet = false;
        return this;
    }

    public ProblemBuilder setMessage() {
        if (!messageSet) {
            result.append("Checking dependency rule failed:").append("\n\t");
            messageSet = true;
        }
        return this;
    }

    public ProblemBuilder setDependencyError(CleanArchitectureLayer layer1, CleanArchitectureLayer layer2) {
        result.append(layer1.toString()).append(" layer depends on ")
                .append(layer2.toString()).append(" layer").append("\n\t");
        return this;
    }

    public ProblemBuilder setErrorSource(ParsedCode code1, DependencyData dependency) {
        result.append("Error found at ").append(code1.getPackageName())
                .append(" with line ").append(dependency.toString()).append("\n\t");
        return this;
    }

    public String getResult() {
        return result.toString();
    }
}
