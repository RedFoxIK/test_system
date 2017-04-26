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

public class SigInCommand implements Command {
    private final UserService userService = ServiceFactory.getUserService();
    private final TestService testService = ServiceFactory.getTestService();
    private final String errorMess = "*Ups. Something wrong with your login or password. Please try again";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.getByLoginAndPassword(login, password);
        if ( user != null ) {
            int idUser = user.getId();
            request.getSession().setAttribute("idUser", idUser);

            if ( user.getRole() == Role.STUDENT ) {
                request.setAttribute("tests", testService.getAllTests());
                request.getRequestDispatcher("/jsp/student/tests.jsp").forward(request, response);
            } else {
                request.setAttribute("tests", testService.getTestsByUserId(idUser));
                request.getRequestDispatcher("/jsp/tutor/tests.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
        }
    }
}
