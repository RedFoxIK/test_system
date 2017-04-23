package ua.test.dao;

import ua.test.entity.Answer;
import ua.test.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao {
    private static final String ADD_ONE = "INSERT INTO answers(`text`, `right`, `id_question`) VALUES(?, ?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM answers WHERE id_answer = ?";
    private static final String FIND_BY_ID = "SELECT id_answer, text, right, id_question FROM answers WHERE id_answer = ?";
    private static final String FIND_BY_QUESTION_ID = "SELECT `id_answer`, `text`, `right` FROM answers WHERE `id_question` = ?";
    private static final String FIND_RIGHT_ANSWERS_BY_QUESTION_ID = "SELECT `id_answer`, `text` FROM answers WHERE `id_question` = ? AND `right` = 1";

    Connection conn;

    public AnswerDao(Connection conn) {
        this.conn = conn;
    }

    public int addOne(Answer answer, int idQuestion) {
        int idGenerated = -1;

        answer.getText();
        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, answer.getText());
            statement.setBoolean(2, answer.isRight());
            statement.setInt(3, idQuestion);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return idGenerated;
    }

    public Answer findById(int id) {
        Answer answer = null;

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                answer = new Answer();
                answer.setId(id);
                answer.setText(rs.getString("text"));
                System.out.println(rs.getByte("right"));
                Question question = (new QuestionDao(conn)).findById(rs.getInt("id_question"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public List<Answer> selectByQuestionId(int id) {
        List<Answer> answers = new ArrayList<>();

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_QUESTION_ID) ) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public List<Answer> selectRightAnswersByQuestionId(int id) {
        List<Answer> answers = new ArrayList<>();

        try ( PreparedStatement statement = conn.prepareStatement(FIND_RIGHT_ANSWERS_BY_QUESTION_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while ( rs.next() ) {
                Answer answer = new Answer();

                answer.setId(rs.getInt("id_answer"));
                answer.setText(rs.getString("text"));
                answer.setRight(true);
                answers.add(answer);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public void deleteById(int id) {
        try ( PreparedStatement statement = conn.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
