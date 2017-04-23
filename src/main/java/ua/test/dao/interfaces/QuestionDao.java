package ua.test.dao.interfaces;

import ua.test.entity.Question;

import java.util.List;

public interface QuestionDao {
    Integer addQuestion(Question question, int idTest);
    Question findById(int id);
    List<Question> findAll();
    List<Question> findByTestId(int id);
    void deleteById(int id);
}
