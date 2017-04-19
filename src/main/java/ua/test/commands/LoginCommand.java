package ua.test.commands;

import ua.test.services.ServiceFactory;
import ua.test.services.TestService;
import ua.test.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    private final UserService userService = ServiceFactory.getUserService();
    private final TestService testService = ServiceFactory.getTestService();
    private final String errorMess = "*Ups. Something wrong with your login or password. Please try again";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if ( userService.hasRegisteredUser(login, password) ) {
            request.setAttribute("tests", testService.getAllTests());
            request.getRequestDispatcher("/jsp/student/tests.jsp").forward(request, response);
        } else {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
