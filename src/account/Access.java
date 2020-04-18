package account;

import main.Database;
import main.Main;

import static java.lang.System.out;

public class Access {
    public Access() {
        Database.setCurrentUser("");
        Database.setLoggedIn(false);
    }

    public void loginOrRegister() {
        int choice;
        do {
            out.print("Welcome to my Log Program" +
                    "\n1.) Login" +
                    "\n2.) Register" +
                    "\n3.) Exit" +
                    "\nUser Input: ");
            choice = Main.inputReader.nextInt();

            switch (choice) {
                case 1:
                    Login login = new Login();

                    if (login.userValid()) {
                        out.println("Login Successfully");
                        choice = 3;

                        Main main = new Main();
                        main.start();
                    }
                    else {
                        out.println("Login Failed");
                    }
                    break;
                case 2:
                    Register register = new Register();

                    register.newAccount();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }
}
