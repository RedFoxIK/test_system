package ua.test.commands.student;

import ua.test.commands.Command;
import ua.test.entity.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class ShowTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Test test = (Test) request.getSession().getAttribute("userTest");
        String timeTest = (String) request.getSession().getAttribute("testTime");

        request.setAttribute("timeTest", timeTest);
        request.setAttribute("test", test);
        request.getRequestDispatcher("/pages/student/test.jsp").forward(request, response);
    }
}
