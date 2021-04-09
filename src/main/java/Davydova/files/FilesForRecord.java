import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.Scanner;

/**
 * This is the class to provide 2 files for recording, deleting and printing lines contained in the source file
 */
@Slf4j
public class FilesForRecord implements Serializable {

    private String fileName;
    private File file;
    private File temporaryFile = new File("Temporary.txt");
    private String number;
    private String line;

    /**
     * Getter for the name of file
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Getter for the file
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * Getter for temporary file
     * @return temporary file
     */
    public File getTemporaryFile() {
        return temporaryFile;
    }

    /**
     * Getter for line number
     * @return line number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Getter for the line
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * Request scanner to add line in file
     */
    public void requestScannerToAdd() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        fileName = scanner.nextLine();
        file = new File (fileName);
        System.out.print("Type line number: ");
        number = scanner.nextLine();
        System.out.print("Type line: ");
        line = scanner.nextLine();
    }

    /**
     * Request scanner to delete of print lines in file
     */
    public void requestScannerToDeleteOrPrint() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        fileName = scanner.nextLine();
        file = new File (fileName);
        System.out.print("Type line number: ");
        number = scanner.nextLine();
    }

    /**
     * Delete redundant file
     */
    public void deleteRedundantFile () {
        if (file.exists() && temporaryFile.exists()) {
            file.delete();
            temporaryFile.renameTo(new File("Record.txt"));
        }
    }

    /**
     * Checks if file is Empty
     * @return availability of the file
     * @throws IOException
     */
    public boolean isFileEmpty () throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine() == null;
        }
    }
}
