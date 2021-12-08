package com.example.castaticanalyzer.analyzer.parsing;

import com.example.castaticanalyzer.code.DTO.Code;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ParsedCode {
    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinTable(name = "code_dependencies",
            joinColumns = @JoinColumn(name = "dependency_id"),
            inverseJoinColumns = @JoinColumn(name = "code_id"))
    private List<DependencyData> dependencies = new ArrayList<>();
    @Column
    private String packageName;
    @Column
    private CleanArchitectureLayer cleanArchitectureLayer;
    @Column
    private String sourceCodeName;

    public void addDependency(DependencyType type, String data) {
        DependencyData dependency = new DependencyData(type, data);
        if (!dependencies.contains(dependency))
            dependencies.add(dependency);
    }

    public ParsedCode() {
    }

    public CleanArchitectureLayer getLayer() {
        return cleanArchitectureLayer;
    }

    public int getLayerLevel() {
        return getCleanArchitectureLayer().getLevel();
    }

    private int compareDependenciesWith(String sourceCodeName) {
        int hasSuchDependency = 0;
        for (DependencyData dependency :
                dependencies) {
            if (dependency.compareTo(sourceCodeName) != 0) {
                hasSuchDependency = dependency.compareTo(sourceCodeName);
                break;
            }
        }
        return hasSuchDependency;
    }

    public DependencyData getErrorDependency(ParsedCode code) {
        for (DependencyData dependency :
                dependencies) {
            if (dependency.compareTo(code.getSourceCodeName()) != 0) {
                return dependency;
            }
        }
        return null;
    }

    public int haveAsDependency(ParsedCode code) {
        return this.compareDependenciesWith(code.getSourceCodeName());
    }

    @Override
    public String toString() {
        return "CodeReview{" +
                "dependencies=" + dependencies +
                ", packageName='" + packageName + '\'' +
                ", cleanArchitectureLayer='" + cleanArchitectureLayer + '\'' +
                '}';
    }
}
