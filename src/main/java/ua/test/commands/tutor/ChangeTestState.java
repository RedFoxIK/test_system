package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.exceptions.TestCannotBeActivatedException;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeTestState implements ua.test.commands.Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestService testService = ServiceFactory.getTestService();
        int idTest = Integer.parseInt(request.getParameter("id_test"));
        Test test = testService.changeTestState(idTest);
        request.setAttribute("test", test);
        request.getRequestDispatcher("/jsp/tutor/test.jsp").include(request, response);
    }

}
