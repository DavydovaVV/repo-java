package com.epam.davydova;

import com.epam.davydova.annotations.Value;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a class to generate file with 5 sets of field values
 */
public class FileGenerator {

    private static final Logger logger = Logger.getLogger(FileGenerator.class.getName());

    /**
     * Generate file with sets of field values
     *
     * @param number is a number of sets
     */
    public static void generateFile(int number) {
        File file = new File("D:\\YandexDisk\\Repos\\src\\main\\resources\\Record.txt");

        try (Scanner scanner = new Scanner(System.in);
             PrintWriter printWriter = new PrintWriter(file)) {
            int i = 0;
            while (i < number) {
                System.out.print("Type age = ");
                String ageString = scanner.nextLine();
                int age = 0;

                try {
                    age = Integer.parseInt(ageString);
                } catch (NumberFormatException e) {
                    System.out.println("String is not parsable!");
                    age = (int) Value.class.getMethod("age").getDefaultValue();
                }

                printWriter.println("age=" + age);
                System.out.print("Type name = ");
                printWriter.println("name=" + scanner.nextLine());
                i++;
            }
        } catch (IOException | NoSuchMethodException e) {
            logger.log(Level.SEVERE, "Exception is: " + e);
        }
    }
}
