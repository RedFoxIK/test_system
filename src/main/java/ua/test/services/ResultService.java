package ua.test.services;

import ua.test.dao.DaoFactory;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.impl.ResultDaoImpl;
import ua.test.dao.impl.TestDaoImpl;
import ua.test.dao.interfaces.AnswerDao;
import ua.test.dao.interfaces.ResultDao;
import ua.test.dao.interfaces.TestDao;
import ua.test.dao.interfaces.UserDao;
import ua.test.entity.Answer;
import ua.test.entity.Result;
import ua.test.entity.Test;
import ua.test.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ResultService {
    ResultDao resultDao = DaoFactory.getInstance().getResultDao();
    TestDao testDao = DaoFactory.getInstance().getTestDao();

    ResultService() {}

    public List<Result> getResultsByUserId(int id) {
        List<Result> results = resultDao.findByUserId(id);

        for ( Result result: results ) {
            Test test = testDao.findById(result.getTest().getId());
            result.setTest(test);
        }
        return  results;
    }

    public double giveMark(Map<Integer, List<String>> testResult) {
        double rightAnswers = 0;

        for ( Integer questionId: testResult.keySet() ) {
            List<String> userAnswers = testResult.get(questionId);
            List<Answer> answers = DaoFactory.getInstance().getAnswerDao()
                    .findRightByQuestionId(questionId);

            if ( isRightAnswer(userAnswers, answers) ) {
                rightAnswers += 1;
            }
        }
        return getPercent(rightAnswers, testResult.size());
    }

    public void addResult(int idUser, Test test, double mark, LocalDateTime dateTime) {
        Result result = new Result();
        User user = ServiceFactory.getInstance().getUserService().getUserById(idUser);
        int id;

        result.setUser(user);
        result.setTest(test);
        result.setMark(mark);
        result.setDateTime(dateTime);
        id = DaoFactory.getInstance().getResultDao().addResult(result);
        result.setId(id);
    }

    public List<Result> getResultsByTestId(int testId) {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<Result> results = resultDao.findByTestId(testId);

        for ( Result result: results ) {
            User user = userDao.findById(result.getUser().getId());
            result.setUser(user);
        }
        return  results;
    }

    private boolean isRightAnswer(List<String> userAnswers, List<Answer> answers) {
        if ( userAnswers == null || answers == null ) {
            return userAnswers == null && answers == null;
        }
        if ( userAnswers.size() != answers.size() ) {
            return false;
        }

        for (Answer answer: answers) {
            Integer id = answer.getId();

            if ( !userAnswers.contains(id.toString()) && !userAnswers.contains(id)  ) {
                return false;
            }
        }

        return true;
    }

    private double getPercent(double rightAnswers, int answers) {
        double result = (rightAnswers / answers) * 100;
        return Math.round(result * 100.0) / 100.0;
    }
}
