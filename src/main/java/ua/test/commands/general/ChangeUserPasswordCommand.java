package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserPasswordCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");

        ServiceFactory.getUserService().changePassword(idUser, password);
        response.sendRedirect("/testing_system/user/my_profile");
    }
}
