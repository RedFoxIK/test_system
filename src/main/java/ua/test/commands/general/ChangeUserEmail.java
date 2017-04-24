package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.services.ServiceFactory;
import ua.test.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserEmail implements Command {
    private final String EMAIL_EXC = "*Email hasn't changed. Such email already registered!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        UserService userService = ServiceFactory.getUserService();

        if ( idUser != null ) {
            if ( userService.isSuchEmail(email) ) {
                request.setAttribute("email_exc", EMAIL_EXC);
            } else {
                System.out.println(email);
                userService.changeEmail(idUser, email);
            }
            request.getRequestDispatcher("/my_profile").forward(request, response);
        }
    }
}
