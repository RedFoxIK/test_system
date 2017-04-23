package ua.test.dao.interfaces;

import ua.test.entity.User;

import java.util.List;

public interface UserDao {
    Integer addUser(User user);
    List<User> selectAll();
    User findByLogin(String login, String password);
    User findById(int id);
    void deleteById(int id);
    boolean isSuchEmail(String email);
    boolean isSuchLogin(String login);
    boolean updatePassword(int id, String password);
    boolean updateEmail(Integer id, String email);
}
