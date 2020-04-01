package logs;

import main.Database;
import main.Main;

import static java.lang.System.out;

import java.io.File;

public abstract class Log {
    public Log() {
        userHome = "user.home";
        userHomePath = System.getProperty(userHome);

        directoryPath = userHomePath + "/Documents/Logs/" + Database.getCurrentUser() + "/";
        directory = new File(directoryPath);
    }

    protected abstract void commandLog(File file);

    protected final String userHome;
    protected final String userHomePath;

    protected final String directoryPath;
    protected final File directory;

    protected final void showBoard(File[] allFiles) {
        out.println("\t\t\t\t\t\t\tLogs of " + Database.getCurrentUser());
        for (int i = 1; i < allFiles.length; i++) {
            String output = i % 4 == 0 ? "\n" : allFiles[i - 1].getName().substring(0, allFiles[i - 1].getName().length() - 4) + "\t";

            out.print(output);
        }
        out.println();
    }

    protected final File findLog(File[] allFiles, int pickANumber) {
        for (int i = 0; i < allFiles.length - 1; i++) {
            String id = allFiles[i].getName().
                    substring(allFiles[i].getName().length() - (String.valueOf(i).length() + 5),
                            allFiles[i].getName().length() - 4);

            String idNumberToFind = "#" + pickANumber;

            if (id.equals(idNumberToFind)) {
                return allFiles[i];
            }
        }

        return allFiles[0];
    }

    public void start() {
        int choice;
        File[] allFiles = directory.listFiles();

        if (directory.exists() && allFiles.length != 1) {
            do {
                showBoard(allFiles);
                out.print("1.) Select" +
                        "\n2.) Back" +
                        "\nUser Input");
                choice = Main.inputReader.nextInt();

                switch (choice) {
                    case 1:
                        showBoard(allFiles);
                        out.print("User Input (#3 = 3): ");
                        int pickANumber = Main.inputReader.nextInt();

                        File tempFile = findLog(allFiles, pickANumber);

                        commandLog(tempFile);

                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            } while (choice != 2 && choice != 1);
        }

        else {
            out.println("The log directory is empty");
        }
    }
}