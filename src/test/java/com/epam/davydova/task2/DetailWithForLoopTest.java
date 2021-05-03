package com.epam.davydova.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a class to test DetailWithForLoop class
 */
public class DetailWithForLoopTest {
    private static final String PATH_TO_FILE_WITH_DETAILS = "src/test/resources/File.txt";

    DetailWithForLoop detailWithForLoop = new DetailWithForLoop();

    @Test
    public void whenGetDetailsThenReturnMapOfDetails() {
        Map<String, List<String>> mapOfDetails = detailWithForLoop.getDetails(PATH_TO_FILE_WITH_DETAILS);

        int expectedResult = 2233;
        int actualResult = Integer.parseInt(mapOfDetails.get("cost").get(6));

        assertEquals(expectedResult, actualResult);
    }
}