package com.epam.davydova;

import com.epam.davydova.pojos.Cat;
import com.epam.davydova.pojos.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is a class to test CheckPojos class
 */
class CheckPojosTest {

    @Test
    public void checkIsPresentEntityAnnotation() {
        CheckPojos pojosCheck = new CheckPojos();

        assertTrue(pojosCheck.checkEntityAnnotation(Person.class));

        assertTrue(pojosCheck.checkEntityAnnotation(Cat.class));
    }
}