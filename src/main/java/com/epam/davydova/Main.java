package com.epam.davydova;

import com.epam.davydova.pojos.Cat;
import com.epam.davydova.pojos.Person;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        EntityGenerator.generateEntities(Person.class,
                new File("D:\\YandexDisk\\Repos\\src\\main\\resources\\Record.txt"));
        CheckPojos.checkEntityAnnotation(Person.class);
        CheckPojos.checkEntityAnnotation(Cat.class);
    }
}
