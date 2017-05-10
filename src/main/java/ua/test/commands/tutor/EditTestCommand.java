package ua.test.commands.tutor;

import ua.test.constants.Messages;
import ua.test.services.ServiceFactory;
import ua.test.utils.Validation;

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


        if ( !Validation.isTestDurationValid(minutes) || !Validation.isTestSizeValid(size) ||
                Validation.isEmtyText(caption) ) {
            request.setAttribute("test", idTest);
            request.setAttribute("change_test_exc", Messages.TEST_CHANGE_EXC);
            request.getRequestDispatcher("/tutor/test").forward(request, response);
        } else {
            ServiceFactory.getInstance().getTestService().editTest(idTest, size, caption, description, minutes);
            response.sendRedirect("/testing_system/tutor/test?id_test="+idTest);
        }
    }
}
