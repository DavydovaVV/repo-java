package com.epam.davydova.task1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Stream;

import static com.epam.davydova.task1.interfaces.HashSetUuidFiller.uuidHashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a class to test FileProcessorWithForLoop class
 */
@Slf4j
public class FileProcessorWithForLoopTest {

    private static final String PATH_TO_FILE_WITH_UUID = "src/test/resources/RecordForTests.txt";

    FileProcessorWithForLoop processorWithForLoop = new FileProcessorWithForLoop();

    @Test
    public void whenFillHashSetWithUuidsThenReturnUuidHashSet() {
        processorWithForLoop.fillHashSetWithUuids();

        int expectedResult = 10000;
        int actualResult = uuidHashSet.size();

        assertEquals(expectedResult, actualResult);

        uuidHashSet.clear();
    }

    @Test
    public void recordUuidHashSetToFileTest() {
        uuidHashSet.add(UUID.randomUUID());
        uuidHashSet.add(UUID.randomUUID());
        int expectedResult = 2;

        processorWithForLoop.recordUuidHashSetToFile(uuidHashSet, "TestFile.txt");

        try (Stream<String> fileStream = Files.lines(Paths.get("TestFile.txt"))) {
            int actualResult = (int) fileStream.count();

            assertEquals(expectedResult, actualResult);
        } catch (IOException e) {
            log.error("Exception is: ", e);
        }

        uuidHashSet.clear();

        new File("TestFile.txt").delete();
    }

    @Test
    public void whenGetDoomsdayThenReturnDoomsday() {
        LocalTime localDoomsdayTime = LocalTime.now();
        ZonedDateTime zonedDoomsDateTime = ZonedDateTime.of(LocalDate.parse("2024-02-11"),
                localDoomsdayTime, ZoneId.of("America/Los_Angeles"));

        String expectedResult = zonedDoomsDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"));
        String actualResult = processorWithForLoop.getDateOfDoomsDay(PATH_TO_FILE_WITH_UUID);

        assertEquals(expectedResult, actualResult);
    }
}