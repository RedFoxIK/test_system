package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.constants.Links;
import ua.test.services.ServiceFactory;
import ua.test.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserEmailCommand implements Command {
    private final String EMAIL_EXC = "*Email hasn't changed. Such email already registered!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        UserService userService = ServiceFactory.getInstance().getUserService();

        if ( userService.isSuchEmail(email) ) {
            request.setAttribute("email_exc", EMAIL_EXC);
        } else {
            userService.changeEmail(idUser, email);
        }
        response.sendRedirect(Links.Uri.PROFILE_PAGE);
    }
}
