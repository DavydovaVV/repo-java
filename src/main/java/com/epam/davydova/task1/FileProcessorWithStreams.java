package com.epam.davydova.task1;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
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

    /**
     * Record hashset of UUIDs to file
     */
    public void recordUuidHashSetToFile(HashSet<UUID> uuidHashSet, String fileName) {
        try {
            Files.write(Paths.get(fileName), (Iterable<String>) uuidHashSet
                    .stream()
                    .map(UUID::toString)::iterator);
        } catch (IOException e) {
            log.error("Exception is: ", e);
        }

        uuidHashSet.clear();
    }

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
     * Get date of Dooms Day
     */
    public String getDateOfDoomsDay(String fileName) {
        File file = new File(fileName);

        int encodedMonthsAndDays = 0;
        if (file.length() != 0) {
            encodedMonthsAndDays = countUuids(fileName);
        } else {
            log.info("File is empty! Fill the file");
        }

        int days = encodedMonthsAndDays % 100;
        int months = encodedMonthsAndDays / 100;

        LocalDate localDoomsday = LocalDate.now()
                .plusMonths(months)
                .plusDays(days);
        LocalTime localDoomsdayTime = LocalTime.now();
        ZonedDateTime zonedDoomsDateTime = ZonedDateTime.of(localDoomsday, localDoomsdayTime, ZoneId.of("America/Los_Angeles"));
        String doomsDay = zonedDoomsDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"));

        log.info("Doomsday is on {}", doomsDay);

        return doomsDay;
    }

    /**
     * Count UUIDs with some of digits greater than 100
     *
     * @return number of UUIDs
     * @throws IOException
     */
    private int countUuids(String fileName) {
        long number = 0;
        try {
            number = Files.lines(Paths.get(fileName))
                    .map(this::getSumOfUuidDigits)
                    .filter(integer -> integer > 100)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
