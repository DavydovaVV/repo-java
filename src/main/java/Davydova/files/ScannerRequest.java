package Davydova.files;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * This is the class to request Scanner
 */
@Slf4j
public class ScannerRequest {

    /**
     * Request scanner to add line in file
     */
    public static void requestScannerToAdd() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        FileToRecord.FILE.setValue(scanner.nextLine());
        System.out.print("Type line number: ");
        Add.NUMBER.setNumber(scanner.nextLine());
        System.out.print("Type line: ");
        Add.LINE.setValue(scanner.nextLine());
    }

    /**
     * Request scanner to delete line or print line(s) from file
     */
    public static void requestScannerToDeleteOrPrint() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type file name: ");
        FileToRecord.FILE.setValue(scanner.nextLine());
        System.out.print("Type line number: ");
        DeleteAndPrint.NUMBER.setNumber(scanner.nextLine());
    }
}
