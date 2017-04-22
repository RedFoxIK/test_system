package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.AnswerDao;
import ua.test.dao.DaoFactory;
import ua.test.dao.QuestionDao;
import ua.test.dao.TestDao;
import ua.test.entity.Answer;
import ua.test.entity.Question;
import ua.test.entity.Test;

import java.sql.Connection;
import java.util.List;

public class TestService {
    Connection conn = DataSource.getInstance().getConnection();
    TestDao testDao = DaoFactory.getInstance().getTestDao(conn);
    QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao(conn);
    AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao(conn);

    public List<Test> getAllTests() {
        return testDao.selectALL();
    }

    public List<Question> getQuestionsByTestId(int testId) {
        List<Question> questions = questionDao.selectByTestId(testId);

        for ( Question question: questions ) {
            int id = question.getId();
            question.addAnswers(answerDao.selectByQuestionId(id));
        }
        return questions;
    }

    public Test getTestById(int testId) {
        return testDao.findById(testId);
    }

    public List<Test> getTestsByUserId(int id) {
        return testDao.selectByUserId(id);
    }

    public void addQuestion(int idTest, String questionText, List<String> answersText, String[] rightAnswers) {
        int numAnsw = answersText.size();
        int numRightAnsw = rightAnswers.length;
        Question question = new Question();

        question.setText(questionText);
        for ( int i = 0; i < numAnsw; i++ ) {
            Answer answer = new Answer();
            answer.setText(answersText.get(i));
            question.addAnswer(answer);
        }
        for ( int i = 0; i < numRightAnsw; i++ ) {
            Integer index = Integer.parseInt(rightAnswers[i]);
            System.out.println(index);
            question.getAnswers().get(index).setRight(true);
        }
        addQuestion(idTest, question);
    }

    //TRANSACTION MUST BE HERE!!!!!
    private void addQuestion(int idTest, Question question) {
        int idQuestion = questionDao.addOne(question, idTest);
        List<Answer> answers = question.getAnswers();

        for ( Answer answer: answers ) {
            int idAnswer = answerDao.addOne(answer, idQuestion);
            answer.setId(idAnswer);
        }
    }


}