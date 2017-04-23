package ua.test.dao.impl;

import ua.test.dao.interfaces.AnswerDao;
import ua.test.entity.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImp implements AnswerDao {
    private static final String ADD_ANSWER = "INSERT INTO answers(`text`, `right`, `id_question`) VALUES(?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT `id_answer`, `text`, `right`, `id_question` FROM answers WHERE `id_answer` = ?";
    private static final String SELECT_BY_QUESTION_ID = "SELECT `id_answer`, `text`, `right` FROM answers WHERE `id_question` = ?";
    private static final String SELECT_RIGHT_BY_QUESTION_ID = "SELECT `id_answer`, `right`, `text` FROM answers WHERE `id_question` = ? AND `right` = 1";
    private static final String DELETE_BY_ID = "DELETE FROM answers WHERE id_answer = ?";

    Connection conn;

    public AnswerDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer addAnswer(Answer answer, int idQuestion) {
        Integer idGenerated = null;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_ANSWER, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, answer.getText());
            statement.setBoolean(2, answer.isRight());
            statement.setInt(3, idQuestion);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
            return idGenerated;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Answer findById(int id) {
        Answer answer = null;

        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                answer = new Answer();
                answer.setId(id);
                answer.setText(rs.getString("text"));
            }
            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        try ( PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Answer> findByQuestionId(int id) {
        return findByQuestionId(id, SELECT_BY_QUESTION_ID);
    }

    @Override
    public List<Answer> findRightByQuestionId(int id) {
        return findByQuestionId(id, SELECT_RIGHT_BY_QUESTION_ID);
    }

    private  List<Answer> findByQuestionId(int id, String sql) {
        List<Answer> answers = new ArrayList<>();

        try ( PreparedStatement statement = conn.prepareStatement(sql) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while ( rs.next() ) {
                Answer answer = new Answer();

                answer.setId(rs.getInt("id_answer"));
                answer.setText(rs.getString("text"));
                answer.setRight(rs.getBoolean("right"));
                answers.add(answer);
            }
            rs.close();
            return answers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
