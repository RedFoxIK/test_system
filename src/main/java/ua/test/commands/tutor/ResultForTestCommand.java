package ua.test.commands.tutor;

import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultForTestCommand implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer testId = Integer.valueOf(request.getParameter("id_test"));

        if ( testId != null ) {
            request.setAttribute("results", ServiceFactory.getInstance().getResultService().getResultsByTestId(testId));
        }
        request.getRequestDispatcher("/pages/tutor/test_results.jsp").forward(request, response);
    }
}
