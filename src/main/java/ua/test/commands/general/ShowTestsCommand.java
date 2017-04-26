package ua.test.commands.general;

import ua.test.entity.Role;
import ua.test.entity.User;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowTestsCommand implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = (int) request.getSession().getAttribute("idUser");
        User user = ServiceFactory.getUserService().getUserById(idUser);
        TestService testService = ServiceFactory.getTestService();

        if ( user.getRole() == Role.STUDENT ) {
            request.setAttribute("tests", testService.getAllTests());
            request.getRequestDispatcher("/pages/student/tests.jsp").forward(request, response);
        } else {
            request.setAttribute("tests", testService.getTestsByUserId(idUser));
            request.getRequestDispatcher("/pages/tutor/tests.jsp").forward(request, response);
        }
    }
}
