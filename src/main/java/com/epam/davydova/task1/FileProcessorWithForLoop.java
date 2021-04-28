package com.epam.davydova.task1;

import com.epam.davydova.task1.interfaces.HashSetUuidFiller;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.UUID;

/**
 * This is a class to generate file of random UUID using for-loops
 */
@Slf4j
public class FileProcessorWithForLoop implements HashSetUuidFiller {

    private final String PATH_TO_FILE_WITH_UUID = "src/main/resources/RecordForLoop.txt";

    /**
     * Fill hashset of UUIDs
     *
     * @return hashset UUIDs
     */
    @Override
    public HashSet<UUID> fillHashSetWithUuids() {
        for (int i = 0; i < 10000; i++) {
            uuidHashSet.add(UUID.randomUUID());
        }
        log.debug("Hashset of UUID is filled up");
        return uuidHashSet;
    }

    /**
     * Record hashset of UUIDs to file
     */
    public void recordUuidHashSetToFile() {
        try (PrintWriter printWriter = new PrintWriter(PATH_TO_FILE_WITH_UUID)) {
            for (UUID uuid : fillHashSetWithUuids()) {
                printWriter.println(uuid);
            }
        } catch (IOException e) {
            log.error("Exception is:\n", e);
        }
    }

    /**
     * Count UUIDs with some of digits greater than 100
     *
     * @return number of UUIDs
     */
    private int countUuids() {
        int count = 0;
        recordUuidHashSetToFile();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(PATH_TO_FILE_WITH_UUID)))) {
            String line;

            if (new File(PATH_TO_FILE_WITH_UUID).length() != 0) {
                while ((line = reader.readLine()) != null) {
                    if (getSumOfUuidDigits(line) > 100) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            log.error("Exception is:\n", e);
        }

        return count;
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
        int encodedMonthsAndDays = countUuids();

        int months = encodedMonthsAndDays / 100;
        int days = encodedMonthsAndDays % 100;

        LocalDate today = LocalDate.now().plusMonths(months).plusDays(days);
        LocalTime todayTime = LocalTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(today, todayTime, ZoneId.of("America/Los_Angeles"));
        String doomsDay = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"));
        log.info("Doomsday is at {}", doomsDay);
        return doomsDay;
    }
}

