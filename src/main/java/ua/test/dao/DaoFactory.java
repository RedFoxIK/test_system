package ua.test.dao;

import ua.test.dao.impl.*;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDaoImpl getUserDao(Connection conn) {
        return new UserDaoImpl(conn);
    }

    public TestDaoImpl getTestDao(Connection conn) {
        return new TestDaoImpl(conn);
    }

    public QuestionDaoImpl getQuestionDao(Connection conn) {
        return new QuestionDaoImpl(conn);
    }

    public AnswerDaoImp getAnswerDao(Connection conn) {
        return new AnswerDaoImp(conn);
    }

    public ResultDaoImpl getResultDao(Connection conn) {
        return new ResultDaoImpl(conn);
    }
}
