package main;

import account.Access;

import java.io.IOException;

public class EntryPoint {
    public static void main(String... args) throws IOException {
        Access access = new Access();
        access.loginOrRegister();
    }
}