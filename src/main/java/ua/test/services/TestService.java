package ua.test.services;

import ua.test.dao.DaoFactory;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.impl.QuestionDaoImpl;
import ua.test.dao.impl.TestDaoImpl;
import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.exceptions.TestCannotBeActivatedException;

import java.util.List;
import java.util.logging.Logger;

public class TestService {
    TestDaoImpl testDao = DaoFactory.getInstance().getTestDao();
    QuestionDaoImpl questionDao = DaoFactory.getInstance().getQuestionDao();
    AnswerDaoImp answerDao = DaoFactory.getInstance().getAnswerDao();

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
        Test test = testDao.findById(testId);
        List<Question> questions = ServiceFactory.getTestService().getQuestionsByTestId(testId);
        test.addQuestions(questions);
        return test;
    }

    public List<Test> getTestsByUserId(int id) {
        return testDao.findByUserId(id);
    }


    public Test changeTestState(int idTest)  {
        Test test = getTestById(idTest);

        if ( test.isActivated() ) {
            test.setActivated(false);
        } else {
            test.setActivated(true);
        }
        testDao.updateTest(test);
        return test;
    }
}