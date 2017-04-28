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
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final TestService testService = ServiceFactory.getInstance().getTestService();
    private final String errorMess = "*Ups. Something wrong with your login or password. Please try again";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.getByLoginAndPassword(login, password);
        if ( user != null ) {
            int idUser = user.getId();
            request.getSession().setAttribute("idUser", idUser);
                response.sendRedirect("/testing_system/user/tests");
        } else {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher("/pages/signIn.jsp").forward(request, response);
        }
    }
}
