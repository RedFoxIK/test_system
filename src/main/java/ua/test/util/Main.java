package ua.test.util;

import ua.test.connection.DataSource;
import ua.test.dao.UserDao;
import ua.test.entity.User;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DataSource.getInstance().getConnection();
        UserDao userDao = (new UserDao(conn));
        String login = "tutor";
        String password = "tutor";
        User user = userDao.findByLogin(login, password);
        System.out.println(user);
    }
}
