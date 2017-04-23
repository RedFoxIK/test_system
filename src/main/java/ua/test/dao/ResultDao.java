package ua.test.dao;

import ua.test.entity.Result;
import ua.test.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDao {
    private static final String SELECT_BY_USER_ID = "SELECT `id_result`, `id_test`, `mark`, `date` FROM results WHERE `id_user`  = ?";
    private static final String ADD_ONE = "INSERT INTO results(`id_user`, `id_test`, `mark`, `date`) VALUES(?, ?, ?, ?)";

    Connection conn;

    public ResultDao(Connection conn) {
        this.conn = conn;
    }

    public List<Result> getByUserId(int id) {
        List<Result> results = new ArrayList<>();

        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_USER_ID) ) {
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
            e.printStackTrace();
        }
        return results;
    }

    public int addOne(Result result) {
        int idGenerated = -1;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setInt(1, result.getUser().getId());
            statement.setInt(2, result.getTest().getId());
            System.out.println("ATTENTION MARK = " + result.getMark() + " !!!!!");
            statement.setDouble(3, result.getMark());
            statement.setTimestamp(4, Timestamp.valueOf(result.getDateTime()));

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
            System.out.println("id = " + idGenerated);
            rs.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return idGenerated;
    }

}
