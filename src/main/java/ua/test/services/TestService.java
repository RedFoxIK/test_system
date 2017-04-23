package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.AnswerDao;
import ua.test.dao.DaoFactory;
import ua.test.dao.QuestionDao;
import ua.test.dao.TestDao;
import ua.test.entity.Question;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;

public class TestService {
    Connection conn = DataSource.getInstance().getConnection();
    TestDao testDao = DaoFactory.getInstance().getTestDao(conn);
    QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao(conn);
    AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao(conn);

    public List<Test> getAllTests() {
        return testDao.selectALL();
    }

    public List<Question> getQuestionsByTestId(int testId) {
        List<Question> questions = questionDao.selectByTestId(testId);

        for ( Question question: questions ) {
            int id = question.getId();
            question.addAnswers(answerDao.selectByQuestionId(id));
        }
        return questions;
    }

    public Test getTestById(int testId) {
        return testDao.findById(testId);
    }

    public List<Test> getTestsByUserId(int id) {
        return testDao.selectByUserId(id);
    }

}