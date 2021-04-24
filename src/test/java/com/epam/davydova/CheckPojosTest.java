package com.epam.davydova;

import com.epam.davydova.pojos.Cat;
import com.epam.davydova.pojos.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is a class to test CheckPojos class
 */
public class CheckPojosTest {

    CheckPojos pojosCheck = new CheckPojos();

    @Test
    public void checkIsPresentEntityAnnotation() {
        assertTrue(pojosCheck.checkEntityAnnotation(Person.class));

        assertTrue(pojosCheck.checkEntityAnnotation(Cat.class));
    }

    @Test
    public void checkValueAnnotationIsPresent() {
        assertTrue(pojosCheck.checkValueAnnotation(Person.class));

        assertTrue(pojosCheck.checkValueAnnotation(Cat.class));
    }
}