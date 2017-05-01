package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddTestCommand implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestService testService = ServiceFactory.getInstance().getTestService();
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        String caption = request.getParameter("caption");
        String description = request.getParameter("description");
        int minutes = Integer.parseInt(request.getParameter("minutes"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        List<Test> tests;

        testService.addTest(caption, description, size, minutes, idUser);
        tests = testService.getTestsByUserId(idUser);
        request.setAttribute("tests", tests);
        response.sendRedirect("/testing_system/");
    }
}
