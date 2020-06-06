package logs;

import database.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ViewLog extends Log {
    @Override
    protected void commandLog(String fileName) {
        try {
            InputStream file = new FileInputStream(fileName);

            int eachChar;

            System.out.println();
            while ((eachChar = file.read()) != -1) {
                System.out.print((char) eachChar);
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("File not found");
        }

    }

    @Override
    protected void commandAll() {
        for (String file : allFiles) {
            try {
                InputStream thisFile = new FileInputStream(file);

                int eachChar;

                System.out.println();
                while((eachChar = thisFile.read()) != -1) {
                    System.out.print((char) eachChar);
                }
                System.out.println();

                thisFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
