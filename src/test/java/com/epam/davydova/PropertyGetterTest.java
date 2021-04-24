package com.epam.davydova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a class to test PropertyGetter class
 */
public class PropertyGetterTest {

    @Test
    public void getPropertiesFromFileTest() {
        PropertyGetter propertyGetter = new PropertyGetter();

        assertEquals("Tom", propertyGetter.getPropertiesFromFile().get("name").get(0));
    }
}