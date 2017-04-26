package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateQuestion implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idTest = Integer.valueOf(request.getParameter("test"));
        Test test = ServiceFactory.getTestService().getTestById(idTest);
        request.setAttribute("test", test);
        request.getRequestDispatcher("/pages/tutor/newQuestion.jsp").forward(request, response);
    }
}
