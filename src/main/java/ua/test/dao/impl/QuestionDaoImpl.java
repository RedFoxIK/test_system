package ua.test.dao.impl;

import org.apache.log4j.Logger;
import ua.test.connection.ConnectionWrapper;
import ua.test.connection.TransactionManager;
import ua.test.dao.interfaces.QuestionDao;
import ua.test.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private static final Logger LOGGER = Logger.getLogger(QuestionDao.class);

    private static final String ADD_QUESTION = "INSERT INTO questions(`text`, `id_test`, `mult_choice`) VALUES(?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT `text` FROM questions WHERE `id_question` = ?";
    private static final String SELECT_BY_TEST_ID = "SELECT `id_question`, `text`, `mult_choice` FROM questions WHERE `id_test` = ?";
    private static final String SELECT_ALL = "SELECT `id_question`, `text`, `mult_choice` FROM questions";
    private static final String DELETE_BY_ID = "DELETE FROM questions WHERE `id_question` = ?";

    private static final String DB_CON_ERROR = "Database connection error";

    public QuestionDaoImpl() {
    }

    @Override
    public Integer addQuestion(Question question, int idTest) {
        Integer idGenerated = null;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
             PreparedStatement statement = connWrap.prepareStatement(ADD_QUESTION, Statement.RETURN_GENERATED_KEYS) ) {
            statement.setString(1, question.getText());
            statement.setInt(2, idTest);
            statement.setBoolean(3, question.isMultChoice());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                idGenerated = rs.getInt(1);
            }
            rs.close();
        } catch ( SQLException e ) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return idGenerated;
    }

    @Override
    public Question findById(int id) {
        Question question = null;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(SELECT_BY_ID) ) {
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
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                Statement statement = connWrap.createStatement();
              ResultSet rs = statement.executeQuery(SELECT_ALL) ) {
            questions = getQuestions(rs);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return questions;
    }

    @Override
    public List<Question> findByTestId(int id) {
        List<Question> questions;

        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(SELECT_BY_TEST_ID) ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            questions = getQuestions(rs);
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
            return null;
        }
        return questions;
    }

    @Override
    public void deleteById(int id) {
        try ( ConnectionWrapper connWrap = TransactionManager.getInstance().getConnectionWrapper();
                PreparedStatement statement = connWrap.prepareStatement(DELETE_BY_ID) ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(DB_CON_ERROR + " " + e);
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
