package com.epam.davydova.task2;

/**
 * This is a POJO class with the details on sausages
 */
public class Sausage {
    private String type;
    private int weight;
    private long cost;

    public Sausage(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }
}
