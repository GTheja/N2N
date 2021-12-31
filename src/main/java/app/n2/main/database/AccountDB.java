package app.n2.main.database;

import app.n2.main.authentication.RegisterDTO;

import java.sql.*;
import java.util.Objects;

import static app.n2.main.utils.N2NConstants.*;

public class AccountDB {
    private final String TABLE_NAME = "accountInfo";
    private final String POST_ID = "post_id";

    public RegisterDTO lookForAccount(String accountId){
        String SQL_INSERT = String.format("SELECT * FROM %s WHERE %S=?",
                TABLE_NAME, ACCOUNT_ID);
        try(Connection connection = DriverManager.getConnection(URL_DB,USER_DB,PASSWORD_DB)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String isAuth = resultSet.getString(STATES);
                String accountId_DB = resultSet.getString(ACCOUNT_ID);
                if(Objects.equals(RegisterDTO.isAuthStates.AUTHENTICATED.toString(), isAuth)
                        && Objects.equals(accountId_DB, accountId)){
                    return new RegisterDTO(resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("code"));
                }
            }
            throw new IllegalStateException("Code already used.");
        }catch (SQLException cause){
            cause.printStackTrace();
            throw new IllegalStateException("User with email does not exist");
        }
    }

    public void createDB(String accountId, String email, String username, String profileId, String postId){
        String SQL_INSERT = String.format("INSERT INTO %s (%s, %s, %s, %s, %S) VALUES (?,?,?,?,?)",
                TABLE_NAME, ACCOUNT_ID, USER_EMAIL, USER_NAME, PROFILE_ID, POST_ID);
        try(Connection connection = DriverManager.getConnection(URL_DB,USER_DB,PASSWORD_DB)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, accountId);
            preparedStatement.setString(2, email);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, profileId);
            preparedStatement.setString(6, postId);
            preparedStatement.executeUpdate();
        }catch (SQLException cause){
            cause.printStackTrace();
        }
    }

}
