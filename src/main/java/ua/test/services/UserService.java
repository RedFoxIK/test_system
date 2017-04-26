package ua.test.services;

import ua.test.dao.DaoFactory;
import ua.test.dao.impl.UserDaoImpl;
import ua.test.dao.interfaces.UserDao;
import ua.test.entity.Role;
import ua.test.entity.User;

public class UserService {
    UserDao userDao = DaoFactory.getInstance().getUserDao();

    public UserService() {}

    public int createUser(String login, String password, String name, String surname, String email ) {
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(Role.STUDENT);
        return userDao.addUser(user);
    }

    public User getUserById(int id) {
        return userDao.findById(id);
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

    public boolean changePassword(int idUser, String password) {
        return userDao.updatePassword(idUser, password);
    }

    public boolean changeEmail(Integer idUser, String email) {
        return userDao.updateEmail(idUser, email);
    }
}
