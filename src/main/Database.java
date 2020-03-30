package main;

import account.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Database {
    private static final String databaseURL = "jdbc:postgresql://localhost:5432/log_database";
    private static final String databaseUsername = "postgres";
    private static final String databasePassword = "chupentot0205";

    private static String currentUser;
    private static boolean loggedIn;

    public static void setLoggedIn(boolean userLoggedIn) {
        loggedIn = userLoggedIn;
    }

    public static boolean getLoggedIn() {
        return loggedIn;
    }

    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static String getUserInfo(int i) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM log_users WHERE user_name = '" + getCurrentUser() + "'";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                return resultSet.getString(i + 1);
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "-1";
    }

    public static boolean userLogin(String user, String pass) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM log_users");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if ((user.equals(resultSet.getString("user_name")))
                    && (pass.equals(resultSet.getString("pass_word")))) {
                    preparedStatement.close();
                    connection.close();
                    resultSet.close();
                    return true;
                }
            }
        }

        catch (Exception e) {
            out.println(e);
        }

        return false;
    }

    public static void userRegister(User user) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        String query = "INSERT INTO log_users(user_name, pass_word, first_name, middle_name, last_name, gender, birthday, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getMiddlename());
            preparedStatement.setString(5, user.getLastname());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getBirthday());
            preparedStatement.setString(8, user.getEmail());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
    }
}