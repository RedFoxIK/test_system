package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.DaoFactory;
import ua.test.dao.impl.QuestionDaoImpl;
import ua.test.dao.impl.TestDaoImpl;
import ua.test.entity.Question;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;

public class TestService {
    Connection conn = DataSource.getInstance().getConnection();
    TestDaoImpl testDao = DaoFactory.getInstance().getTestDao(conn);
    QuestionDaoImpl questionDao = DaoFactory.getInstance().getQuestionDao(conn);
    AnswerDaoImp answerDao = DaoFactory.getInstance().getAnswerDao(conn);

    public List<Test> getAllTests() {
        return testDao.findAll();
    }

    public List<Question> getQuestionsByTestId(int testId) {
        List<Question> questions = questionDao.findByTestId(testId);

        for ( Question question: questions ) {
            int id = question.getId();
            question.addAnswers(answerDao.findByQuestionId(id));
        }
        return questions;
    }

    public Test getTestById(int testId) {
        return testDao.findById(testId);
    }

    public List<Test> getTestsByUserId(int id) {
        return testDao.findByUserId(id);
    }

}