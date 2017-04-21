package ua.test.commands;

import ua.test.entity.Question;
import ua.test.entity.User;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestResultCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idQuestions = request.getParameter("idQuestions");
        List<Question> questions = (List<Question>) request.getSession().getAttribute(idQuestions);
        request.getSession().removeAttribute(idQuestions);
        Map<Integer, List<String>> testResult = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        double mark;

        for ( Question question: questions ) {
            Integer questionId = question.getId();
            String[] answersArray = request.getParameterValues(questionId.toString());
            List<String> answers  = (answersArray == null) ? null : Arrays.asList(answersArray);
            testResult.put(questionId, answers);
        }
        Integer idTest = Integer.parseInt(request.getParameter("test"));
        mark = ServiceFactory.getResultService().giveMark(testResult);
        request.setAttribute("mark", mark);
        ServiceFactory.getResultService().addResult(user.getId(), idTest, mark);
        //not forget to add result to DB

        request.getRequestDispatcher("/jsp/student/testresult.jsp").forward(request, response);
    }
}
