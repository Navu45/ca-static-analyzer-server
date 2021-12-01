package com.example.castaticanalyzer.code.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class CodeReview {
    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinTable(name = "employees_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<DependencyData> dependencies = new ArrayList<>();
    @Column
    private String packageName;
    @Column
    private String cleanArchitectureLayer;


    public void addImport(String importStr) {
        dependencies.add(new DependencyData(DependencyType.IMPORT, importStr));
    }

    public void addField(String fieldStr) {
        dependencies.add(new DependencyData(DependencyType.FIELD, fieldStr));
    }

    public CodeReview() {
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
