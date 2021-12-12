package com.example.castaticanalyzer.analyzer.problems;

import com.example.castaticanalyzer.analyzer.parsing.CleanArchitectureLayer;
import com.example.castaticanalyzer.analyzer.parsing.DependencyData;
import com.example.castaticanalyzer.analyzer.parsing.ParsedCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProblemBuilder {

    StringBuilder result = new StringBuilder();
    List<Problem> problems = new ArrayList<>();
    public boolean messageSet = false;

    public void reset() {
        result = new StringBuilder();
    }

    public ProblemBuilder setMessage() {
        if (!messageSet) {
            problems.add(new Problem("Checking Dependency rule failed:", ProblemType.ERROR));
            messageSet = true;
        }
        return this;
    }

    public ProblemBuilder setDependencyError(CleanArchitectureLayer layer1, CleanArchitectureLayer layer2) {
        result.append(layer1.toString())
                .append(" layer depends on ")
                .append(layer2.toString())
                .append(" layer");
        return this;
    }

    public void setErrorSource(ParsedCode code1, DependencyData dependency) {
        String error = result.append("Error found at ")
                .append(code1.getPackageName())
                .append(".")
                .append(code1.getSourceCodeName())
                .append(" with ")
                .append(dependency.toString())
                .toString();
        problems.add(new Problem(error, ProblemType.ERROR));
        reset();
    }

    public void setNotDefinedLayerWarning(ParsedCode code) {
        String warning = result.append("Not defined Clean architecture layer at ")
                .append(code.getPackageName())
                .append(".")
                .append(code.getSourceCodeName())
                .append("\n")
                .toString();
        problems.add(new Problem(warning, ProblemType.WARNING));
        reset();
    }

    public List<Problem> getResult() {
        reset();
        messageSet = false;
        return problems;
    }
}
