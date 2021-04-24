package com.epam.davydova;

/**
 * This is a class to generate Entities with fields pulled from given file
 */
public class EntityGenerator {

    /**
     * Generate entities
     *
     * @param object is an object of Class class
     */
    public void generateEntities(Class<?> object) {
        CheckPojos checkPojos = new CheckPojos();
        checkPojos.checkEntityAnnotation(object);
    }
}
