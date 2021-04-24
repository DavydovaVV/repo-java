package com.epam.davydova.pojos;

import com.epam.davydova.annotations.Entity;
import com.epam.davydova.annotations.Value;

/**
 * This is a class of pojo to work with annotations and reflection
 */
@Entity
public class Person {

    @Value(path = "name")
    private String name;
    @Value(path = "age")
    private int age;

    /**
     * Setter of field name
     *
     * @param name is a field of name
     */
    @Value(value = "Cris")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter of field age
     *
     * @param age is a field of age
     */
    @Value(value = "7")
    public void setAge(int age) {
        this.age = age;
    }
}