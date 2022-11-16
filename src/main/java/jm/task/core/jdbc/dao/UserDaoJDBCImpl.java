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
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User (id INT NOT NULL AUTO_INCREMENT, name varchar(50) NOT NULL, lastName varchar(50) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))");
            ps.executeUpdate();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception i) {
            }

        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement("DROP TABLE IF EXISTS User");
            ps.executeUpdate();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception i) {

            }


        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO User (name, lastName, age) VALUES (?,?,?)");
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception i) {

            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM User where id = ?");
            preparedStatement.setLong(1, id);
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception i) {

            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        List<User> userList = new ArrayList<>();
        try {
            connection = getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM User");
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
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception i) {

            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            ps = connection.prepareStatement("TRUNCATE TABLE User;");
            ps.executeUpdate();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception i) {

            }
        }

    }

}
