package com.epam.davydova.task2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is a class to get the detail from file using stream
 */
@Slf4j
public class DetailWithStream {

    private final String PATH_TO_FILE_WITH_DETAILS = "src/main/resources/File.txt";

    /**
     * Get details from file
     *
     * @return map with value of fields of class Sausage
     * @throws IOException
     */
    public Map<String, List<String>> getDetails() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(PATH_TO_FILE_WITH_DETAILS));

        Map<String, List<String>> mapOfDetails =
                stream.map(this::decodeBase64)
                        .flatMap(s -> Arrays.stream(s.split(", ")))
                        .collect(Collectors.groupingBy(s -> s.substring(0, s.indexOf("="))));

        return mapOfDetails;
    }

    /**
     * Decode a line with Base64 encoding
     *
     * @param encodedLine is a line encoded with Base64 encoding
     * @return decoded line
     */
    public String decodeBase64(String encodedLine) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedLine);

        return new String(decodedBytes);
    }
}
