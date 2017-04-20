package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.ResultDao;
import ua.test.dao.TestDao;
import ua.test.entity.Result;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;

public class ResultService {
    Connection conn = DataSource.getInstance().getConnection();
    ResultDao resultDao = new ResultDao(conn);
    TestDao testDao = new TestDao(conn);


    public ResultService() {}

    public List<Result> getResultsBuUserId(int id) {
        List<Result> results = resultDao.getByUserId(id);

        for ( Result result: results ) {
            Test test = testDao.findById(result.getTest().getId());
            result.setTest(test);
        }
        return  results;
    }
}
