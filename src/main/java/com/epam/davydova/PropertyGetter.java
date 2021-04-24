package com.epam.davydova;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a class to get properties from file
 */
public class PropertyGetter {

    private static final Logger logger = LoggerFactory.getLogger(PropertyGetter.class);

    private static final String PATH_TO_FILE_WITH_PROPERTIES = "src/main/resources/Properties.txt";

    /**
     * Get properties from given file
     *
     * @return HashMap of field set from file
     */
    public HashMap<String, ArrayList<String>> getPropertiesFromFile() {
        int numberOfFieldSet = 0;
        HashMap<String, ArrayList<String>> properties = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_FILE_WITH_PROPERTIES))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    String key = line.substring(0, line.lastIndexOf('='));
                    String value = line.substring(line.lastIndexOf('=') + 1);
                    if (!properties.containsKey(key)) {
                        ArrayList<String> values = new ArrayList<>();
                        values.add(value);
                        properties.put(key, values);
                    } else {
                        ArrayList<String> values = properties.get(key);
                        values.add(value);
                        properties.replace(key, values);
                    }
                } else {
                    if (!properties.isEmpty()) {
                        numberOfFieldSet++;
                    } else {
                        logger.info("File is empty");
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Exception is:\n" + e);
        }
        logger.info("Number of sets [" + (numberOfFieldSet + 1) + "] is ready for record to entities");
        ArrayList<String> numberOfFieldSetsContainer = new ArrayList<>();
        numberOfFieldSetsContainer.add(Integer.toString(numberOfFieldSet));
        properties.put("numberOfFieldSets", numberOfFieldSetsContainer);
        return properties;
    }
}
