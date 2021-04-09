import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the class of methods to record, delete and print lines from file
 */
@Slf4j
public class LineRecorder {

    private FilesForRecord files = new FilesForRecord();

    /**
     * Get object of FilesForRecord class
     * @return object of FilesForRecord
     */
    public FilesForRecord getFiles() {
        return files;
    }

    /**
     * Handle command to add line in file
     * @throws IOException
     */
    public void addLine() throws IOException {

        files.requestScannerToAdd();
        String lineNo = files.getNumber();

        if (files.isFileEmpty()) {
            if (lineNo.equals("")) {
                addLineToEmptyFile();
            } else {
                addLineWithNumberToEmptyFile();
            }
        } else if (lineNo.equals("")) {
            addLineToFilledFile();
        } else if (Integer.parseInt(lineNo) == 0) {
            addFirstLineToFilledFile();
        } else {
            addLineWithNumberToFilledFile();
        }
        files.deleteRedundantFile();
    }

    /**
     * Handle command to delete line from file
     * @throws IOException
     */
    public void deleteLine() throws IOException {

        files.requestScannerToDeleteOrPrint();
        String lineNo = files.getNumber();

        if (files.isFileEmpty()) {
            log.info("File is empty: {}", files.isFileEmpty());
        } else if (lineNo.equals("")) {
            deleteLastLine();
        } else {
            deleteLineWithNumber();
        }
        files.deleteRedundantFile();
    }

    /**
     * Print line of respective number or entire file
     * @throws IOException
     */
    public void printLine() throws IOException {

        files.requestScannerToDeleteOrPrint();

        if (files.isFileEmpty()) {
            log.info("File is empty: {}", files.isFileEmpty());
        } else {
            printLineOrFile();
        }
    }

    /**
     * Add line with no number to file
     * @throws IOException
     */
    public void addLineToEmptyFile() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            if (files.getNumber().equals("")) {
                pw.print(files.getLine());
            }
            pw.flush();
        }
    }

    /**
     * Add line of respective number to file
     * @throws IOException
     */
    public void addLineWithNumberToEmptyFile() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            int number = Integer.parseInt(files.getNumber());
            if (number < 0) {
                log.error("Number is negative");
            } else {
                for (int j = 0; j <= number; j++) {
                    if (j == number) {
                        pw.print(files.getLine());
                        return;
                    }
                    pw.println("");
                }
                pw.flush();
            }
        }
    }

    /**
     * Add line with no number to filled file
     * @throws IOException
     */
    public void addLineToFilledFile() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            String line;

            while ((line = reader.readLine()) != null) {
                pw.println(line);
            }
            pw.print(files.getLine());
            pw.flush();
        }
    }

    /**
     * Add line of respective number 0 to filled file
     * @throws IOException
     */
    public void addFirstLineToFilledFile() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            String line;

            pw.println(files.getLine());
            if ((line = reader.readLine()).isEmpty()) {
                pw.println(reader.readLine());
            } else {
                pw.println(line);
            }
            while ((line = reader.readLine()) != null) {
                pw.println(line);
            }
            pw.flush();
        }
    }

    /**
     * Add line of respective number to filled file
     * @throws IOException
     */
    public void addLineWithNumberToFilledFile() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            int i = 0;
            String line;
            int number = Integer.parseInt(files.getNumber());
            if (number < 0) {
                log.error("Number is negative");
            } else {
                while ((line = reader.readLine()) != null) {
                    if (i == number) {
                        pw.println(files.getLine());
                        i++;
                        if (line.isEmpty()) {
                            pw.println(reader.readLine());
                            i++;
                            continue;
                        } else {
                            pw.println(line);
                            i++;
                            continue;
                        }
                    }
                    pw.println(line);
                    i++;
                }
                for (; i <= number; i++) {
                    if (i == number) {
                        pw.print(files.getLine());
                        break;
                    }
                    pw.print("\n");
                }
                pw.flush();
            }
        }
    }

    /**
     * Delete last line in file
     * @throws IOException
     */
    public void deleteLastLine() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            int i = 0;
            reader.mark(100);

            while (reader.readLine() != null) {
                i++;
            }
            reader.reset();

            for (int j = 0; j < i; j++) {
                if (j == i - 1) {
                    pw.print("");
                    break;
                }
                pw.println(reader.readLine());
            }
           pw.flush();
        }
    }

    /**
     * Delete line of respective number from file
     * @throws IOException
     */
    public void deleteLineWithNumber() throws IOException {

        try (FileInputStream fis = new FileInputStream(files.getFileName());
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr);
             PrintWriter pw = new PrintWriter(files.getTemporaryFile()))
        {
            int i = 0;
            String line;

            int number = Integer.parseInt(files.getNumber());
            if (number < 0) {
                log.error("Number is negative");
            } else {
                while ((line = reader.readLine()) != null) {
                    if (i == number) {
                        pw.print("");
                        i ++;
                        continue;
                    }
                    pw.println(line);
                    i++;
                }
                if (i - 1 < number) {
                    log.info("Line number exceeds the number of lines in file");
                }
                pw.flush();
            }
        }
    }

    /**
     * Print line of respective number of entire file
     */
    public void printLineOrFile() throws FileNotFoundException {

        try (Scanner scanner = new Scanner(files.getFile())) {

            int i = 0;
            String line;

            if (files.getNumber().equals("")) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } else {
                int number = Integer.parseInt(files.getNumber());
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
                    }
                    catch (NoSuchElementException e) {
                        log.error("Line number exceeds the number of lines in file or line is negative", e);
                    }
                }
            }
        }
    }
}
