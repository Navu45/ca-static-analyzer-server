package com.example.castaticanalyzer.analyzer.parsing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DependencyData implements Comparable<String>{

    private DependencyType type;
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
