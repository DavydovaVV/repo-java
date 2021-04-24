package com.epam.davydova;

import com.epam.davydova.pojos.Cat;
import com.epam.davydova.pojos.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CheckPojos checkPojos = new CheckPojos();


        Class person = Person.class;
        Field[] personFields = person.getDeclaredFields();
        Method[] personMethods = person.getMethods();

        Class cat = Cat.class;
        Field[] catFields = cat.getDeclaredFields();
        Method[] catMethods = cat.getMethods();

        checkPojos.checkEntityAnnotation(person);
        checkPojos.checkEntityAnnotation(cat);

        checkPojos.checkValueHasEntityAnnotation(person);
        checkPojos.checkValueHasEntityAnnotation(cat);

        checkPojos.fillUpFields(person, personFields);
        checkPojos.fillUpFields(person, personMethods);

        checkPojos.fillUpFields(cat, catFields);
        checkPojos.fillUpFields(cat, catMethods);

        EntityGenerator entityGenerator = new EntityGenerator();

        entityGenerator.generateEntities(Person.class);
        entityGenerator.generateEntities(Cat.class);
    }
}
