package ua.test.dao;

import ua.test.entity.Question;
import ua.test.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private final String ADD_ONE = "INSERT INTO questions(`text`, `id_test`) VALUES(?, ?)";
    private final String SELECT_ALL = "SELECT id_question, text, id_test FROM questions";
    private final String DELETE_BY_ID = "DELETE FROM questions WHERE id_question = ?";
    private final String FIND_BY_ID = "SELECT id_question, text, id_test FROM questions WHERE id_question = ?";

    Connection conn;

    public QuestionDao(Connection conn) {
        this.conn = conn;
    }

    public int addOne(Question question) {
        int idGenerated = -1;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_ONE, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, question.getText());
            statement.setInt(2, question.getTest().getId());
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

    public Question findById(int id) {
        Question question = null;

        try ( PreparedStatement statement = conn.prepareStatement(FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                question = new Question();
                question.setId(id);
                question.setText(rs.getString("text"));
                Test test = (new TestDao(conn)).findById(rs.getInt("id_test"));
                question.setTest(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public List<Question> selectALL() {
        List<Question> questions = new ArrayList<>();

        try ( Statement statement = conn.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {

            while ( rs.next() ) {
                Question question = new Question();
                question.setId(rs.getInt("id_question"));
                question.setText(rs.getString("text"));
                Test test = (new TestDao(conn)).findById(rs.getInt("id_test"));
                question.setTest(test);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
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
