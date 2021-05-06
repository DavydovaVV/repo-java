package com.epam.davydova;

import com.epam.davydova.task1.DeadLockDemo;
import com.epam.davydova.task1.RaceConditionDemo;
import com.epam.davydova.task2.DeadLockSolution;
import com.epam.davydova.task2.RaceConditionSolution;
import com.epam.davydova.task3.Chat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        RaceConditionDemo raceConditionDemo = new RaceConditionDemo();
        raceConditionDemo.calculateMismatch();

        DeadLockDemo deadLockDemo = new DeadLockDemo();
        deadLockDemo.calculateSize();

        RaceConditionSolution raceConditionSolution = new RaceConditionSolution();
        raceConditionSolution.calculateMismatch();

        DeadLockSolution deadLockSolution = new DeadLockSolution();
        deadLockSolution.calculateSize();

        Chat chat = new Chat();
        chat.writeMessage(25);
        chat.readMessage(15);
        chat.modifyMessage(5);
    }
}
