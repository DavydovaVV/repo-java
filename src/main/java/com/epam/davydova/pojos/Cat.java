package com.epam.davydova.pojos;

import com.epam.davydova.annotations.Value;

/**
 * This is a class of pojo to work with annotations and reflection
 */
public class Cat {

    @Value
    private String name;
    @Value(name = "Tom", age = 11)
    private int age;

    /**
     * Default constructor
     */
    public Cat() {

    }

    /**
     * Getter of field name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of field name
     *
     * @param name is a field of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of field age
     *
     * @return value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter of field age
     *
     * @param age is a field of age
     */
    public void setAge(int age) {
        this.age = age;
    }
}