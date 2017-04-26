package ua.test.commands.student;

import ua.test.commands.Command;
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

public class StartTestCommand implements Command {
    TestService testService = ServiceFactory.getTestService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = testService.getTestById(testId);

        test = testService.shuffleQuestions(test);
        request.setAttribute("test", test);
        request.getSession().setAttribute("userTest", test);

        request.getRequestDispatcher("/pages/student/test.jsp").forward(request, response);
    }
}
