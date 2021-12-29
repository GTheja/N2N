package app.n2.main.database;

import app.n2.main.authentication.RegisterDTO;

import java.sql.*;

public class AuthenticationDB {
   private final String TABLE_NAME = "userInfo";
   private final String USER_EMAIL = "email";
   private final String USER_PASSWORD = "password";
   private final String USER_NAME = "username";
   private final String STATES = "states";
   private final String CODE ="code";



    public void createDB(String email, String username, String password, String state, String code){
        String SQL_INSERT = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?,?,?,?,?)",
                TABLE_NAME, USER_EMAIL, USER_PASSWORD, USER_NAME, STATES, CODE);
        try(Connection connection = DriverManager.getConnection("","","")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, state);
            preparedStatement.setString(5, code);
            preparedStatement.executeUpdate();
        }catch (SQLException cause){
            cause.printStackTrace();
        }
    }

    public void deleteDB(String code){
        String SQL_INSERT = String.format("DELETE FROM %s WHERE %s=?",
                TABLE_NAME, CODE);
        try(Connection connection = DriverManager.getConnection("","","")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        }catch (SQLException cause){
            cause.printStackTrace();
        }
    }


    public RegisterDTO readDB(String code){
        String SQL_INSERT = String.format("SELECT * FROM %s WHERE %S=?",
                TABLE_NAME, CODE);
        try(Connection connection = DriverManager.getConnection("","","")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String isAuth = resultSet.getString("isAuth");
                if(isAuth.contains(RegisterDTO.isAuthStates.NOTAUTHENTICATED.toString())){
                    return new RegisterDTO(resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("code"));
                }
            }
            return null;
        }catch (SQLException cause){
            cause.printStackTrace();
            throw new IllegalStateException("User with email does not exist");
        }
    }

    public RegisterDTO readWithEmail(String email){
        String SQL_INSERT = String.format("SELECT * FROM %s WHERE %S=?",
                TABLE_NAME, CODE);
        try(Connection connection = DriverManager.getConnection("","","")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String isAuth = resultSet.getString("isAuth");
                if(isAuth.contains(RegisterDTO.isAuthStates.AUTHENTICATED.toString())){
                    return new RegisterDTO(resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("code"));
                }
            }
            return null;
        }catch (SQLException cause){
            cause.printStackTrace();
            throw new IllegalStateException("User with email does not exist");
        }
    }

    public void updateDB(String email, String state){
        String SQL_INSERT = String.format("UPDATE %s SET %s=? WHERE %s=?",
                TABLE_NAME, STATES, USER_EMAIL);
        try(Connection connection = DriverManager.getConnection("","","")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, state);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }catch (SQLException cause){
            cause.printStackTrace();
        }
    }


}
