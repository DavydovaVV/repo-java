package com.epam.davydova.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a class to test ObjectGenerator class
 */
public class ObjectGeneratorTest {
    private static final String PATH_TO_FILE_WITH_DETAILS = "src/test/resources/File.txt";

    ObjectGenerator objectGenerator = new ObjectGenerator();

    DetailWithStream detailWithStream = new DetailWithStream();

    @Test
    public void generateObject() {
        Map<String, List<String>> mapOfDetails = detailWithStream.getDetails(PATH_TO_FILE_WITH_DETAILS);

        int expectedResult = 10;
        int actualResult = objectGenerator.generateObject(mapOfDetails, Sausage.class);

        assertEquals(expectedResult, actualResult);
    }
}