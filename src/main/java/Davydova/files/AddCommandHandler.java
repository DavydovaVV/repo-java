package Davydova.files;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.Scanner;

/**
 * This is the class to add lines to File
 */
@Slf4j
public class AddCommandHandler {

    /**
     * Add line in file
     * @throws IOException if file does not exits
     */
    public void handle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        FileToRecord.FILE.setValue(scanner.nextLine());
        System.out.print("Type line number: ");
        Add.NUMBER.setNumber(scanner.nextLine());
        System.out.print("Type line: ");
        Add.LINE.setValue(scanner.nextLine());

        File file = new File(FileToRecord.FILE.getValue());
        String lineNo = Add.NUMBER.getNumber();

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
    private void addLineToEmptyFile() throws IOException {
        String fileName = FileToRecord.FILE.getValue();
        String lineNo = Add.NUMBER.getNumber();
        String string = Add.LINE.getValue();

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
    private void addLineWithNumberToEmptyFile() throws IOException {
        String fileName = FileToRecord.FILE.getValue();
        String lineNo = Add.NUMBER.getNumber();
        String string = Add.LINE.getValue();

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
    private void addLineToFilledFile() throws IOException {
        String fileName = FileToRecord.FILE.getValue();
        String string = Add.LINE.getValue();

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
    private void addFirstLineToFilledFile() throws IOException {
        String fileName = FileToRecord.FILE.getValue();
        String string = Add.LINE.getValue();

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
    private void addLineWithNumberToFilledFile() throws IOException {
        String fileName = FileToRecord.FILE.getValue();
        String lineNo = Add.NUMBER.getNumber();
        String string = Add.LINE.getValue();

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
