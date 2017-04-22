package ua.test.dao;

import ua.test.entity.Test;
import ua.test.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDao {
    private final String ADD_ONE = "INSERT INTO tests(`caption`, `description`, `size`, `activated`, `author`) VALUES(?, ?, ?, ?, ?)";
    private final String SELECT_ALL = "SELECT id_test, caption, description, size, activated, author FROM tests";
    private final String SELECT_BY_USER_ID = "SELECT `id_test`, `caption`, `description`, `size`, `activated` FROM tests where author = ?";
    private final String DELETE_BY_ID = "DELETE FROM tests WHERE id_test = ?";
    private final String FIND_BY_ID = "SELECT id_test, caption, description, size, activated, author FROM tests WHERE id_test = ?";

    Connection conn;

    public TestDao(Connection conn) {
        this.conn = conn;
    }

    public int addOne(Test test) {
        int idGenerated = -1;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, test.getCaption());
            statement.setString(2, test.getDescription());
            statement.setInt(3, test.getSize());
            statement.setBoolean(4, test.isActivated());
            statement.setInt(5, test.getAuthor().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return idGenerated;
    }

    public Test findById(int id) {
        Test test = null;

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                test = new Test();
                test.setId(id);
                test.setCaption(rs.getString("caption"));
                test.setDescription(rs.getString("description"));
                test.setSize(rs.getInt("size"));
                test.setActivated(rs.getBoolean("activated"));
                User user = (new UserDao(conn)).findById(rs.getInt("author"));
                test.setAuthor(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public List<Test> selectALL() {
        List<Test> tests = new ArrayList<>();

        try ( Statement statement = conn.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {

            while ( rs.next() ) {
                Test test = new Test();
                test.setId(rs.getInt("id_test"));
                test.setCaption(rs.getString("caption"));
                test.setDescription(rs.getString("description"));
                test.setSize(rs.getInt("size"));
                test.setActivated(rs.getBoolean("activated"));
                User user = (new UserDao(conn)).findById(rs.getInt("author"));
                test.setAuthor(user);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public void deleteById(int id) {
        try ( PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Test> selectByUserId(int id) {
        List<Test> tests = new ArrayList<>();

        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_USER_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while ( rs.next() ) {
                Test test = new Test();
                test.setId(rs.getInt("id_test"));
                test.setCaption(rs.getString("caption"));
                test.setDescription(rs.getString("description"));
                test.setSize(rs.getInt("size"));
                test.setActivated(rs.getBoolean("activated"));
                tests.add(test);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }
}
