package ua.test.dao;

import ua.test.entity.Result;
import ua.test.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDao {
    private final String SELECT_BY_USER_ID = "SELECT `id_result`, `id_test`, `result`, `date` FROM results WHERE `id_user`  = ?";
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
                result.setResult(rs.getDouble("result"));
                result.setDate(rs.getDate("date"));
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

}
