package sample.server;

import java.sql.*;

public class Database {

    public Connection connection = null;
    public Statement statement = null;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pacman","root", "root");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public String CheckPassword(String userName, String password) {
        String answer = "not found";
        String selectTableSQL = "SELECT UserName, Password FROM users WHERE UserName = '" + userName + "';";
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(selectTableSQL);
            if (result.next()) {
                if (result.getString("Password").equals(password))
                    answer = "ok";
                else
                    answer = "incorrect";
            }
            statement.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); e.printStackTrace(); }
        return answer;
    }

    public void InsertNewUser(String userName, String password) {
        String insertTableSQL = "INSERT INTO users"
                + "(UserName, Password, MaxScore1, MaxScore2, MaxScore3, MaxScore4) "
                + "VALUES ('"+userName+"','"+password+"',0,0,0,0)";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertTableSQL);
            statement.close();
        } catch (SQLException e) { System.out.println(e.getMessage()); e.printStackTrace(); }
    }

}
