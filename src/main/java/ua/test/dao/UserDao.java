package ua.test.dao;

import ua.test.entity.Role;
import ua.test.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String ADD_ONE = "INSERT INTO users(`login`, `password`, `name`, `surname`, `email`, `role`) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT id_user, login, password, name, surname, email, ole FROM users";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id_user = ?";
    private static final String FIND_BY_ID = "SELECT id_user, login, password, name, surname, email, role FROM users WHERE id_user = ?";
    private static final String FIND_BY_LOGIN = "SELECT `id_user`, `login`, `password`, `name`, `surname`, `email`, `role` FROM users WHERE login = ? AND password = ?";
    private static final String CHECK_UNIQUE_LOGIN = "SELECT `login` FROM users WHERE login = ?";
    private static final String CHECK_UNIQUE_EMAIL = "SELECT `email` FROM users WHERE email = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users set `password` = ? WHERE `id_user` = ?";
    private static final String UPDATE_EMAIL = "UPDATE users set `email` = ? WHERE `id_user` = ?";

    Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public int addOne(User user) {
        int idGenerated = -1;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
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
            return idGenerated;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<User> selectALL() {
        List<User> users = new ArrayList<>();

        try ( Statement statement = conn.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {
            while ( rs.next() ) {
                users.add(getUserFromRS(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findByLogin(String login, String password) {
        User user = null;

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_LOGIN) ) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if ( rs.next() ) {
                user = getUserFromRS(rs);
            }
            rs.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User findById(int id) {
        User user = null;

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                user = getUserFromRS(rs);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteById(int id) {
        try ( PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuchEmail(String email) {
        return isSuchRecord(email, CHECK_UNIQUE_EMAIL);
    }

    public boolean isSuchLogin(String login) {
        return isSuchRecord(login, CHECK_UNIQUE_LOGIN);
    }

    private boolean isSuchRecord(String key, String sql) {
        boolean result = false;

        try ( PreparedStatement statement = conn.prepareStatement(sql) ) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            result = rs.next();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUserFromRS(ResultSet rs) throws SQLException {
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

    public boolean updatePassword(int id, String password) {
        int result = 0;

        try ( PreparedStatement statement = conn.prepareStatement(UPDATE_PASSWORD) ) {
            statement.setString(1, password);
            statement.setInt(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (result == 1) ;
    }

    public boolean updateEmail(Integer id, String email) {
        int result = 0;

        try ( PreparedStatement statement = conn.prepareStatement(UPDATE_EMAIL) ) {
            statement.setString(1, email);
            statement.setInt(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        return (result == 1) ;
    }
}
