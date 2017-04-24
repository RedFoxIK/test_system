package ua.test.dao.impl;

import org.apache.log4j.Logger;
import ua.test.connection.ConnectionWrapper;
import ua.test.connection.TransactionManager;
import ua.test.dao.interfaces.TestDao;
import ua.test.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDaoImpl implements TestDao{
    private static final Logger LOGGER = Logger.getLogger(TestDao.class);

    private static final String ADD_TEST = "INSERT INTO tests(`caption`, `description`, `size`, `activated`, `author`) VALUES(?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT id_test, caption, description, size, activated, author FROM tests";
    private static final String SELECT_BY_USER_ID = "SELECT `id_test`, `caption`, `description`, `size`, `activated` FROM tests where author = ?";
    private static final String DELETE_BY_ID = "DELETE FROM tests WHERE id_test = ?";
    private static final String FIND_BY_ID = "SELECT id_test, caption, description, size, activated, author FROM tests WHERE id_test = ?";

    private static final String DB_CON_ERROR = "Database connection error";

    public TestDaoImpl() {
    }

    @Override
    public Integer addTest(Test test) {
        Integer idGenerated = null;

        try (ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
             PreparedStatement statement = connWrap.prepareStatement(ADD_TEST, Statement.RETURN_GENERATED_KEYS) ) {
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
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return idGenerated;
    }

    @Override
    public Test findById(int id) {
        Test test = null;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                test = new Test();
                test.setId(id);
                test.setCaption(rs.getString("caption"));
                test.setDescription(rs.getString("description"));
                test.setSize(rs.getInt("size"));
                test.setActivated(rs.getBoolean("activated"));
            }
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return test;
    }

    @Override
    public List<Test> findByUserId(int id) {
        List<Test> tests;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(SELECT_BY_USER_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            tests = getTests(rs);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return tests;
    }


    @Override
    public List<Test> findAll() {
        List<Test> tests;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                Statement statement = connWrap.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {
            tests = getTests(rs);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return tests;
    }

    @Override
    public void deleteById(int id) {
        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
        }
    }

    private List<Test> getTests(ResultSet rs) throws SQLException {
        List<Test> tests = new ArrayList<>();

        while (rs.next()) {
            Test test = new Test();
            test.setId(rs.getInt("id_test"));
            test.setCaption(rs.getString("caption"));
            test.setDescription(rs.getString("description"));
            test.setSize(rs.getInt("size"));
            test.setActivated(rs.getBoolean("activated"));
            tests.add(test);
        }
        return tests;
    }
}
