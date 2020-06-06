package logs;

import database.Log;

import java.io.File;

import static java.lang.System.out;

public class DeleteLog extends Log {
    @Override
    protected void commandLog(String fileName) {
        File file = new File(fileName);

        if (file.delete()) {
            out.println("The file has been deleted");
        }
    }

    @Override
    protected void commandAll() {
        for (String file : allFiles) {
            File temp = new File(file);

            temp.delete();
        }

        if (allFiles.length == 0) {
            out.println("The Files have all been deleted");
        }
    }
}
