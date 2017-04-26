package ua.test.commands.student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowTestResultCommand implements ua.test.commands.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double mark = (double) request.getSession().getAttribute("mark");

        request.getSession().removeAttribute("mark");
        request.setAttribute("mark", mark);
        request.getRequestDispatcher("/pages/student/test_result.jsp").forward(request, response);
    }
}
