package ua.test.services;

import ua.test.connection.DataSource;
import ua.test.dao.AnswerDao;
import ua.test.dao.DaoFactory;
import ua.test.dao.QuestionDao;
import ua.test.entity.Answer;
import ua.test.entity.Question;

import java.sql.Connection;
import java.util.List;

public class QuestionService {
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
        Connection conn = DataSource.getInstance().getConnection();
        QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao(conn);
        AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao(conn);

        int idQuestion = questionDao.addOne(question, idTest);
        List<Answer> answers = question.getAnswers();

        for ( Answer answer: answers ) {
            int idAnswer = answerDao.addOne(answer, idQuestion);
            answer.setId(idAnswer);
        }
    }

    //TRANSACTION MUST BE HERE!!!!!
    public void deleteQuestion(int idQuestion) {
        Connection conn = DataSource.getInstance().getConnection();
        QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao(conn);
        AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao(conn);

        Question question = questionDao.findById(idQuestion);
        List<Answer> answers = answerDao.selectByQuestionId(idQuestion);

        for (Answer answer: answers) {
            answerDao.deleteById(answer.getId());
        }
        questionDao.deleteById(idQuestion);
    }
}
