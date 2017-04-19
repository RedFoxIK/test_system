package ua.test.dao;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {}

    public DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao(Connection conn) {
        return new UserDao(conn);
    }

    public TestDao getTestDao(Connection conn) {
        return new TestDao(conn);
    }

    public QuestionDao getQuestionDao(Connection conn) {
        return new QuestionDao(conn);
    }

    public AnswerDao getAnswerDao(Connection conn) {
        return new AnswerDao(conn);
    }

    public ResultDao getresultDao(Connection conn) {
        return new ResultDao(conn);
    }
}
