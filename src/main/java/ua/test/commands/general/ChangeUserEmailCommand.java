package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.constants.Links;
import ua.test.services.ServiceFactory;
import ua.test.services.UserService;
import ua.test.utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserEmailCommand implements Command {
    private final String EMAIL_IS_NOT_VALID = "*Email is not valid";
    private final String EMAIL_EXC = "*Email hasn't changed. Such email already registered!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean rightEmail = true;
        String email = request.getParameter("email");
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");

        if ( ServiceFactory.getInstance().getUserService().isSuchEmail(email) ) {
            request.setAttribute("email_exc", EMAIL_EXC);
            rightEmail = false;
        }
        if ( !Validation.isEmailValid(email) ) {
            request.setAttribute("email_exc", EMAIL_IS_NOT_VALID);
            rightEmail = false;
        }
        if ( rightEmail ) {
            ServiceFactory.getInstance().getUserService().changeEmail(idUser, email);
            response.sendRedirect(Links.Uri.PROFILE_PAGE);
        } else {
            request.getRequestDispatcher("/user/my_profile").forward(request, response);
        }
    }
}
