package Davydova.files;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.Scanner;

/**
 * This is the class to delete lines from File
 */
@Slf4j
public class DeleteCommandHandler {

    /**
     * Handle command to delete line from file
     * @throws IOException if file does not exist
     */
    public void handle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        FileToRecord.FILE.setValue(scanner.nextLine());
        System.out.print("Type line number: ");
        DeleteAndPrint.NUMBER.setNumber(scanner.nextLine());

        File file = new File(FileToRecord.FILE.getValue());
        String lineNo = DeleteAndPrint.NUMBER.getNumber();

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
    private void deleteLastLine() throws IOException {
        String fileName = FileToRecord.FILE.getValue();

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
    private void deleteLineWithNumber() throws IOException {
        String fileName = FileToRecord.FILE.getValue();
        String lineNo = DeleteAndPrint.NUMBER.getNumber();

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
