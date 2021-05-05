package com.epam.davydova.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is a class to demonstrate Race condition
 */
@Slf4j
public class RaceConditionDemo {
    private final ArrayList<Integer> arrayList = new ArrayList<>();

    /**
     * Calculate mismatch between actual and expected ArrayList size
     */
    public void calculateMismatch() {
        Thread thread1 = new Thread(this::fillArrayList);
        Thread thread2 = new Thread(this::fillArrayList);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            log.error("Exception is: ", e);
        }

        log.info("ArrayList size mismatch after filling up with 2000 elements " +
                "using 2 asynchronous threads = {}", 2000 - arrayList.size());
    }

    /**
     * Fill an ArrayList with 1000 random elements
     */
    private void fillArrayList() {
        for (int i = 0; i < 1000; i++) {
            arrayList.add(new Random().nextInt(10));
        }
    }
}
