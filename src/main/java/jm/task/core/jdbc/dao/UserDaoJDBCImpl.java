package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import net.bytebuddy.description.NamedElement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT, name varchar(50) NOT NULL, lastName varchar(50) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))");

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("DROP TABLE IF EXISTS User");

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO User (name, lastName, age) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement =  connection.prepareStatement("DELETE FROM User WHERE id=?");
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        Statement statement = null;

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, lastName, age FROM User");

            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }

        return userList;

    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement("TRUNCATE TABLE User");

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
        }
    }
}
