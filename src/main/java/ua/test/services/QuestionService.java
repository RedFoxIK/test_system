package ua.test.services;

import ua.test.connection.TransactionManager;
import ua.test.dao.DaoFactory;
import ua.test.dao.impl.AnswerDaoImp;
import ua.test.dao.impl.QuestionDaoImpl;
import ua.test.entity.Answer;
import ua.test.entity.Question;

import java.util.List;

public class QuestionService {
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

    private void addQuestion(int idTest, Question question) {
        QuestionDaoImpl questionDao = DaoFactory.getInstance().getQuestionDao();
        AnswerDaoImp answerDao = DaoFactory.getInstance().getAnswerDao();

        TransactionManager.getInstance().beginTransaction();
        int idQuestion = questionDao.addQuestion(question, idTest);
        List<Answer> answers = question.getAnswers();

        for ( Answer answer: answers ) {
            int idAnswer = answerDao.addAnswer(answer, idQuestion);
            answer.setId(idAnswer);
        }
        TransactionManager.getInstance().commit();
    }

    public void deleteQuestion(int idQuestion) {
        QuestionDaoImpl questionDao = DaoFactory.getInstance().getQuestionDao();
        AnswerDaoImp answerDao = DaoFactory.getInstance().getAnswerDao();
        TransactionManager.getInstance().beginTransaction();
        List<Answer> answers = answerDao.findByQuestionId(idQuestion);

        for (Answer answer: answers) {
            answerDao.deleteById(answer.getId());
        }
        questionDao.deleteById(idQuestion);
        TransactionManager.getInstance().commit();
    }
}
