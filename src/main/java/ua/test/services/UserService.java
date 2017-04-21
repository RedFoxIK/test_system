package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.DaoFactory;
import ua.test.dao.UserDao;
import ua.test.entity.Role;
import ua.test.entity.User;

import java.sql.Connection;

public class UserService {
    Connection conn = DataSource.getInstance().getConnection();
    UserDao userDao = DaoFactory.getInstance().getUserDao(conn);

    public UserService() {}

    public int createUser(String login, String password, String name, String surname, String email ) {
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(Role.STUDENT);
        return userDao.addOne(user);
    }

    public User getByLoginAndPassword(String login, String email) {
        return userDao.findByLogin(login, email);
    }

    public boolean isSuchEmail(String email) {
        return userDao.isSuchEmail(email);
    }

    public boolean isSuchLogin(String login) {
        return userDao.isSuchLogin(login);
    }

}
