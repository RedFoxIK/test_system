package ua.test.commands.tutor;

import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTestCommand implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTest = Integer.parseInt(request.getParameter("id_test"));
        int size = Integer.parseInt(request.getParameter("test_size"));
        String caption = request.getParameter("caption");
        String description = request.getParameter("description");
        int minutes = Integer.parseInt(request.getParameter("minutes"));

        ServiceFactory.getInstance().getTestService().editTest(idTest, size, caption, description, minutes);
        response.sendRedirect("/testing_system/tutor/test?id_test="+idTest);
    }
}
