package ua.test.commands;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTest implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = ServiceFactory.getTestService().getTestById(testId);
        System.out.println(test);
        request.setAttribute("test", test);
        request.getRequestDispatcher("/jsp/tutor/test.jsp").forward(request, response);
    }
}
