package com.cloud.computing.edziennik.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum Grade {

    SIX("6", 6),
    FIVE_PLUS("5+", 5.5),
    FIVE("5", 5),
    FIVE_MINUS("5-", 4.75),
    FOUR_PLUS("4+", 4.5),
    FOUR("4", 4),
    FOUR_MINUS("4-", 3.75),
    TREE_PLUS("3+", 3.5),
    TREE("3", 3),
    TREE_MINUS("3-", 2.75),
    TWO_PLUS("2+", 2.5),
    TWO("2", 2),
    TWO_MINUS("2-", 1.75),
    ONE_PLUS("1+", 1.5),
    ONE("1", 1);

    private String name;
    private BigDecimal value;

    private static final Map<String, Grade> lookup = new HashMap<>();

    Grade(String name, double value) {
        this.name = name;
        this.value = new BigDecimal(value);
    }

    static {
        for (Grade d : Grade.values()) {
            lookup.put(d.getName(), d);
        }
    }

    public static Grade get(String name) {
        return lookup.get(name);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}