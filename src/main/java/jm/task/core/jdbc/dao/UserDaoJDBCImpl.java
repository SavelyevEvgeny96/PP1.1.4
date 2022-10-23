package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS USER " +
                    "(ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name varchar(40) NOT NULL, " +
                    "lastname varchar(40) NOT NULL, age TINYINT)";
            statement.execute(sql);


        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String sql = " DROP TABLE IF EXISTS user";
            statement.execute(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USER(name,lastname,age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void removeUserById(long id)  {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER WHERE ID=?")) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }





    @Override
    public List<User> getAllUsers() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> resultUsers = new ArrayList<>();
            while (resultSet.next()) {
                resultUsers.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age")));

            }
            return resultUsers;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void cleanUsersTable()  {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER")) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


}