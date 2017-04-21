package ua.test.commands;

import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class TestCommand implements Command {
    TestService testService = ServiceFactory.getTestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = testService.getTestById(testId);
        List<Question> questions = testService.getQuestionsByTestId(testId);
        String idQuestions = UUID.randomUUID().toString();

        request.setAttribute("test", test);
        request.setAttribute("questions", questions);
        request.getSession().setAttribute(idQuestions, questions);
        request.setAttribute("idQuestions", idQuestions);

        request.getRequestDispatcher("/jsp/student/test.jsp").forward(request, response);
    }
}
