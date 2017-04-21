package ua.test.commands;

import ua.test.services.ResultService;
import ua.test.services.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultCommand implements Command {
    private final ResultService resultService = ServiceFactory.getResultService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (int) request.getSession().getAttribute("idUser");

        if ( userId != null ) {
            request.setAttribute("results", resultService.getResultsBuUserId(userId));
        }
        request.getRequestDispatcher("/jsp/student/results.jsp").forward(request, response);
    }
}
