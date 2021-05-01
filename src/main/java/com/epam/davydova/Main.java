package com.epam.davydova;

import com.epam.davydova.task1.FileProcessorWithForLoop;
import com.epam.davydova.task1.FileProcessorWithStreams;
import com.epam.davydova.task2.DetailWithForLoop;
import com.epam.davydova.task2.DetailWithStream;
import com.epam.davydova.task2.ObjectGenerator;
import com.epam.davydova.task2.Sausage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        FileProcessorWithForLoop fileProcessorWithForLoop = new FileProcessorWithForLoop();
        fileProcessorWithForLoop.recordUuidHashSetToFile(
                fileProcessorWithForLoop.fillHashSetWithUuids(), "src/main/resources/Record.txt");
        fileProcessorWithForLoop.getDateOfDoomsDay("src/main/resources/Record.txt");

        FileProcessorWithStreams fileProcessorWithStreams = new FileProcessorWithStreams();
        fileProcessorWithStreams.getDateOfDoomsDay("src/main/resources/Record.txt");

        DetailWithStream detailWithStream = new DetailWithStream();

        DetailWithForLoop detailWithForLoop = new DetailWithForLoop();

        ObjectGenerator objectGenerator = new ObjectGenerator();
        objectGenerator.generateObject(detailWithStream.getDetails("src/main/resources/File.txt"), Sausage.class);
        objectGenerator.generateObject(detailWithForLoop.getDetails("src/main/resources/File.txt"), Sausage.class);
    }
}
