package ua.test.commands;

import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditTest implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = ServiceFactory.getTestService().getTestById(testId);
        List<Question> questions = ServiceFactory.getTestService().getQuestionsByTestId(testId);
        request.setAttribute("test", test);
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("/jsp/tutor/test.jsp").forward(request, response);
    }
}
