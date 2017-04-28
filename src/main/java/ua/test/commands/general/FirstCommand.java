package ua.test.commands.general;

import ua.test.commands.Command;
import ua.test.entity.Role;
import ua.test.entity.User;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("idUser");

        if ( userId == null ) {
            request.getRequestDispatcher("/pages/signIn.jsp").forward(request, response);
            return;
        }

        User user = ServiceFactory.getInstance().getUserService().getUserById(userId);

        if ( user.getRole() == Role.STUDENT ) {
            request.setAttribute("tests", ServiceFactory.getInstance().getTestService().findAllActivatedTests());
            request.getRequestDispatcher("/pages/student/tests.jsp").forward(request, response);
        } else {
            request.setAttribute("tests", ServiceFactory.getInstance().getTestService().getTestsByUserId(userId));
            request.getRequestDispatcher("/pages/tutor/tests.jsp").forward(request, response);
        }
    }
}
