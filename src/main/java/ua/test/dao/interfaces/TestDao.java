package ua.test.dao.interfaces;

import ua.test.entity.Test;

import java.util.List;

public interface TestDao {
    Integer addTest(Test test);
    Test findById(int id);
    List<Test> findByUserId(int id);
    List<Test> findAll();
    void deleteById(int id);
    void updateTest(Test test);
}
