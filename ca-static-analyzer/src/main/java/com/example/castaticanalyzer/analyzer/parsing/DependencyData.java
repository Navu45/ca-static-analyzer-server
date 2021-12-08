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

    public DependencyData() {
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "type=" + type +
                ", data='" + data + '\'' +
                '}';
    }

    @Override
    public int compareTo(String data) {
        if (this.data.contains(data) || data.contains(this.data)){
            return 1;
        }
        return 0;
    }
}
