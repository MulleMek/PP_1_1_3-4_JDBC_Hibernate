package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.Array;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //SessionFactory sessionFactory = Util.getSessionFactory();
        //sessionFactory.close();

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        List<User> users = Arrays.asList(
                new User("Иван", "Иванов", (byte) 5),
                new User("Олег", "Олегов", (byte) 6),
                new User("Петр", "Петров", (byte) 7),
                new User("Николай", "Николаев", (byte) 8)
        );

        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        }

        List<User> dbUserList = userService.getAllUsers();

        for (User user : dbUserList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

        userService.connectionClose();

    }
}
