package ua.test.services;

import ua.test.connection.TransactionManager;
import ua.test.dao.DaoFactory;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.impl.QuestionDaoImpl;
import ua.test.dao.impl.TestDaoImpl;
import ua.test.dao.impl.UserDaoImpl;
import ua.test.dao.interfaces.AnswerDao;
import ua.test.dao.interfaces.QuestionDao;
import ua.test.dao.interfaces.TestDao;
import ua.test.dao.interfaces.UserDao;
import ua.test.entity.Answer;
import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.entity.User;
import ua.test.exceptions.TestCannotBeActivatedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class TestService {
    UserDao userDao = DaoFactory.getInstance().getUserDao();
    TestDao testDao = DaoFactory.getInstance().getTestDao();
    QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
    AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

    TestService() {}

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
        List<Question> questions = ServiceFactory.getInstance().getTestService().getQuestionsByTestId(testId);
        test.setQuestions(questions);
        return test;
    }

    public List<Test> getTestsByUserId(int id) {
        return testDao.findByUserId(id);
    }


    public Test changeTestState(int idTest)  {
        Test test = getTestById(idTest);

        test.setActivated(!test.isActivated());
        testDao.updateState(test);
        return test;
    }

    public void addTest(String caption, String description, int size, Integer idUser) {
        Test test = new Test();

        test.setCaption(caption);
        test.setDescription(description);
        test.setSize(size);
        test.setActivated(false);
        test.setAuthor(ServiceFactory.getInstance().getUserService().getUserById(idUser));
        testDao.addTest(test);
    }

    public void deleteTestById(int idTest) {
        QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
        TransactionManager.getInstance().beginTransaction();
        Test test = getTestById(idTest);
        List<Question> questions = test.getQuestions();
        for ( Question question: questions ) {
            List<Answer> answers = question.getAnswers();

            for ( Answer answer: answers ) {
                answerDao.deleteById(answer.getId());
            }
            questionDao.deleteById(question.getId());
        }
        answerDao.deleteById(idTest);
        testDao.deleteById(idTest);
        TransactionManager.getInstance().commit();
    }

    public Test shuffleQuestions(Test test) {
        List<Question> newQuestions = new ArrayList<>();
        List<Question> oldQuestions = test.getQuestions();
        int numberQuestions = oldQuestions.size();
        int size = numberQuestions > test.getSize() ? test.getSize() : numberQuestions;

        Collections.shuffle(oldQuestions);
        for ( int i = 0; i < size; i++ ) {
            Collections.shuffle(oldQuestions.get(i).getAnswers());
            newQuestions.add(oldQuestions.get(i));
        }
        test.setQuestions(newQuestions);
        return test;
    }

    public List<Test> findAllActivatedTests() {
        List<Test> tests =  testDao.findAllActivated();

        for (Test test: tests) {
            User user = userDao.findById(test.getAuthor().getId());
            test.setAuthor(user);
        }
        return tests;
    }

    public void editTest(int idTest, int size, String caption, String description) {
        Test test = new Test();

        test.setId(idTest);
        test.setSize(size);
        test.setCaption(caption);
        test.setDescription(description);
        testDao.updateTest(test);

        List<Question> questions = questionDao.findByTestId(idTest);
        if ( questions.size() < size ) {
            test.setActivated(false);
            testDao.updateState(test);
        }
    }
}