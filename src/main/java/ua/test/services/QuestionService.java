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
        int numAnsw = answersText.size();
        int numRightAnsw = rightAnswers.length;
        Question question = new Question();

        question.setText(questionText);
        question.setMultChoice(multChoice);
        for ( int i = 0; i < numAnsw; i++ ) {
            Answer answer = new Answer();
            answer.setText(answersText.get(i));
            question.addAnswer(answer);
        }
        for ( int i = 0; i < numRightAnsw; i++ ) {
            Integer index = Integer.parseInt(rightAnswers[i]);
            question.getAnswers().get(index).setRight(true);
        }
        addQuestion(idTest, question);
    }

    //TRANSACTION MUST BE HERE!!!!!
    private void addQuestion(int idTest, Question question) {
        QuestionDaoImpl questionDao = DaoFactory.getInstance().getQuestionDao();
        AnswerDaoImp answerDao = DaoFactory.getInstance().getAnswerDao();

//        TransactionManager.getInstance().beginTransaction();
        int idQuestion = questionDao.addQuestion(question, idTest);
        List<Answer> answers = question.getAnswers();

        for ( Answer answer: answers ) {
            int idAnswer = answerDao.addAnswer(answer, idQuestion);
            answer.setId(idAnswer);
        }
//        TransactionManager.getInstance().commit();
//        TransactionManager.getInstance().close();
    }

    //TRANSACTION MUST BE HERE!!!!!
    public void deleteQuestion(int idQuestion) {
        QuestionDaoImpl questionDao = DaoFactory.getInstance().getQuestionDao();
        AnswerDaoImp answerDao = DaoFactory.getInstance().getAnswerDao();

        Question question = questionDao.findById(idQuestion);
        List<Answer> answers = answerDao.findByQuestionId(idQuestion);

        for (Answer answer: answers) {
            answerDao.deleteById(answer.getId());
        }
        questionDao.deleteById(idQuestion);
    }
}
