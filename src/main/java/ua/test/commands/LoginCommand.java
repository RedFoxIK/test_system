package ua.test.commands;

import ua.test.connection.DataSource;
import ua.test.dao.DaoFactory;
import ua.test.dao.UserDao;
import ua.test.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class LoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Connection conn = DataSource.getInstance().getConnection();
        UserDao userDao = DaoFactory.getInstance().getUserDao(conn);
        User user = userDao.findByLogin(login, password);

        if ( user == null ) {
            request.getRequestDispatcher("/jsp/index4.jsp").forward(request, response);
            return;
        }
        if ( user.getRole().getRole().equals("student") ) {
            request.getRequestDispatcher("/jsp/student/tests.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsp/index2.jsp").forward(request, response);
        }
    }
}
