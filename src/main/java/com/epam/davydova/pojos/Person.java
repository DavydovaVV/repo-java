package com.epam.davydova.pojos;

import com.epam.davydova.annotations.Entity;
import com.epam.davydova.annotations.Value;

/**
 * This is a class of pojo to work with annotations and reflection
 */
@Entity
public class Person {

    private String name;
    private int age;

    /**
     * Default constructor
     */
    public Person() {

    }

    /**
     * Constructor with all args
     *
     * @param name is a field of name
     * @param age  is a field of age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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
    @Value(destination = "D:\\YandexDisk\\Repos\\src\\main\\resources\\Record.txt")
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
    @Value(age = 2)
    public void setAge(int age) {
        this.age = age;
    }
}