package ua.test.dao;

import ua.test.dao.impl.*;
import ua.test.dao.interfaces.*;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private final AnswerDao answerDao = new AnswerDaoImp();
    private final ResultDao resultDao = new ResultDaoImpl();
    private final QuestionDao questionDao = new QuestionDaoImpl();
    private final TestDao testDao = new TestDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public TestDao getTestDao() {
        return testDao;
    }

    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    public AnswerDao getAnswerDao() {
        return answerDao;
    }

    public ResultDao getResultDao() {
        return resultDao;
    }
}
