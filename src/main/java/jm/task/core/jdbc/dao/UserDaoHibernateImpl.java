package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl extends UserDaoJDBCImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
