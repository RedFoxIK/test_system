package ua.test.dao.impl;

import org.apache.log4j.Logger;
import ua.test.connection.ConnectionWrapper;
import ua.test.connection.TransactionManager;
import ua.test.dao.interfaces.ResultDao;
import ua.test.entity.Result;
import ua.test.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {
    private static final Logger LOGGER = Logger.getLogger(ResultDao.class);

    private static final String SELECT_BY_USER_ID = "SELECT `id_result`, `id_test`, `mark`, `date` FROM results WHERE `id_user`  = ?";
    private static final String ADD_RESULT = "INSERT INTO results(`id_user`, `id_test`, `mark`, `date`) VALUES(?, ?, ?, ?)";

    private static final String DB_CON_ERROR = "Database connection error";

    public ResultDaoImpl() {
    }

    @Override
    public Integer addResult(Result result) {
        Integer idGenerated = null;

        try (ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
             PreparedStatement statement = connWrap.prepareStatement(ADD_RESULT, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setInt(1, result.getUser().getId());
            statement.setInt(2, result.getTest().getId());
            statement.setDouble(3, result.getMark());
            statement.setTimestamp(4, Timestamp.valueOf(result.getDateTime()));
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
    public List<Result> findByUserId(int id) {
        List<Result> results = new ArrayList<>();

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(SELECT_BY_USER_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while ( rs.next() ) {
                Result result = new Result();

                result.setId(rs.getInt("id_result"));
                result.setMark(rs.getDouble("mark"));
                result.setDateTime(rs.getTimestamp("date").toLocalDateTime());
                Test test = new Test();
                test.setId(rs.getInt("id_test"));
                result.setTest(test);
                results.add(result);
            }
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return results;

    }
}
