package logs;

import account.User;
import database.Log;
import main.Database;
import main.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import static java.lang.System.out;
import static main.Main.showDate;

public class NewLog extends Log {

    @Override
    public void start() throws IOException {
        if (!directory.exists()) {
            createUserLogDirectory(directory);
        }

        createUserInformationDirectory(userHomePath);

        createUserLog(directory);
    }

    public void createUserLog(File directoryPath) throws IOException {
        int highestId = 0;

        for (String file : allFiles) {

            String fileName = file.substring(file.indexOf(Database.getCurrentUser()));

            int temp = Integer.parseInt(fileName.substring(1, fileName.indexOf(" ")));

            highestId = Math.max(highestId, temp);
        }

        highestId++;

        String logFileName = directoryPath.getPath() + "/#" + highestId + " " + showDate() + ".txt";

//        File newLogFile = new File(logFileName);

//        out.println("Date & Time: " + showDate());
//        out.print("Log: ");
//        String log = Main.stringReader.nextLine();
//
//        String finalLog = log.replaceAll("(.{50})", "$1\n");
//
//        FileWriter writeInLogFile = null;
//        try {
//            writeInLogFile = new FileWriter(newLogFile, true);
//
//            writeInLogFile.write(showDate() + "\n");
//            writeInLogFile.write(Database.getCurrentUser() + " Log # " + highestId + ": " + finalLog + "\nEnd Of Log");
//            out.println("The log has been registered");
//            writeInLogFile.close();
//
//        } catch (IOException e) {
//            out.println("CREATE USER LOG: " + e);
//        }

        OutputStream newLogFile = new FileOutputStream(logFileName);

        out.println("Date & Time: " + showDate());
        out.print("Log: ");

        String log = Main.stringReader.nextLine();

        String finalLog = log.replaceAll("(.{50})", "$1\n");

        newLogFile.write((showDate() + "\n").getBytes());
        newLogFile.write((Database.getCurrentUser() + " Log #" + highestId + ": " + finalLog + "\nEnd of Log").getBytes());
        out.println("\nThe log has been registered\n");

        newLogFile.close();
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

    @Override
    protected void commandLog(String fileName) {

    }

    @Override
    protected void commandAll() {

    }
}
