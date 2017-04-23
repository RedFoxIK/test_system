package ua.test.dao.interfaces;

import ua.test.entity.Result;

import java.util.List;

public interface ResultDao {
    Integer addResult(Result result);
    List<Result> findByUserId(int id);
}
