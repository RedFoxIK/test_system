package ua.test.commands;

import ua.test.connection.DataSource;
import ua.test.dao.DaoFactory;
import ua.test.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class NewUserCommand implements Command {
    private final String passExc = "passwords are not equals!!!";
    private final String loginExc = "such login already registered!";
    private final String emailExc = "such email already registered!";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = DataSource.getInstance().getConnection();
        UserDao userDao = DaoFactory.getUserDao(conn);

        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("password_repeat");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        if ( !password.equals(passwordRepeat) ) {
            request.setAttribute("password_exc", "");
        }

        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }
}
