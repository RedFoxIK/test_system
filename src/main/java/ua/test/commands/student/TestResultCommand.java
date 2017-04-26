package ua.test.commands.student;

import ua.test.commands.Command;
import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestResultCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Test test = (Test) request.getSession().getAttribute("userTest");
        List<Question> questions = test.getQuestions();
        Map<Integer, List<String>> testResult = new HashMap<>();
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        double mark;

        request.getSession().removeAttribute("userTest");

        for ( Question question: questions ) {
            Integer questionId = question.getId();
            String[] answersArray = request.getParameterValues(questionId.toString());
            List<String> answers  = (answersArray == null) ? null : Arrays.asList(answersArray);
            testResult.put(questionId, answers);
        }
        mark = ServiceFactory.getResultService().giveMark(testResult);
        request.setAttribute("mark", mark);
        ServiceFactory.getResultService().addResult(idUser, test, mark, LocalDateTime.now());

        request.getRequestDispatcher("/pages/student/test_result.jsp").forward(request, response);
    }
}
