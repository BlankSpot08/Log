package account;

import main.Database;
import main.Main;

import static java.lang.System.out;

public class Register {
    public void newAccount() {
        User newUser = new User();
        out.println("Registering a new account.... ");

        out.print("Username: ");
        String username = Main.stringReader.nextLine();
        newUser.setUsername(username);

        out.print("Password: ");
        String password = Main.stringReader.nextLine();
        newUser.setPassword(password);

        out.print("First name: ");
        String firstName = Main.stringReader.nextLine();
        newUser.setFirstname(firstName);

        out.print("Middle name: ");
        String middleName = Main.stringReader.nextLine();
        newUser.setMiddlename(middleName);

        out.print("Last name: ");
        String lastName = Main.stringReader.nextLine();
        newUser.setLastname(lastName);

        out.print("Gender: ");
        String genderName = Main.stringReader.nextLine();
        newUser.setGender(genderName);

        out.print("Birthday: ");
        String birthDay = Main.stringReader.nextLine();
        newUser.setBirthday(birthDay);

        out.print("Email: ");
        String email = Main.stringReader.nextLine();
        newUser.setEmail(email);

        out.print("Confirm Register Y/N: ");
        char confirm = Main.stringReader.nextLine().toUpperCase().charAt(0);

        if (confirm == 'Y') {
            out.println("Registering.......");
            Database.userRegister(newUser);
            out.println("Successfully registered");
        }
        else {
            out.println("Returning.....");
        }
    }
}
