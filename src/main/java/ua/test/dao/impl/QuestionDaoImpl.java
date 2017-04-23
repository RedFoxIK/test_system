package ua.test.dao.impl;

import ua.test.dao.interfaces.QuestionDao;
import ua.test.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private static final String ADD_QUESTION = "INSERT INTO questions(`text`, `id_test`, `mult_choice`) VALUES(?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT `text` FROM questions WHERE `id_question` = ?";
    private static final String SELECT_BY_TEST_ID = "SELECT `id_question`, `text`, `mult_choice` FROM questions WHERE `id_test` = ?";
    private static final String SELECT_ALL = "SELECT `id_question`, `text`, `mult_choice` FROM questions";
    private static final String DELETE_BY_ID = "DELETE FROM questions WHERE `id_question` = ?";

    Connection conn;

    public QuestionDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer addQuestion(Question question, int idTest) {
        Integer idGenerated = null;

        try ( PreparedStatement statement = conn.prepareStatement(ADD_QUESTION, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, question.getText());
            statement.setInt(2, idTest);
            statement.setBoolean(3, question.isMultChoice());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
            rs.close();
            return idGenerated;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Question findById(int id) {
        Question question = null;

        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                question = new Question();
                question.setId(id);
                question.setText(rs.getString("text"));
                question.setMultChoice(rs.getBoolean("mult_choice"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        try ( Statement statement = conn.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {
            return getQuestions(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Question> findByTestId(int id) {
        try ( PreparedStatement statement = conn.prepareStatement(SELECT_BY_TEST_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
           return getQuestions(rs);
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

    private List<Question> getQuestions(ResultSet rs) throws SQLException {
        List<Question> questions = new ArrayList<>();

        while ( rs.next() ) {
            Question question = new Question();

            question.setId(rs.getInt("id_question"));
            question.setText(rs.getString("text"));
            question.setMultChoice(rs.getBoolean("mult_choice"));
            questions.add(question);
        }
        rs.close();
        return questions;
    }
}
