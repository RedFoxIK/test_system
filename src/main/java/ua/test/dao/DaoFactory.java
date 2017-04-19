package ua.test.dao;

import java.sql.Connection;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public static UserDao getUserDao(Connection conn) {
        return new UserDao(conn);
    }

    public static TestDao getTestDao(Connection conn) {
        return new TestDao(conn);
    }

    public static QuestionDao getQuestionDao(Connection conn) {
        return new QuestionDao(conn);
    }

    public static AnswerDao getAnswerDao(Connection conn) {
        return new AnswerDao(conn);
    }
}
