package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.TestDao;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;

public class TestService {
    Connection conn = DataSource.getInstance().getConnection();
    TestDao testDao = new TestDao(conn);

    public List<Test> getAllTests() {
        return testDao.selectALL();
    }
}
