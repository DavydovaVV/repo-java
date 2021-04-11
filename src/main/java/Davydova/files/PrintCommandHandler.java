package Davydova.files;

import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * This is the class to print lines from File
 */
@Slf4j
public class PrintCommandHandler extends CommandHandler {

    /**
     * Print line of respective number or entire file
     * @throws IOException if file does not exist
     * @param map consist of fileName, line number fields
     */
    public void handle(TreeMap<Integer, String> map) throws IOException {

        File file = new File(map.get(0));

        if (file.length() == 0) {
            log.info("File is empty or not found: {}", file.length() == 0);
        } else {
            printLineOrFile(map);
        }
    }

    /**
     * Print line of respective number of entire file
     * @param map consist of fileName, line number fields
     */
    private void printLineOrFile(TreeMap<Integer, String> map) throws FileNotFoundException {
        File file = new File(map.get(0));
        String lineNo = map.get(1);

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
