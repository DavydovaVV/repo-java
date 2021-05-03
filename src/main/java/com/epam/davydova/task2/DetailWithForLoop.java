package com.epam.davydova.task2;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This is a class to get the detail from file using for-loop
 */
@Slf4j
public class DetailWithForLoop {

    /**
     * Get details from file
     *
     * @return map with value of fields for class Sausage
     */
    public Map<String, List<String>> getDetails(String fileName) {
        Map<String, List<String>> mapOfDetails = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String decodedLine = decodeBase64(line);
                for (String element : decodedLine.split(", ")) {
                    String key = element.substring(0, element.indexOf("="));
                    String value = element.substring(element.indexOf("=") + 1);

                    if (!mapOfDetails.containsKey(key)) {
                        List<String> values = new ArrayList<>();
                        values.add(value);
                        mapOfDetails.put(key, values);
                    } else {
                        List<String> values = mapOfDetails.get(key);
                        values.add(value);
                        mapOfDetails.replace(key, values);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Exception is: ", e);
        }
        return mapOfDetails;
    }

    /**
     * Decode a line with Base64 encoding
     *
     * @param encodedLine is a line encoded with Base64 encoding
     * @return decoded line
     */
    private String decodeBase64(String encodedLine) {
        byte[] decodedBytes = Base64.getDecoder()
                .decode(encodedLine);

        return new String(decodedBytes);
    }
}
