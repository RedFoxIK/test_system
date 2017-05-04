package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.constants.Links;
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

        ServiceFactory.getInstance().getUserService().changePassword(idUser, password);
        response.sendRedirect(Links.Uri.PROFILE_PAGE);
    }
}
