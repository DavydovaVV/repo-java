package com.epam.davydova;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a class to generate Entities with fields pulled from given file
 */
public class EntityGenerator {

    private static final Logger logger = Logger.getLogger(FileGenerator.class.getName());

    /**
     * Generate entities
     *
     * @param object is an object of Class class
     * @param file   is a file with sets of field values
     */
    public static void generateEntities(Class<?> object, File file) {
        FileGenerator.generateFile(5);
        try {
            CheckPojos.getPropertiesFromFile(file.getAbsolutePath(), object);
        } catch (NoSuchMethodException e) {
            logger.log(Level.SEVERE, "Exception is: " + e);
        }
    }
}
