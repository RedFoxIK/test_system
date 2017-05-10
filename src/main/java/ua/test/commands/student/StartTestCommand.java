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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class StartTestCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestService testService = ServiceFactory.getInstance().getTestService();
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = testService.getTestById(testId);

        test = testService.shuffleQuestions(test);
        request.setAttribute("test", test);
        request.getSession().setAttribute("userTest", test);
        request.getSession().setAttribute("timeTest", LocalDateTime.now().plusMinutes(test.getMinutes()).withNano(0));
        response.sendRedirect("/testing_system/student/test");
    }
}
