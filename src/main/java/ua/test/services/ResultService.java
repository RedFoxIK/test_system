package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.AnswerDao;
import ua.test.dao.DaoFactory;
import ua.test.dao.ResultDao;
import ua.test.dao.TestDao;
import ua.test.entity.Answer;
import ua.test.entity.Result;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ResultService {
    Connection conn = DataSource.getInstance().getConnection();
    ResultDao resultDao = DaoFactory.getInstance().getresultDao(conn);
    TestDao testDao = DaoFactory.getInstance().getTestDao(conn);
    AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao(conn);


    public ResultService() {}

    public List<Result> getResultsBuUserId(int id) {
        List<Result> results = resultDao.getByUserId(id);

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
            List<Answer> answers = answerDao.selectRightAnswersByQuestionId(questionId);

            if ( isRightAnswer(userAnswers, answers) ) {
                rightAnswers += 1;
            }
        }
        return getPercent(rightAnswers, testResult.size());
    }

    public void addResult(int idUser, int idTest, double mark) {
//        resultDao.addOne();
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

            if ( !userAnswers.contains(id.toString()) ) {
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
