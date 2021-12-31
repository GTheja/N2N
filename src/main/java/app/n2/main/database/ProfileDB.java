package app.n2.main.database;

import app.n2.main.account.ProfileDTO;

import java.sql.*;
import java.util.Objects;

import static app.n2.main.utils.N2NConstants.*;

public class ProfileDB {

    private final String TABLE_NAME = "profileInfo";

    public ProfileDTO lookForProfile(String profileId){
        String SQL_INSERT = String.format("SELECT * FROM %s WHERE %S=?",
                TABLE_NAME, ACCOUNT_ID);
        try(Connection connection = DriverManager.getConnection(URL_DB,USER_DB,PASSWORD_DB)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, profileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String profileId_DB = resultSet.getString(PROFILE_ID);
                String username_DB = resultSet.getString("profileName");
                String profileAvatar_DB = resultSet.getString("profileAvatar");
                String about_DB = resultSet.getString("about");
                if(Objects.equals(profileId_DB, profileId)){
                    return new ProfileDTO(profileId, username_DB, profileAvatar_DB, about_DB);
                }
            }
            throw new IllegalStateException("Code already used.");
        }catch (SQLException cause){
            cause.printStackTrace();
            throw new IllegalStateException("User with email does not exist");
        }
    }
}
