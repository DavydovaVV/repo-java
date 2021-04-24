package com.epam.davydova;

import com.epam.davydova.pojos.Cat;
import com.epam.davydova.pojos.Person;

public class Main {

    public static void main(String[] args) {
        EntityGenerator entityGenerator = new EntityGenerator();

        entityGenerator.generateEntities(Person.class);
        entityGenerator.generateEntities(Cat.class);
    }
}
