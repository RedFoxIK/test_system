package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.AnswerDao;
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
    ResultDao resultDao = new ResultDao(conn);
    TestDao testDao = new TestDao(conn);
    AnswerDao answerDao = new AnswerDao(conn);


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
            List<Answer> answers = answerDao.selectByQuestionId(questionId);

            if ( isRightAnswer(userAnswers, answers) ) {
                rightAnswers += 1;
            }
        }
        System.out.println(rightAnswers);
        System.out.println(testResult.size());
        return (rightAnswers / testResult.size()) * 100;
    }

    private boolean isRightAnswer(List<String> userAnswers, List<Answer> answers) {
        if ( userAnswers == null || answers == null ) {
            return userAnswers == null && answers == null;
        }

        for (Answer answer: answers) {
            int id = answer.getId();
            boolean right = answer.isRigth();
            System.out.println(id + "= " + right);

            if ( !isRightAnswerInAnswers(userAnswers, id, right) ) {
                return false;
            }
        }
        return true;
    }

    private boolean isRightAnswerInAnswers(List<String> userAnswers, Integer id, boolean right) {
        if ( right ) {
            return userAnswers.contains(id.toString());
        }
        return !userAnswers.contains(id.toString());
    }

}
