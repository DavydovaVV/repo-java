package com.epam.davydova.task2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This is a class to get the detail from file using stream
 */
@Slf4j
public class DetailWithStream {

    /**
     * Get details from file
     *
     * @return map with value of fields for class Sausage
     */
    public Map<String, List<String>> getDetails(String fileName) {
        Map<String, List<String>> mapOfDetails = null;
        try {
            mapOfDetails = Files.lines(Paths.get(fileName))
                    .map(this::decodeBase64)
                    .flatMap(s -> Arrays.stream(s.split(", ")))
                    .collect(Collectors.groupingBy(s -> s.substring(0, s.indexOf("="))));
        } catch (IOException e) {
            log.error("Exception is: ", e);
        }

        return Objects.requireNonNull(mapOfDetails);
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
