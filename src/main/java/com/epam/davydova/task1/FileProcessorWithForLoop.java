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

    /**
     * Record hashset of UUIDs to file
     */
    public void recordUuidHashSetToFile(HashSet<UUID> uuidHashSet, String fileName) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            for (UUID uuid : uuidHashSet) {
                printWriter.println(uuid);
            }
        } catch (IOException e) {
            log.error("Exception is: ", e);
        }

        uuidHashSet.clear();
    }

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

        int months = encodedMonthsAndDays / 100;
        int days = encodedMonthsAndDays % 100;

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
     */
    private int countUuids(String fileName) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))) {
            String line;

            if (new File(fileName).length() != 0) {
                while ((line = reader.readLine()) != null) {
                    if (getSumOfUuidDigits(line) > 100) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            log.info("File is empty! Fill the file");
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
}

