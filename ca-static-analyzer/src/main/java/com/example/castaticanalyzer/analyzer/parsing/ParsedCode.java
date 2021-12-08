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

    private boolean compareDependenciesWith(String sourceCodeName) {
        boolean hasSuchDependency = false;
        for (DependencyData dependency :
                this.dependencies) {
            if (dependency.compareTo(sourceCodeName) != 0) {
                hasSuchDependency = true;
            }
        }
        return hasSuchDependency;
    }

    public List<DependencyData> getErrorDependency(ParsedCode code) {
        List<DependencyData> list = new ArrayList<>();
        for (DependencyData dependency :
                this.dependencies) {
            if (dependency.compareTo(code.getSourceCodeName()) != 0) {
                list.add(dependency);
            }
        }
        return list;
    }

    public boolean haveAsDependency(ParsedCode code) {
        return this.compareDependenciesWith(code.getSourceCodeName());
    }

    @Override
    public String toString() {
        return "Parsed{" +
                "sourceCodeName" + sourceCodeName +
                ", dependencies=" + dependencies +
                ", packageName='" + packageName + '\'' +
                ", cleanArchitectureLayer='" + cleanArchitectureLayer + '\'' +
                '}';
    }
}
