package logs;

import database.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ViewLog extends Log {
    @Override
    protected void commandLog(File file) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");

            System.out.println(scanner.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void commandAll() {
        try {
            for (File file : allFiles) {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("\\Z");

                System.out.println(scanner.next());
            }
            System.out.println();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
