package ua.test.commands.student;

import ua.test.commands.Command;
import ua.test.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Test test = (Test) request.getSession().getAttribute("userTest");

        request.setAttribute("test", test);
        request.getRequestDispatcher("/pages/student/test.jsp").forward(request, response);
    }
}
