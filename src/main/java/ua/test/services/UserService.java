package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.UserDao;
import ua.test.entity.Role;
import ua.test.entity.User;

import java.sql.Connection;
import java.util.Map;

public class UserService {
    Connection conn = DataSource.getInstance().getConnection();
    UserDao userDao = new UserDao(conn);

    public UserService() {}

    public void createUser(Map<String, String> userData) {
        User user = new User();

        user.setLogin(userData.get("login"));
        user.setPassword(userData.get("password"));
        user.setEmail(userData.get("email"));
        user.setName(userData.get("name"));
        user.setSurname(userData.get("surname"));
        user.setRole(Role.STUDENT);
        userDao.addOne(user);
    }

    public boolean hasRegisteredUser(String login, String email) {
        User user = userDao.findByLogin(login, email);
        return user != null;
    }

    public boolean isSuchEmail(String email) {
        return userDao.isSuchEmail(email);
    }

    public boolean isSuchLogin(String login) {
        return userDao.isSuchLogin(login);
    }

}
