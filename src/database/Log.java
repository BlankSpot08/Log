package database;

import main.Database;
import main.Main;

import static java.lang.System.out;

import java.io.File;
import java.io.FilenameFilter;

public abstract class Log {

    protected abstract void commandLog(File file);
    protected abstract void commandAll();

    protected final String userHome;
    protected final String userHomePath;

    protected final String directoryPath;
    protected final File directory;

    protected final File[] allFiles;

    public Log() {
        userHome = "user.home";
        userHomePath = System.getProperty(userHome);

        directoryPath = userHomePath + "/Documents/Logs/" + Database.getCurrentUser() + "/";
        directory = new File(directoryPath);

        allFiles = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        // Lambda mode
//        allFiles = directory.listFiles((dir, name) -> name.endsWith(".txt"));
    }

    protected final void showBoard(File[] allFiles) {
        out.println("\t\t\t\t\t\t\tLogs of " + Database.getCurrentUser());
        for (int i = 0; i < allFiles.length; i++) {
            String fileName = allFiles[i].getName().substring(0, allFiles[i].getName().indexOf(".")) + "\t";
            String line = i % 3 == 0 && i != 0 ? "\n" + fileName : fileName;
            out.print(line);
        }

        out.println();
    }

    protected final File findLog(File[] allFiles, int pickANumber) {
        for (File allFile : allFiles) {
            if (Integer.parseInt(allFile.getName().substring(1, allFile.getName().indexOf(" "))) == pickANumber) {
                return allFile;
            }
        }

        return null;
    }

    public void start() {
        int choice;

        if (directory.exists() && allFiles.length != 0) {
            do {
                showBoard(allFiles);
                 out.print("1.) Select" +
                        "\n2.) All" +
                        "\n3.) Back" +
                        "\nUser Input: ");
                choice = Main.inputReader.nextInt();

                switch (choice) {
                    case 1:
                        showBoard(allFiles);
                        out.print("User Input (#3 = 3): ");
                        int pickANumber = Main.inputReader.nextInt();

                        File tempFile = findLog(allFiles, pickANumber);

                        out.println(tempFile.getName());

                        commandLog(tempFile);

                        break;
                    case 2:
                        commandAll();
                        break;
                    default:
                        break;
                }
            } while (choice != 3);
        }

        else {
            out.println("The log directory is empty");
        }
    }
}