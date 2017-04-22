package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserPassword implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");

        if ( idUser != null ) {
           ServiceFactory.getUserService().changePassword(idUser, password);
           request.getRequestDispatcher("/my_profile").forward(request, response);
        }
    }
}
