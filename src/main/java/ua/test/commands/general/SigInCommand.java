package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.constants.Links;
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
    private final String errorMess = "*Ups. Something wrong with your login or password. Please try again";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = ServiceFactory.getInstance().getUserService().getByLoginAndPassword(login, password);
        if ( user != null ) {
            int idUser = user.getId();
            request.getSession().setAttribute("idUser", idUser);
                response.sendRedirect(Links.Uri.TESTS_PAGE);
        } else {
            request.setAttribute("error", errorMess);
            request.getRequestDispatcher(Links.Jsp.SIGN_IN_PAGE).forward(request, response);
        }
    }
}
