package ua.test.dao.impl;

import org.apache.log4j.Logger;
import ua.test.dao.interfaces.UserDao;
import ua.test.entity.Role;
import ua.test.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String ADD_USER = "INSERT INTO users(`login`, `password`, `name`, `surname`, `email`, `role`) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT `id_user`, `login`, `password`, `name`, `surname`, `email`, `role` FROM users WHERE `id_user` = ?";
    private static final String SELECT_BY_LOGIN = "SELECT `id_user`, `login`, `password`, `name`, `surname`, `email`, `role` FROM users WHERE `login` = ? AND `password` = ?";
    private static final String SELECT_ALL = "SELECT `id_user`, `login`, `password`, `name`, `surname`, `email`, `role` FROM users";
    private static final String CHECK_UNIQUE_LOGIN = "SELECT `login` FROM users WHERE login = ?";
    private static final String CHECK_UNIQUE_EMAIL = "SELECT `email` FROM users WHERE email = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users set `password` = ? WHERE `id_user` = ?";
    private static final String UPDATE_EMAIL = "UPDATE users set `email` = ? WHERE `id_user` = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE `id_user` = ?";

    private static final String DB_CON_ERROR = "Database connection error";

    Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer addUser(User user) {
        Integer idGenerated = null;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().name());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
            rs.close();
        } catch ( SQLException e ) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return idGenerated;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();

        try ( Statement statement = conn.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {
            while ( rs.next() ) {
                users.add(getUser(rs));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return users;
    }

    @Override
    public User findByLogin(String login, String password) {
        User user = null;
        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_LOGIN) ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                user = getUser(rs);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                user = getUser(rs);
            }
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return user;
    }

    @Override
    public void deleteById(int id) {
        try ( PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
        }
    }

    @Override
    public boolean isSuchEmail(String email) {
        return isSuchRecord(email, CHECK_UNIQUE_EMAIL);
    }

    @Override
    public boolean isSuchLogin(String login) {
        return isSuchRecord(login, CHECK_UNIQUE_LOGIN);
    }

    @Override
    public boolean updatePassword(int id, String password) {
        return updateUser(id, password, UPDATE_PASSWORD);
    }

    @Override
    public boolean updateEmail(Integer id, String email) {
       return updateUser(id, email, UPDATE_EMAIL);
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id_user"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }

    private boolean updateUser(int id, String key, String sql) {
        try ( PreparedStatement statement = conn.prepareStatement(UPDATE_PASSWORD) ) {
            int result;

            statement.setString(1, key);
            statement.setInt(2, id);
            result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
        }
        return false;
    }

    private boolean isSuchRecord(String key, String sql) {
        boolean result = false;

        try ( PreparedStatement statement = conn.prepareStatement(sql) ) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            result = rs.next();
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
        }
        return result;
    }
}