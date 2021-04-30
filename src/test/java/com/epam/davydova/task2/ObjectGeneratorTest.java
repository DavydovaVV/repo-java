package com.epam.davydova.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a class to test ObjectGenerator class
 */
public class ObjectGeneratorTest {

    ObjectGenerator objectGenerator = new ObjectGenerator();

    DetailWithStream detailWithStream = new DetailWithStream();

    @Test
    public void generateObject() {
        Map<String, List<String>> mapOfDetails = detailWithStream.getDetails("src/main/resources/File.txt");

        int expectedResult = 10;
        int actualResult = objectGenerator.generateObject(mapOfDetails, Sausage.class);

        assertEquals(expectedResult, actualResult);
    }
}