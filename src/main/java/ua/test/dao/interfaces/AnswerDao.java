package ua.test.dao.interfaces;

import ua.test.entity.Answer;

import java.util.List;

public interface AnswerDao {
    Integer addAnswer(Answer answer, int idQuestion);
    Answer findById(int id);
    List<Answer> findByQuestionId(int id);
    List<Answer> findRightByQuestionId(int id);
    void deleteById(int id);
}
