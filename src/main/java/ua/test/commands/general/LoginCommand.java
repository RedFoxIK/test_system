package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.entity.Role;
import ua.test.entity.User;
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

        User user = userService.getByLoginAndPassword(login, password);
        if ( user != null ) {
            request.getSession().setAttribute("idUser", user.getId());

            if ( user.getRole() == Role.STUDENT ) {
                request.setAttribute("tests", testService.getAllTests());
                request.getRequestDispatcher("/jsp/student/tests.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/jsp/tutor/tests.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/signIn.jsp").forward(request, response);
        }
    }
}
