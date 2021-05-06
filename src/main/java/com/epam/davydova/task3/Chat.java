package com.epam.davydova.task3;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * This is a class that represents work of chat
 */
@Slf4j
public class Chat {
    private BlockingQueue<String> smsQueue = new ArrayBlockingQueue<>(25);

    /**
     * Write a message to chat
     *
     * @param numberOfThreads is a number of threads that write messages
     */
    public void writeMessage(int numberOfThreads) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.scheduleAtFixedRate(() -> {
                String message = "id:" + new Random().nextInt(100);

                try {
                    smsQueue.put(message);
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                if (smsQueue.size() == 25) {
                    log.debug("Chat capacity is full");
                }

                log.info("Thread [{}], has written the message \"[{}]\" to the Chat. " +
                                "Current capacity of the Chat is [{}]",
                        Thread.currentThread().getId(), message, smsQueue.size());

            }, 0, 20 + new Random().nextInt(39), TimeUnit.SECONDS);
        }
    }

    /**
     * Read a message from chat
     *
     * @param numberOfThreads is a number of threads that read messages from chat
     */
    public void readMessage(int numberOfThreads) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.scheduleAtFixedRate(() -> {
                String message = "";

                try {
                    message = smsQueue.take();
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                log.info("Thread [{}], has read the message \"[{}]\" from the Chat. " +
                                "Current capacity of the Chat is [{}]",
                        Thread.currentThread().getId(), message, smsQueue.size());
            }, 0, 30 + new Random().nextInt(19), TimeUnit.SECONDS);
        }
    }

    /**
     * Modify and return message to chat
     *
     * @param numberOfThreads is a number of threads that modify messages
     */
    public void modifyMessage(int numberOfThreads) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.scheduleAtFixedRate(() -> {
                String message = "";
                String modifiedMessage = "";

                try {
                    message = smsQueue.take();
                    modifiedMessage = message + new Random().nextInt(10);
                    smsQueue.put(modifiedMessage);
                } catch (InterruptedException e) {
                    log.error("Exception is: ", e);
                }

                if (smsQueue.size() == 25) {
                    log.debug("Chat capacity is full");
                }

                log.info("Thread [{}], has modified the message \"[{}]\" from Chat. " +
                                "Modified message is \"[{}]\". Current capacity of the Chat is [{}]",
                        Thread.currentThread().getId(), message, modifiedMessage, smsQueue.size());
            }, 0, 65, TimeUnit.SECONDS);
        }
    }
}
