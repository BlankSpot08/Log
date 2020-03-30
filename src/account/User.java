package account;

import main.Database;

public class User {
    public User() {
        if (Database.getLoggedIn()) {
            setUsername(Database.getUserInfo(1));
            setPassword(Database.getUserInfo(2));
            setFirstname(Database.getUserInfo(3));
            setMiddlename(Database.getUserInfo(4));
            setLastname(Database.getUserInfo(5));
            setGender(Database.getUserInfo(6));
            setBirthday(Database.getUserInfo(7));
            setEmail(Database.getUserInfo(8));
        }
    }

    private String username;
    private String password;
    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private String birthday;
    private String email;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }
}