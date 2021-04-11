package Davydova.files;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the class to print lines from File
 */
@Slf4j
public class PrintCommandHandler {

    /**
     * Print line of respective number or entire file
     * @throws IOException if file does not exist
     */
    public void handle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        FileToRecord.FILE.setValue(scanner.nextLine());
        System.out.print("Type line number: ");
        DeleteAndPrint.NUMBER.setNumber(scanner.nextLine());

        File file = new File(FileToRecord.FILE.getValue());

        if (file.length() == 0) {
            log.info("File is empty or not found: {}", file.length() == 0);
        } else {
            printLineOrFile();
        }
    }

    /**
     * Print line of respective number of entire file
     */
    private void printLineOrFile() throws FileNotFoundException {
        File file = new File(FileToRecord.FILE.getValue());
        String lineNo = DeleteAndPrint.NUMBER.getNumber();

        try (Scanner scanner = new Scanner(file)) {

            int i = 0;
            String line;

            if (lineNo.equals("")) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } else {
                int number = Integer.parseInt(lineNo);
                if (number < 0) {
                    log.error("Number is negative");
                } else {
                    try {
                        while ((line = scanner.nextLine()) != null) {
                            if (i == number) {
                                System.out.println(line);
                                return;
                            }
                            i++;
                        }
                    } catch (NoSuchElementException e) {
                        log.error("Line number exceeds the number of lines in file or line is negative", e);
                    }
                }
            }
        }
    }
}
