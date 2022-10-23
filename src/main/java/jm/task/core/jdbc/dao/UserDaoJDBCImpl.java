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

        String sql = "CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT, name varchar(50) NOT NULL, lastName varchar(50) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";

        try{
            statement = connection.prepareStatement(sql);

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

        String sql = "DROP TABLE IF EXISTS User";

        try{
            statement = connection.prepareStatement(sql);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO User (name, lastName, age) VALUES (?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
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

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM User WHERE id=?";

        try{
            preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        Statement statement = null;
        String sql = "SELECT name, lastName, age FROM User";

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;

    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement statement = null;

        String sql = "TRUNCATE TABLE User";

        try{
            statement = connection.prepareStatement(sql);

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
