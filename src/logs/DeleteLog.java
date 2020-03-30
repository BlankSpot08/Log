package logs;

import java.io.File;

public class DeleteLog extends Log {
    @Override
    protected void commandLog(File file) {
        if (file.delete()) {
            System.out.println(file.getName() + " has been successfully deleted");
        }
    }
}
