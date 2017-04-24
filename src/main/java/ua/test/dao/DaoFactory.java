package ua.test.dao;

import ua.test.dao.impl.*;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDaoImpl getUserDao() {
        return new UserDaoImpl();
    }

    public TestDaoImpl getTestDao() {
        return new TestDaoImpl();
    }

    public QuestionDaoImpl getQuestionDao() {
        return new QuestionDaoImpl();
    }

    public AnswerDaoImp getAnswerDao() {
        return new AnswerDaoImp();
    }

    public ResultDaoImpl getResultDao() {
        return new ResultDaoImpl();
    }
}
