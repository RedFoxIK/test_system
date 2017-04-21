package ua.test.commands;

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

        System.out.println("FIRST COMMAND");
        if ( userId == null ) {
            request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
            return;
        }

        User user = ServiceFactory.getUserService().getUserById(userId);

        if ( user.getRole() == Role.STUDENT ) {
            request.setAttribute("tests", ServiceFactory.getTestService().getAllTests());
            request.getRequestDispatcher("/jsp/student/tests.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/jsp/tutor/tests.jsp").forward(request, response);
        }
    }
}
