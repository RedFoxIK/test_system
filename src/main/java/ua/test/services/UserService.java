package ua.test.services;

import ua.test.dao.DaoFactory;
import ua.test.dao.impl.UserDaoImpl;
import ua.test.dao.interfaces.UserDao;
import ua.test.entity.Role;
import ua.test.entity.User;

public class UserService {
    UserService() {}

    public int createUser(String login, String password, String name, String surname, String email ) {
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(Role.STUDENT);
        return DaoFactory.getInstance().getUserDao().addUser(user);
    }

    public User getUserById(int id) {
        return DaoFactory.getInstance().getUserDao().findById(id);
    }

    public User getByLoginAndPassword(String login, String email) {
        return DaoFactory.getInstance().getUserDao().findByLogin(login, email);
    }

    public boolean isSuchEmail(String email) {
        return DaoFactory.getInstance().getUserDao().isSuchEmail(email);
    }

    public boolean isSuchLogin(String login) {
        return DaoFactory.getInstance().getUserDao().isSuchLogin(login);
    }

    public boolean changePassword(int idUser, String password) {
        return DaoFactory.getInstance().getUserDao().updatePassword(idUser, password);
    }

    public boolean changeEmail(Integer idUser, String email) {
        return DaoFactory.getInstance().getUserDao().updateEmail(idUser, email);
    }
}
