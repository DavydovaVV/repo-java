package com.epam.davydova.task2;

import com.epam.davydova.task2.annotations.Value;

/**
 * This is a POJO class with the details on sausages
 */
public class Sausage {
    @Value(path = "type")
    private String type;
    @Value(path = "weight")
    private int weight;
    @Value(path = "cost")
    private long cost;
}
