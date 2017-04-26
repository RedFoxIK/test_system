package ua.test.dao;

import ua.test.dao.impl.*;
import ua.test.dao.interfaces.*;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public TestDao getTestDao() {
        return new TestDaoImpl();
    }

    public QuestionDao getQuestionDao() {
        return new QuestionDaoImpl();
    }

    public AnswerDao getAnswerDao() {
        return new AnswerDaoImp();
    }

    public ResultDao getResultDao() {
        return new ResultDaoImpl();
    }
}
