package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.constants.Links;
import ua.test.entity.User;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowMyProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        User user = ServiceFactory.getInstance().getUserService().getUserById(idUser);
        request.setAttribute("user", user);
        request.getRequestDispatcher(Links.Jsp.MY_PROFILE_PAGE).forward(request, response);
    }
}
