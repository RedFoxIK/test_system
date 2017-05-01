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

public class EndTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Test test = (Test) request.getSession().getAttribute("userTest");
        List<Question> questions = test.getQuestions();
        Map<Integer, List<String>> testResult = new HashMap<>();
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        double mark;

        request.getSession().removeAttribute("userTest");
        request.getSession().removeAttribute("timeTest");
        for ( Question question: questions ) {
            Integer questionId = question.getId();
            String[] answersArray = request.getParameterValues(questionId.toString());
            List<String> answers  = (answersArray == null) ? null : Arrays.asList(answersArray);
            testResult.put(questionId, answers);
        }
        mark = ServiceFactory.getInstance().getResultService().giveMark(testResult);
        ServiceFactory.getInstance().getResultService().addResult(idUser, test, mark, LocalDateTime.now());
        request.getSession().setAttribute("mark", mark);
        response.sendRedirect("/testing_system/student/show_result");
    }
}
