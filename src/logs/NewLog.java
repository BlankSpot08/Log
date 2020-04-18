package logs;

import account.User;
import database.Log;
import main.Database;
import main.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.out;
import static main.Main.showDate;

public class NewLog extends Log {

    @Override
    public void start() {
        if (!directory.exists()) {
            createUserLogDirectory(directory);
        }

        createUserInformationDirectory(userHomePath);

        createUserLog(directory);
    }

    public void createUserLog(File directoryPath) {
        int directoryNumOfFiles = getTheNumberOfFiles(directoryPath);

        String logFileName = directoryPath.getPath() + "/" + showDate() + " #" + directoryNumOfFiles + ".txt";

        File newLogFile = new File(logFileName);

        out.println("Date & Time: " + showDate());
        out.print("Log: ");
        String log = Main.stringReader.nextLine();

        String finalLog = log.replaceAll("(.{50})", "$1\n");

        FileWriter writeInLogFile = null;
        try {
            writeInLogFile = new FileWriter(newLogFile, true);

            writeInLogFile.write(showDate() + "\n");
            writeInLogFile.write(Database.getCurrentUser()+ " Log # " + directoryNumOfFiles + ": " + finalLog + "\nEnd Of Log");
            out.println("The log has been registered");
            writeInLogFile.close();

        } catch (IOException e) {
            out.println("CREATE USER LOG: " + e);
        }
    }

    public void createUserInformationDirectory(String path) {
        String userInfoDirectoryPath = path + "/Documents/Logs/" + Database.getCurrentUser() + "/User Information/";
        File userInfoDirectory = new File(userInfoDirectoryPath);

        if (!userInfoDirectory.exists()) {
            out.println("The user information directory does not exists");
            userInfoDirectory.mkdir();
            out.println("The user information has been created");

            String userInfoName = userInfoDirectoryPath + "User Information.txt";
            File userInfo = new File(userInfoName);

            FileWriter writeUserInfo = null;

            try {
                User user = new User();
                writeUserInfo = new FileWriter(userInfo, true);

                writeUserInfo.write("Information of user - " + Database.getCurrentUser());
                writeUserInfo.write("\nUsername: " + user.getUsername());
                writeUserInfo.write("\nPassword: " + user.getPassword());
                writeUserInfo.write("\nFirst Name: " + user.getFirstname());
                writeUserInfo.write("\nMiddle Name: " + user.getMiddlename());
                writeUserInfo.write("\nLast Name: " + user.getLastname());
                writeUserInfo.write("\nGender: " + user.getGender());
                writeUserInfo.write("\nBirthday: " + user.getBirthday());
                writeUserInfo.write("\nEmail: " + user.getEmail());

                out.println("The log has been registered");
                writeUserInfo.close();
            }
            catch (IOException e) {
                out.println("INFORMATION DIRECTORY: " + e);
            }
        }
    }

    public void createUserLogDirectory(File directory) {
        out.println("The user log directory does not exists");
        out.println("The user log directory is being created.....");
        directory.mkdir();

        out.println("The user log directory have been made");
    }

    private int getTheNumberOfFiles(File directoryPath) {
        return directoryPath.list().length == 0 ? 1 : directoryPath.listFiles().length;
    }

    @Override
    protected void commandLog(File file) {
    }
}
