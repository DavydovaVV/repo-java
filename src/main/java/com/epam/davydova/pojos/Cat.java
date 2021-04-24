package com.epam.davydova.pojos;

import com.epam.davydova.annotations.Entity;
import com.epam.davydova.annotations.Value;

/**
 * This is a class of pojo to work with annotations and reflection
 */
@Entity
public class Cat {

    @Value(value = "Ginger")
    private String name;
    @Value(value = "11")
    private int age;

    /**
     * Setter of field name
     *
     * @param name is a field of name
     */
    @Value(path = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter of field age
     *
     * @param age is a field of age
     */
    @Value(path = "age")
    public void setAge(int age) {
        this.age = age;
    }
}