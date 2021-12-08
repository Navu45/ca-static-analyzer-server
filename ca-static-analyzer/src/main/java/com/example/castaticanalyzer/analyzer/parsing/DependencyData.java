package com.example.castaticanalyzer.analyzer.parsing;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(schema = "public", name = "dependency_data")
public class DependencyData implements Comparable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private DependencyType type;

    @Column(name = "data")
    private String data;

    public DependencyData(DependencyType type, String data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependencyData that = (DependencyData) o;
        return data.equals(that.data);
    }

    public DependencyData() {
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "type=" + type +
                ", line='" + data + '\'';
    }

    @Override
    public int compareTo(String data) {
        if (this.data.contains(data)){
            return 1;
        }
        return 0;
    }
}
