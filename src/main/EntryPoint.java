package main;

import account.Access;

public class EntryPoint {
    public static void main(String... args) {
        Access access = new Access();
        access.loginOrRegister();
    }
}