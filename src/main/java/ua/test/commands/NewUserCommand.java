package ua.test.commands;

import ua.test.services.ServiceFactory;
import ua.test.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewUserCommand implements Command {
    private final UserService userService = ServiceFactory.getUserService();
    private final String PASS_EXC = "*passwords are not equals!!!";
    private final String LOGIN_EXC = "*such login already registered!";
    private final String EMAIL_EXC = "*such email already registered!";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> userData = new HashMap<>();

        userData.put("password", request.getParameter("password"));
        userData.put("passwordRepeat", request.getParameter("password_repeat"));
        userData.put("login", request.getParameter("login"));
        userData.put("email", request.getParameter("email"));
        userData.put("name", request.getParameter("name"));
        userData.put("surname", request.getParameter("surname"));

        if ( outputError(request, userData) ) {
            request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        } else {
            userService.createUser(userData);
            request.getRequestDispatcher("/jsp/student/tests.jsp").forward(request, response);
        }
    }

    private boolean outputError(HttpServletRequest request, Map<String, String> userData) {
        boolean result = false;

        if ( userData.get("password").equals(userData.get("passwordRepeat")) ) {
            request.setAttribute("password_exc", PASS_EXC);
            result = true;
        }
        if ( userService.isSuchLogin(userData.get("login") ) ) {
            request.setAttribute("login_exc", LOGIN_EXC);
            result = true;
        }
        if ( userService.isSuchEmail(userData.get("email")) ) {
            request.setAttribute("email_exc", EMAIL_EXC);
            result = true;
        }
        return result;
    }



}
