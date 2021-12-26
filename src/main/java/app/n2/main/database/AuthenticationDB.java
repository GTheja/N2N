package app.n2.main.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthenticationDB {
   private final DataSource dataSource;

    public AuthenticationDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertToDB(){
        try(Connection connection = dataSource.getConnection()) {
            String insertQuery =
            PreparedStatement preparedStatement = connection.prepareStatement();

        }catch (SQLException casue){
            casue.printStackTrace();
        }

    }
}
