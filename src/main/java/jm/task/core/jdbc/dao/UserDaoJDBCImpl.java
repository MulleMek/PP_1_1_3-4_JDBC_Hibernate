package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT, name varchar(50) NOT NULL, lastName varchar(50) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().execute(createTable);
            connection.commit();
        } catch (Exception ex) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception i) {
            }

        }
    }

    public void dropUsersTable() throws SQLException {
        String dropTable = "DROP TABLE IF EXISTS User";
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().execute(dropTable);
            connection.commit();
        } catch (Exception ex) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception i) {

            }


        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String saveUser = "INSERT INTO User (name, lastName, age) VALUES (?,?,?)";
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception ex) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception i) {

            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String removeUserById = "DELETE FROM User where id = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().execute(removeUserById);
            connection.commit();
        } catch (Exception ex) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception i) {

            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String getAllUsers = "SELECT id, name, lastName, age FROM User";
        Connection connection = null;

        List<User> userList = new ArrayList<>();
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            connection.commit();
        } catch (Exception ex) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception i) {

            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String cleanUsersTable = "DELETE FROM User";
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().execute(cleanUsersTable);
            connection.commit();
        } catch (Exception ex) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception i) {

            }
        }

    }

}
