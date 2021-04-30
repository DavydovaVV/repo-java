package com.epam.davydova.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a class to test DetailWithStream class
 */
public class DetailWithStreamTest {

    DetailWithStream detailWithStream = new DetailWithStream();

    @Test
    public void whenGetDetailsThenReturnMapOfDetails() {
        Map<String, List<String>> mapOfDetails = detailWithStream.getDetails("src/main/resources/File.txt");

        int expectedResult = 1306;

        String actualElementOfMap = mapOfDetails.get("weight")
                .get(8);
        int actualResult = Integer.parseInt(actualElementOfMap
                .substring(actualElementOfMap.indexOf("=") + 1));

        assertEquals(expectedResult, actualResult);
    }
}