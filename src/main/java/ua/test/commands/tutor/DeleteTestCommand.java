package ua.test.commands.tutor;

import ua.test.entity.Test;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteTestCommand implements ua.test.commands.Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTest = Integer.parseInt(request.getParameter("id_test"));

        ServiceFactory.getInstance().getTestService().deleteTestById(idTest);
        response.sendRedirect("/testing_system/");
    }
}
