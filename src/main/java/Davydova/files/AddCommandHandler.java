package Davydova.files;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.TreeMap;

/**
 * This is the class to add lines to File
 */
@Slf4j
public class AddCommandHandler extends CommandHandler {
    String fileName;
    String lineNo;
    String line;

    /**
     * Get Map object
     * @return map
     */
    public TreeMap<Integer, String> getMap() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(0, fileName);
        map.put(1, lineNo);
        map.put(2, line);
        return map;
    }
    /**
     * Add line in file
     * @throws IOException if file does not exits
     * @param map consist of fileName, line number and line fields
     */
    public void handle(TreeMap<Integer, String> map) throws IOException {

        File file = new File(map.get(0));
        String lineNo = map.get(1);

        if (file.length() == 0) {
            if (lineNo.equals("")) {
                addLineToEmptyFile(map);
            } else {
                addLineWithNumberToEmptyFile(map);
            }
        } else if (lineNo.equals("")) {
            addLineToFilledFile(map);
        } else if (Integer.parseInt(lineNo) == 0) {
            addFirstLineToFilledFile(map);
        } else {
            addLineWithNumberToFilledFile(map);
        }
        file.delete();
        new File ("Temporary.txt").renameTo(new File("Record.txt"));
    }

    /**
     * Add line with no number to file
     * @throws IOException if file does not exist
     * @param map consist of fileName, line number and line fields
     */
    private void addLineToEmptyFile(TreeMap<Integer, String> map) throws IOException {
        String fileName = map.get(0);
        String lineNo = map.get(1);
        String string = map.get(2);

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
     * @param map consist of fileName, line number and line fields
     */
    private void addLineWithNumberToEmptyFile(TreeMap<Integer, String> map) throws IOException {
        String fileName = map.get(0);
        String lineNo = map.get(1);
        String string = map.get(2);

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
     * @param map consist of fileName, line number and line fields
     */
    private void addLineToFilledFile(TreeMap<Integer, String> map) throws IOException {
        String fileName = map.get(0);
        String string = map.get(2);

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
     * @param map consist of fileName, line number and line fields
     */
    private void addFirstLineToFilledFile(TreeMap<Integer, String> map) throws IOException {
        String fileName = map.get(0);
        String string = map.get(2);

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
     * @param map consist of fileName, line number and line fields
     */
    private void addLineWithNumberToFilledFile(TreeMap<Integer, String> map) throws IOException {
        String fileName = map.get(0);
        String lineNo = map.get(1);
        String string = map.get(2);

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
