package pl.zwa.testprojekt.models.dao.impl;

import pl.zwa.testprojekt.models.MysqlConnector;
import pl.zwa.testprojekt.models.dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private MysqlConnector connector = MysqlConnector.getInstance();

    @Override
    public boolean login(String name, String password) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT * FROM user WHERE username = ?"
            );
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return false;
            }

            return (resultSet.getString("password").equals(password));


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;

    }

    @Override
    public boolean register(String name, String password) {
        try {
            //sprawdzenie czy nie istnieje user o takim samym loginie
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "SELECT * FROM user WHERE username = ?"
            );
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){  //zmiana z !resultset na resultset
                return true;  //zmiana z false na true
            }

            PreparedStatement preparedStatementInsert = connector.getConnection().prepareStatement(
                    "INSERT INTO user VALUES(?, ?, ?)"
            );
            preparedStatementInsert.setInt(1,0);
            preparedStatementInsert.setString(2, name);
            preparedStatementInsert.setString(3, password);

            preparedStatement.execute();
            preparedStatement.close();
            preparedStatementInsert.execute();
            preparedStatementInsert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void removeUser(int id) {
        try {
            PreparedStatement preparedStatement = connector.getConnection().prepareStatement(
                    "DELETE FROM user WHERE id = ?"
                );
            preparedStatement.setInt(1, 0);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
