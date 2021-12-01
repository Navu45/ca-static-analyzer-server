package com.example.castaticanalyzer.code.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(schema = "public", name = "dependency_data")
public class DependencyData {
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
    public String toString() {
        return "DependencyData{" +
                "type=" + type +
                ", data='" + data + '\'' +
                '}';
    }
}
