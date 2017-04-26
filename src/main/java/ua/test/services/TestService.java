package ua.test.services;

import ua.test.dao.DaoFactory;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.impl.QuestionDaoImpl;
import ua.test.dao.impl.TestDaoImpl;
import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.exceptions.TestCannotBeActivatedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        test.setQuestions(questions);
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

    public void addTest(String caption, String description, int size, Integer idUser) {
        Test test = new Test();

        test.setCaption(caption);
        test.setDescription(description);
        test.setSize(size);
        test.setActivated(false);
        test.setAuthor(ServiceFactory.getUserService().getUserById(idUser));
        testDao.addTest(test);
    }

    public void deleteTestById(Integer idTest) {
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
}