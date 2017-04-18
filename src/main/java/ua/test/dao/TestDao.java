package ua.test.dao;

import ua.test.entity.Test;
import ua.test.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDao {
    private final String ADD_ONE = "INSERT INTO tests(`caption`, `description`, `author`) VALUES(?, ?, ?)";
    private final String SELECT_ALL = "SELECT id_test, caption, description, author FROM tests";
    private final String DELETE_BY_ID = "DELETE FROM tests WHERE id_test = ?";
    private final String FIND_BY_ID = "SELECT id_test, caption, description, author FROM tests WHERE id_test = ?";

    Connection conn;

    public TestDao(Connection conn) {
        this.conn = conn;
    }

    public int addOne(Test test) {
        int idGenerated = -1;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, test.getCaption());
            statement.setString(2, test.getDescription());
            statement.setInt(3, test.getAuthor().getId());
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
}
