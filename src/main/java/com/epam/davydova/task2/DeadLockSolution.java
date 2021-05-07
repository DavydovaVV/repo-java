package com.epam.davydova.task2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is a class to resolve DeadLock
 */
@Slf4j
public class DeadLockSolution {
    private List<Integer> arrayList1 = new ArrayList<>();
    private List<Integer> arrayList2 = new ArrayList<>();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    /**
     * Calculate ArrayList size
     */
    public void calculateSize() {
        var thread1 = new Thread(() -> {
            fillArrayList(lock1, lock2);
        });

        var thread2 = new Thread(() -> {
            fillArrayList(lock2, lock1);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            log.error("Exception is: ", e);
        }

        log.info("Size of ArrayList No.1 = {}, " +
                "Size of ArrayList No.2 = {}", arrayList1.size(), arrayList2.size());
    }

    /**
     * Fill an ArrayList with 10 random elements
     */
    private void fillArrayList(Lock lock1, Lock lock2) {
        for (var i = 0; i < 10; i++) {
            takeLocks();

            try {
                arrayList1.add(new Random().nextInt(10));
                arrayList2.add(new Random().nextInt(10));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    /**
     * Lock both locks for the current thread or unlock one if another was taken
     */
    private void takeLocks() {
        var firstLockPresent = false;
        var secondLockPresent = false;

        while (true) {
            try {
                firstLockPresent = lock1.tryLock();
                secondLockPresent = lock2.tryLock();
            } finally {
                if (firstLockPresent && secondLockPresent) {
                    log.debug("All locks are locked by the current thread");
                    return;
                } else if (firstLockPresent) {
                    log.debug("[{}] is locked in DEADLOCK", lock1);
                    lock1.unlock();
                    log.debug("[{}] is unlocked to be locked by another thread as another lock has been already taken", lock1);
                    try {
                        Thread.sleep(1);
                        log.debug("Current thread is sleeping, so another thread has time to lock");
                    } catch (InterruptedException e) {
                        log.error("Exception is: ", e);
                    }
                } else if (secondLockPresent) {
                    log.debug("[{}] is locked in DEADLOCK", lock2);
                    lock2.unlock();
                    log.debug("[{}] is unlocked to be locked by another thread as another lock has been already taken", lock2);
                    try {
                        Thread.sleep(1);
                        log.debug("Current thread is sleeping, so another thread has time to lock");
                    } catch (InterruptedException e) {
                        log.error("Exception is: ", e);
                    }
                }
            }
        }
    }
}
