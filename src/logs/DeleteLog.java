package logs;

import database.Log;

import java.io.File;

import static java.lang.System.out;

public class DeleteLog extends Log {
    @Override
    protected void commandLog(File file) {
        if (file.delete()) {
            out.println(file.getName() + " has been successfully deleted");
        }
    }

    @Override
    protected void commandAll() {
//        for () {
//
//
//        }
    }
}
