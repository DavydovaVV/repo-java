package com.epam.davydova;

import com.epam.davydova.task1.FileProcessorWithForLoop;
import com.epam.davydova.task1.FileProcessorWithStreams;
import com.epam.davydova.task2.DetailWithForLoop;
import com.epam.davydova.task2.DetailWithStream;
import com.epam.davydova.task2.ObjectGenerator;
import com.epam.davydova.task2.Sausage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        FileProcessorWithForLoop fileProcessorWithForLoop = new FileProcessorWithForLoop();
        fileProcessorWithForLoop.getDateOfDoomsDay();

        FileProcessorWithStreams fileProcessorWithStreams = new FileProcessorWithStreams();
        fileProcessorWithStreams.getDateOfDoomsDay();

        DetailWithStream detailWithStream = new DetailWithStream();

        DetailWithForLoop detailWithForLoop = new DetailWithForLoop();

        ObjectGenerator objectGenerator = new ObjectGenerator();
        objectGenerator.generateObject(detailWithStream.getDetails(), Sausage.class);
        objectGenerator.generateObject(detailWithForLoop.getDetails(), Sausage.class);
    }
}
