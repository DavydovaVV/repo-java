package com.epam.davydova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyGetterTest {

    @Test
    void getPropertiesFromFileTest() {
        PropertyGetter propertyGetter = new PropertyGetter();

        assertEquals("Tom", propertyGetter.getPropertiesFromFile().get("name").get(0));
    }
}