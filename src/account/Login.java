package account;

import main.Database;
import main.Main;

import static java.lang.System.out;

public class Login {
    public boolean userValid() {
        out.print("Logging in" +
                "\nUsername: ");
        String username = Main.stringReader.nextLine();
        out.print("Password: ");
        String password = Main.stringReader.nextLine();

        if (Database.userLogin(username, password)) {
            Database.setCurrentUser(username);
            Database.setLoggedIn(true);
            return true;
        }

        return false;
    }
}
