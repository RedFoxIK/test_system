package ua.test.services;

import ua.test.connection.TransactionManager;
import ua.test.dao.DaoFactory;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.impl.QuestionDaoImpl;
import ua.test.dao.interfaces.AnswerDao;
import ua.test.dao.interfaces.QuestionDao;
import ua.test.entity.Answer;
import ua.test.entity.Question;

import java.util.List;

public class QuestionService {
    QuestionService() {}

    public void addQuestion(int idTest, String questionText, boolean multChoice, List<String> answersText, String[] rightAnswers) {
        int numAnswers = answersText.size();
        int numRightAnswers = rightAnswers.length;
        Question question = new Question();

        question.setText(questionText);
        question.setMultChoice(multChoice);
        for ( int i = 0; i < numAnswers; i++ ) {
            Answer answer = new Answer();
            answer.setText(answersText.get(i));
            question.addAnswer(answer);
        }
        for ( int i = 0; i < numRightAnswers; i++ ) {
            Integer index = Integer.parseInt(rightAnswers[i]);
            question.getAnswers().get(index).setRight(true);
        }
        addQuestion(idTest, question);
    }

    public void deleteQuestion(int idQuestion) {
        TransactionManager.getInstance().beginTransaction();
        AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();
        QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
        List<Answer> answers = answerDao.findByQuestionId(idQuestion);

        for (Answer answer: answers) {
            answerDao.deleteById(answer.getId());
        }
        questionDao.deleteById(idQuestion);
        TransactionManager.getInstance().commit();
    }

    private void addQuestion(int idTest, Question question) {
        QuestionDao questionDao = DaoFactory.getInstance().getQuestionDao();
        AnswerDao answerDao = DaoFactory.getInstance().getAnswerDao();

        TransactionManager.getInstance().beginTransaction();
        int idQuestion = questionDao.addQuestion(question, idTest);
        List<Answer> answers = question.getAnswers();

        for ( Answer answer: answers ) {
            int idAnswer = answerDao.addAnswer(answer, idQuestion);
            answer.setId(idAnswer);
        }
        TransactionManager.getInstance().commit();
    }
}
