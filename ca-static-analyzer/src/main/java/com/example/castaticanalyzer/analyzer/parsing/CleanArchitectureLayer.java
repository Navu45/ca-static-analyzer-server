package com.example.castaticanalyzer.analyzer.parsing;

/** @DomainEntity */

public enum CleanArchitectureLayer implements Comparable<CleanArchitectureLayer>{
    NOT_DEFINED (0), DOMAIN (1), USE_CASE (2), INTERFACE_ADAPTER(3), FRAMEWORK(4);

    private final int level;

    CleanArchitectureLayer(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
