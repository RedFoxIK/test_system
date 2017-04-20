package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.QuestionDao;
import ua.test.dao.TestDao;
import ua.test.entity.Question;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;

public class TestService {
    Connection conn = DataSource.getInstance().getConnection();
    TestDao testDao = new TestDao(conn);
    QuestionDao questionDao = new QuestionDao(conn);

    public List<Test> getAllTests() {
        return testDao.selectALL();
    }

    public List<Question> getQuestionsByTestId(int testId) {
        return questionDao.selectByTestId(testId);
    }

    public String getTestCaptionById(int testId) {
        Test test = testDao.findById(testId);
        return test.getCaption();
    }
}
