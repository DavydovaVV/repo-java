package com.epam.davydova.task3;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class Chat {
    private BlockingQueue<String> smsQueue = new ArrayBlockingQueue<>(25);

    public void writeMessage(int numberOfThreads) {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        service.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(TimeUnit
                            .SECONDS
                            .toMillis(20 + new Random().nextInt(39)));
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                String message = "id:" + new Random().nextInt(100);
                smsQueue.put(message);

                if (smsQueue.size() == 25) {
                    log.debug("Chat capacity is full");
                }

                log.info("Thread [{}], has written the message \"[{}]\" to the Chat. " +
                                "Current capacity of the Chat is [{}]",
                        Thread.currentThread().getId(), message, smsQueue.size());
            }
        });
    }

    public void readMessage(int numberOfThreads) {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        service.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(TimeUnit
                            .SECONDS
                            .toMillis(30 + new Random().nextInt(19)));
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                String message = smsQueue.take();

                log.info("Thread [{}], has read the message \"[{}]\" from the Chat. " +
                                "Current capacity of the Chat is [{}]",
                        Thread.currentThread().getId(), message, smsQueue.size());
            }
        });
    }

    public void modifyMessage(int numberOfThreads) {
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        service.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(80_000);
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                String message = smsQueue.take();
                String modifiedMessage = message + new Random().nextInt(10);

                try {
                    smsQueue.put(message);
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                log.info("Thread [{}], has modified the message \"[{}]\" from Chat. " +
                                "Modified message is \"[{}]\". Current capacity of the Chat is [{}]",
                        Thread.currentThread().getId(), message, modifiedMessage, smsQueue.size());
            }
        });
    }
}
