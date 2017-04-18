package ua.test.dao;

import ua.test.entity.Answer;
import ua.test.entity.Question;

import java.sql.*;

public class AnswerDao {
    private final String ADD_ONE = "INSERT INTO answers(`text`, `right`, `id_question`) VALUES(?, ?, ?)";
    private final String DELETE_BY_ID = "DELETE FROM answers WHERE id_answer = ?";
    private final String FIND_BY_ID = "SELECT id_answer, text, right, id_question FROM answers WHERE id_answer = ?";

    Connection conn;

    public AnswerDao(Connection conn) {
        this.conn = conn;
    }

    public int addOne(Answer answer) {
        int idGenerated = -1;

        answer.getText();
        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, answer.getText());
            statement.setBoolean(2, answer.isRigth());
            statement.setInt(3, answer.getQuestion().getId());
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
                answer.setQuestion(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
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
