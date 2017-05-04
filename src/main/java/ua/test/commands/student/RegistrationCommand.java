package ua.test.commands.student;

import ua.test.commands.Command;
import ua.test.services.ServiceFactory;
import ua.test.services.TestService;
import ua.test.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private final UserService userService = ServiceFactory.getInstance().getUserService();

    private final String PASS_EXC = "*passwords are not equals!!!";
    private final String LOGIN_EXC = "*such login already registered!";
    private final String EMAIL_EXC = "*such email already registered!";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String passwordRe = request.getParameter("password_repeat");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        if ( outputError(request, password, passwordRe, login, email) ) {
            request.setAttribute("login", login);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
        } else {
            int idUser = userService.createUser(login, password, name, surname, email);
            request.getSession().setAttribute("idUser", idUser);
            response.sendRedirect("/testing_system/");
        }
    }

    private boolean outputError(HttpServletRequest request, String password, String passwordRe,
                                String login, String email) {
        boolean result = false;

        if ( !password.equals(passwordRe) ) {
            request.setAttribute("password_exc", PASS_EXC);
            result = true;
        }
        if ( userService.isSuchLogin(login) ) {
            request.setAttribute("login_exc", LOGIN_EXC);
            result = true;
        }
        if ( userService.isSuchEmail(email) ) {
            request.setAttribute("email_exc", EMAIL_EXC);
            result = true;
        }
        return result;
    }



}
