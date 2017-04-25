package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddTest implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestService testService = ServiceFactory.getTestService();
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        String caption = request.getParameter("caption");
        String description = request.getParameter("description");
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<Test> tests;

        testService.addTest(caption, description, size, idUser);
        tests = testService.getTestsByUserId(idUser);
        request.setAttribute("tests", tests);
        request.getRequestDispatcher("/jsp/tutor/tests.jsp").include(request, response);
    }
}
