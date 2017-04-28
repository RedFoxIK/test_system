package ua.test.commands.tutor;

import ua.test.commands.Command;
import ua.test.entity.Answer;
import ua.test.entity.Question;
import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowTutorTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("id_test"));
        Test test = ServiceFactory.getInstance().getTestService().getTestById(testId);
        request.setAttribute("test", test);
        request.getRequestDispatcher("/pages/tutor/test.jsp").forward(request, response);
    }
}
