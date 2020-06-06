package main;

import account.Access;
import logs.DeleteLog;
import logs.NewLog;
import logs.ViewLog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    public static final Scanner inputReader = new Scanner(System.in);
    public static final Scanner stringReader = new Scanner(System.in);

    public void start() throws IOException {
        int choice;
        do {
            out.print("Welcome to my Logging Program" +
                    "\nUser: " + Database.getCurrentUser() +
                    "\n1.) Log" +
                    "\n2.) View Logs" +
                    "\n3.) Delete Logs" +
                    "\n4.) Logout" +
                    "\n5.) Exit" +
                    "\nUser Input: ");
            choice = inputReader.nextInt();

            switch (choice) {
                case 1:
                    NewLog newLog = new NewLog();
                    newLog.start();
                    break;
                case 2:
                    ViewLog viewLog = new ViewLog();
                    viewLog.start();
                    break;
                case 3:
                    DeleteLog deleteLog = new DeleteLog();
                    deleteLog.start();
                    break;
                case 4:
                    Access access = new Access();
                    access.loginOrRegister();
                    break;
                case 5:
                    out.println("Thank you for using my program");
                    System.exit(0);
                default:
                    out.println("Input is not a choice");
                    break;
            }
        } while(choice != 5);
    }

    public static String showDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy,MM,dd HH-mm-ss");
        LocalDateTime currentTime = LocalDateTime.now();

        return dateTimeFormatter.format(currentTime);
    }
}
