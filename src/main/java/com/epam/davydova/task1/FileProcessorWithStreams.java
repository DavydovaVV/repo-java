package com.epam.davydova.task1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This is a class to generate file of random UUID using streams
 */
@Slf4j
public class FileProcessorWithStreams {

    private final String PATH_TO_FILE_WITH_UUID = "src/main/resources/RecordStream.txt";

    /**
     * Fill up hashset by stream
     *
     * @return hashset of UUID
     */
    public HashSet<UUID> fillHashSetWithUuids() {
        HashSet<UUID> uuidHashSet = (HashSet<UUID>) Stream
                .generate(UUID::randomUUID)
                .limit(10000)
                .collect(Collectors.toSet());

        return uuidHashSet;
    }

    /**
     * Record hashset of UUIDs to file
     */
    public void recordUuidHashSetToFile() {
        try {
            Files.write(Paths.get(PATH_TO_FILE_WITH_UUID),
                    (Iterable<String>) fillHashSetWithUuids()
                            .stream()
                            .map(UUID::toString)::iterator);
        } catch (IOException e) {
            log.error("Exception is:\n", e);
        }
    }

    /**
     * Count UUIDs with some of digits greater than 100
     *
     * @return number of UUIDs
     * @throws IOException
     */
    public int countUuids() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(PATH_TO_FILE_WITH_UUID));
        long number = stream.map(this::getSumOfUuidDigits).filter(integer -> integer > 100).count();
        return (int) number;
    }

    /**
     * Get sum of digits of UUID
     *
     * @param line is a line that represents a UUID in file
     * @return sum of digits of a UUID
     */
    private int getSumOfUuidDigits(String line) {
        int sumOfDigits = 0;

        for (int i = 0; i < 36; i++) {
            int character = line.charAt(i);
            if (Character.isDigit(character)) {
                sumOfDigits += Character.getNumericValue(character);
            }
        }
        return sumOfDigits;
    }

    /**
     * Get date of Dooms Day
     *
     * @return Dooms Day in string format
     */
    public String getDateOfDoomsDay() {
        int encodedMonthsAndDays = 0;

        try {
            encodedMonthsAndDays = countUuids();
        } catch (IOException e) {
            log.error("Exception is:\n", e);
        }

        int days = encodedMonthsAndDays % 100;
        int months = encodedMonthsAndDays / 100;

        LocalDate today = LocalDate.now().plusMonths(months).plusDays(days);
        LocalTime todayTime = LocalTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(today, todayTime, ZoneId.of("America/Los_Angeles"));
        String doomsDay = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"));
        log.info("Doomsday is at {}", doomsDay);
        return doomsDay;
    }
}
