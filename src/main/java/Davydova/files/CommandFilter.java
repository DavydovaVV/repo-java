package Davydova.files;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.EnumMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the class that accepts commands and pass them to a specific command handler
 */
@Slf4j
public class CommandFilter {

    /**
     * Handle string received from scanner to pass to a specific command handler
     * @param command string received from scanner
     */
    public void getCommand(String command) {

        try {
            Command.valueOf(Command.class, command).goToCommandHandler();
        }
        catch (IllegalArgumentException e) {
            log.error("Exception is: ", e);
        }
    }

    /**
     * This is the class to add lines to File
     */
    public class AddToFile {
        private File file = new File(FileToRecord.FILE.getValue());
        private String fileName = FileToRecord.FILE.getValue();
        private String lineNo = Add.NUMBER.getNumber();
        private String string = Add.LINE.getValue();

        /**
         * Add line in file
         * @throws IOException if file does not exits
         */
        public void addLine() throws IOException {

            if (file.length() == 0) {
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
            file.delete();
            new File ("Temporary.txt").renameTo(new File("Record.txt"));
        }

        /**
         * Add line with no number to file
         * @throws IOException if file does not exist
         */
        public void addLineToEmptyFile() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
            {
                if (lineNo.equals("")) {
                    pw.print(string);
                }
                pw.flush();
            }
        }

        /**
         * Add line of respective number to file
         * @throws IOException if file does not exist
         */
        public void addLineWithNumberToEmptyFile() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
            {
                int number = Integer.parseInt(lineNo);
                if (number < 0) {
                    log.error("Number is negative");
                } else {
                    for (int j = 0; j <= number; j++) {
                        if (j == number) {
                            pw.print(string);
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
         * @throws IOException if file does not exist
         */
        public void addLineToFilledFile() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
            {
                String line;

                while ((line = reader.readLine()) != null) {
                    pw.println(line);
                }
                pw.print(string);
                pw.flush();
            }
        }

        /**
         * Add line of respective number 0 to filled file
         * @throws IOException if file does not exist
         */
        public void addFirstLineToFilledFile() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
            {
                String line;

                pw.println(string);
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
         * @throws IOException if file does not exist
         */
        public void addLineWithNumberToFilledFile() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
            {
                int i = 0;
                String line;
                int number = Integer.parseInt(lineNo);
                if (number < 0) {
                    log.error("Number is negative");
                } else {
                    while ((line = reader.readLine()) != null) {
                        if (i == number) {
                            pw.println(string);
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
                            pw.print(string);
                            break;
                        }
                        pw.print("\n");
                    }
                    pw.flush();
                }
            }
        }
    }

    /**
     * This is the class to delete lines from File
     */
    class DeleteFromFile {
        private File file = new File(FileToRecord.FILE.getValue());
        private String fileName = FileToRecord.FILE.getValue();
        private String lineNo = DeleteAndPrint.NUMBER.getNumber();

        /**
         * Handle command to delete line from file
         * @throws IOException if file does not exist
         */
        public void deleteLine() throws IOException {

            if (file.length() == 0) {
                log.info("File is empty or not found: {}", file.length() == 0);
            } else if (lineNo.equals("")) {
                deleteLastLine();
            } else {
                deleteLineWithNumber();
            }
            file.delete();
            new File ("Temporary.txt").renameTo(new File("Record.txt"));
        }

        /**
         * Delete last line in file
         * @throws IOException if file does not exist
         */
        public void deleteLastLine() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
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
         * @throws IOException if file does not exist
         */
        public void deleteLineWithNumber() throws IOException {

            try (FileInputStream fis = new FileInputStream(fileName);
                 InputStreamReader isr = new InputStreamReader(fis);
                 BufferedReader reader = new BufferedReader(isr);
                 PrintWriter pw = new PrintWriter("Temporary.txt"))
            {
                int i = 0;
                String line;

                int number = Integer.parseInt(lineNo);
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
    }

    /**
     * This is the class to print lines from File
     */
    class PrintFile {
        private File file = new File(FileToRecord.FILE.getValue());
        private String fileName = FileToRecord.FILE.getValue();
        private String lineNo = DeleteAndPrint.NUMBER.getNumber();

        /**
         * Print line of respective number or entire file
         * @throws IOException if file does not exist
         */
        public void printLine() throws IOException {

            if (file.length() == 0) {
                log.info("File is empty or not found: {}", file.length() == 0);
            } else {
                printLineOrFile();
            }
        }

        /**
         * Print line of respective number of entire file
         */
        public void printLineOrFile() throws FileNotFoundException {

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
}

@Slf4j
enum Command {

    ADD {
        public void goToCommandHandler () {
            AddCommandHandler adder = new AddCommandHandler();
            try {
                adder.handle();
            } catch (IOException e) {
                log.error("Exception is: ", e);
            }
        }
    },

    DELETE {
        public void goToCommandHandler () {
            DeleteCommandHandler deleter = new DeleteCommandHandler();
            try {
                deleter.handle();
            } catch (IOException e) {
                log.error("Exception is: ", e);
            }
        }
    },
    PRINT {
        public void goToCommandHandler () {
            PrintCommandHandler printer = new PrintCommandHandler();
            try {
                printer.handle();
            } catch (IOException e) {
                log.error("Exception is: ", e);
            }
        }
    };

    public abstract void goToCommandHandler();
}

enum FileToRecord {
    FILE;
    private String value;

    /**
     * Getter for field value of enum File
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter for field value of enum File
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}

enum Add {
    NUMBER, LINE;
    private String value;
    private String number;

    /**
     * Getter for field value of enum Add
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for field number of enum Add
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter for field value of enum Add
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Setter for field number of enum Add
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}

enum DeleteAndPrint {
    NUMBER;
    private String number;

    /**
     * Getter for field number of enum DeleteAndPrint
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter for field number of enum DeleteAndPrint
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}